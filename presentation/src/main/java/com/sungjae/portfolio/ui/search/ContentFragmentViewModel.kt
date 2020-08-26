package com.sungjae.portfolio.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.sungjae.portfolio.R
import com.sungjae.portfolio.base.BaseViewModel
import com.sungjae.portfolio.components.ItemClickListener
import com.sungjae.portfolio.components.SingleLiveEvent
import com.sungjae.portfolio.components.Tabs
import com.sungjae.portfolio.domain.entity.request.ContentEntity
import com.sungjae.portfolio.domain.exception.InvalidQueryBlankException
import com.sungjae.portfolio.domain.exception.InvalidQueryException
import com.sungjae.portfolio.domain.exception.InvalidSingleException
import com.sungjae.portfolio.domain.exception.InvalidTabTypeException
import com.sungjae.portfolio.domain.usecase.GetCacheContentUseCase
import com.sungjae.portfolio.domain.usecase.GetContentUseCase
import com.sungjae.portfolio.domain.usecase.LoadContentByHistoryUseCase
import com.sungjae.portfolio.mapper.ContentPresenterMapper
import com.sungjae.portfolio.models.ContentItem


class ContentFragmentViewModel(
    private val tab: Tabs,
    private val getContentUseCase: GetContentUseCase,
    private val getCacheContentUseCase: GetCacheContentUseCase,
    private val loadContentByHistoryUseCase: LoadContentByHistoryUseCase
) : BaseViewModel(), ItemClickListener, ContentPresenterMapper<ContentEntity, ArrayList<ContentItem>> {

    private val _searchQueryResultList = MutableLiveData<List<ContentItem>>()
    val searchQueryResultList: LiveData<List<ContentItem>> get() = _searchQueryResultList

    private val _invokeWebBrowser = SingleLiveEvent<String>()
    val invokeWebBrowser: LiveData<String> get() = _invokeWebBrowser

    private val _isShowLoadingProgressBar = MutableLiveData<Boolean>()
    val isShowLoadingProgressBar: LiveData<Boolean> get() = _isShowLoadingProgressBar

    val searchQuery = MutableLiveData<String>()

    val isResultEmptyError: LiveData<Boolean> = Transformations.map(searchQueryResultList) { it.isNullOrEmpty() }

    fun loadContents() {
        getContentUseCase
            .execute(Pair(tab.name, searchQuery.value))
            .doOnSubscribe { _isShowLoadingProgressBar.value = true }
            .doAfterTerminate { _isShowLoadingProgressBar.value = false }
            .subscribe({
                _searchQueryResultList.value = toDomain(it)
            }, {
                _errorMsg.value =
                    when (it) {
                        is InvalidQueryException -> R.string.error_query_fail
                        is InvalidSingleException -> R.string.error_single_fail
                        is InvalidQueryBlankException -> R.string.please_write
                        else -> R.string.error_load_fail
                    }
            }).addDisposable()
    }

    fun getCacheContents() {
        getCacheContentUseCase
            .execute(tab.name)
            .subscribe({
                _searchQueryResultList.value = toDomain(it)
                searchQuery.value = it.query
            }, {
                _errorMsg.value =
                    when (it) {
                        is InvalidSingleException -> R.string.error_single_fail
                        is InvalidTabTypeException -> R.string.error_tab_fail
                        else -> R.string.error_load_fail
                    }
            }).addDisposable()
    }

    fun loadContentByHistory(query: String) {
        loadContentByHistoryUseCase
            .execute(Pair(tab.name, query))
            .subscribe({
                _searchQueryResultList.value = toDomain(it)
                searchQuery.value = it.query
                loadContents()
            }, {
                when (it) {
                    is InvalidQueryException -> R.string.error_query_fail
                    is InvalidTabTypeException -> R.string.error_tab_fail
                    is InvalidSingleException -> R.string.error_single_fail
                    else -> R.string.error_load_fail
                }
            }).addDisposable()
    }

    override fun onClick(item: Any?) {
        _invokeWebBrowser.value = (item as ContentItem).link
    }

    override fun toDomain(data: ContentEntity): ArrayList<ContentItem> =
        ArrayList<ContentItem>().also { arrayList ->
            data.contentEntityItems.forEach { entity ->
                arrayList.add(
                    ContentItem(
                        image = entity.image,
                        actor = entity.actor,
                        description = entity.description,
                        title = entity.title,
                        link = entity.link,
                        isThumbnailVisible = when (tab) {
                            Tabs.BLOG, Tabs.NEWS -> false
                            else -> true
                        }
                    )
                )
            }
        }
}