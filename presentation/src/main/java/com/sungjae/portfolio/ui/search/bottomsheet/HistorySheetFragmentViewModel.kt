package com.sungjae.portfolio.ui.search.bottomsheet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sungjae.portfolio.R
import com.sungjae.portfolio.base.BaseViewModel
import com.sungjae.portfolio.components.ItemClickListener
import com.sungjae.portfolio.components.Tabs
import com.sungjae.portfolio.domain.exception.InvalidSingleException
import com.sungjae.portfolio.domain.exception.InvalidTabTypeException
import com.sungjae.portfolio.domain.usecase.GetContentQueriesUseCase
import com.sungjae.portfolio.models.HistoryModel

class HistorySheetFragmentViewModel(
    private val tab: Tabs,
    private val getContentQueriesUseCase: GetContentQueriesUseCase
) : BaseViewModel(), ItemClickListener {

    private val _searchHistoryResult = MutableLiveData<List<HistoryModel>>()
    val searchHistoryResult: LiveData<List<HistoryModel>> get() = _searchHistoryResult

    private val _clickedQuery = MutableLiveData<String>()
    val clickedQuery: LiveData<String> get() = _clickedQuery

    fun getSearchQueryHistory() =
        getContentQueriesUseCase.execute(tab.name)
            .subscribe({
                _searchHistoryResult.value = mappingQuery(it)
            }, {
                _errorMsg.value =
                    when (it) {
                        is InvalidSingleException -> R.string.error_single_fail
                        is InvalidTabTypeException -> R.string.error_tab_fail
                        else -> R.string.error_load_fail
                    }
            }).addDisposable()

    private fun mappingQuery(it: List<String>): ArrayList<HistoryModel> {
        return ArrayList<HistoryModel>().also { arrayList ->
            it.forEach { history ->
                arrayList.add(
                    HistoryModel(
                        query = history
                    )
                )
            }
        }
    }

    override fun onClick(item: Any?) {
        _clickedQuery.value = (item as? HistoryModel)?.query
    }
}