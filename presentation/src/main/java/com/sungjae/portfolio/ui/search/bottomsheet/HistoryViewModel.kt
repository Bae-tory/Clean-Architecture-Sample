package com.sungjae.portfolio.ui.search.bottomsheet

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sungjae.portfolio.base.BaseViewModel
import com.sungjae.portfolio.components.ItemClickListener
import com.sungjae.portfolio.components.Tabs
import com.sungjae.portfolio.domain.repository.Repository
import com.sungjae.portfolio.models.HistoryModel
import io.reactivex.android.schedulers.AndroidSchedulers

class HistoryViewModel(
    private val tab: Tabs,
    private val repository: Repository
) : BaseViewModel(), ItemClickListener {

    private val _searchHistoryResult = MutableLiveData<List<HistoryModel>>()
    val searchHistoryResult: LiveData<List<HistoryModel>> get() = _searchHistoryResult

    private val _clickedQuery = MutableLiveData<String>()
    val clickedQuery: LiveData<String> get() = _clickedQuery

    fun getSearchQueryHistory() =
        repository.getContentQueries(tab.name)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _searchHistoryResult.value = mappingQuery(it)
            }, {}).addDisposable()

    private fun mappingQuery(it: List<String>): ArrayList<HistoryModel> {
        val historyList = ArrayList<HistoryModel>()
        it.forEach { history ->
            historyList.add(
                HistoryModel(
                    query = history
                )
            )
        }
        return historyList
    }

    override fun onClick(item: Any?) {
        Log.d("HistoryViewModelonClick", "$item")
        _clickedQuery.value = (item as? HistoryModel)?.query
    }
}