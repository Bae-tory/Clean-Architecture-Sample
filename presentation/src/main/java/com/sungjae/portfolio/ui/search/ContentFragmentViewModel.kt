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
import com.sungjae.portfolio.domain.repository.Repository
import com.sungjae.portfolio.models.ContentItem
import io.reactivex.android.schedulers.AndroidSchedulers


class ContentFragmentViewModel(
    private val tab: Tabs,
    private val repository: Repository
) : BaseViewModel(), ItemClickListener {

    private val _searchQueryResultList = MutableLiveData<List<ContentItem>>()
    val searchQueryResultList: LiveData<List<ContentItem>> get() = _searchQueryResultList

    private val _invokeWebBrowser = SingleLiveEvent<String>()
    val invokeWebBrowser: LiveData<String> get() = _invokeWebBrowser

    private val _isShowLoadingProgressBar = MutableLiveData<Boolean>()
    val isShowLoadingProgressBar: LiveData<Boolean> get() = _isShowLoadingProgressBar

    val searchQuery = MutableLiveData<String>()
    val isResultEmptyError: LiveData<Boolean> = Transformations.map(searchQueryResultList) { it.isNullOrEmpty() }

    fun loadContents() {
        if (searchQuery.value.isNullOrBlank()) {
            mutableErrorTextResId.value = (R.string.please_write)
        } else {
            repository.getContents(type = tab.name, query = searchQuery.value!!)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { _isShowLoadingProgressBar.value = true }
                .doAfterTerminate { _isShowLoadingProgressBar.value = false }
                .subscribe({
                    _searchQueryResultList.value = mappingContentItem(it)
                }, {
                    mutableErrorTextResId.value = R.string.error_load_fail
                }).addDisposable()

        }
    }

    fun getCacheContents() {
        repository.getCache(tab.name)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _searchQueryResultList.value = mappingContentItem(it)
                searchQuery.value = it.query
            }, {}).addDisposable()
    }

    fun loadContentsByHistory(query: String) {
        repository.getContentsByHistory(tab.name, query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _searchQueryResultList.value = mappingContentItem(it)
                searchQuery.value = it.query
                loadContents()
            }, {
                mutableErrorTextResId.value = R.string.error_load_fail
            }).addDisposable()
    }

    override fun onClick(item: Any?) {
        _invokeWebBrowser.value = (item as ContentItem).link
    }

    private fun mappingContentItem(it: ContentEntity): ArrayList<ContentItem> {
        val contentList = ArrayList<ContentItem>()
        it.contentEntityItems.forEach { entity ->
            contentList.add(
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
        return contentList
    }
}