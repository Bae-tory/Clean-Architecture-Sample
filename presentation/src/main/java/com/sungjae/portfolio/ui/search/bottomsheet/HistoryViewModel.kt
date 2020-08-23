package com.sungjae.portfolio.ui.search.bottomsheet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sungjae.portfolio.base.BaseViewModel
import com.sungjae.portfolio.components.Tabs
import com.sungjae.portfolio.domain.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers

class HistoryViewModel(
    private val tab: Tabs,
    private val naverDataRepository: Repository
) : BaseViewModel() {

    private val _searchHistoryResult = MutableLiveData<List<String>>()

    val searchHistoryResult: LiveData<List<String>> get() = _searchHistoryResult

    fun getSearchQueryHistory() =
        naverDataRepository.getContentQueries(tab.name)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _searchHistoryResult.value = it
            }, {}).addDisposable()

}