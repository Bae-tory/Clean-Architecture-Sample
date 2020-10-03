package com.sungjae.portfolio.ui.search.bottomsheet

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sungjae.portfolio.R
import com.sungjae.portfolio.base.BaseViewModel
import com.sungjae.portfolio.components.Constants.TAB_TYPE
import com.sungjae.portfolio.components.ItemClickListener
import com.sungjae.portfolio.components.Tabs
import com.sungjae.portfolio.domain.exception.InvalidSingleException
import com.sungjae.portfolio.domain.exception.InvalidTabTypeException
import com.sungjae.portfolio.domain.exception.Result
import com.sungjae.portfolio.domain.usecase.GetContentQueriesUseCase
import com.sungjae.portfolio.models.HistoryModel
import kotlinx.coroutines.launch

class HistorySheetFragmentViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val getContentQueriesUseCase: GetContentQueriesUseCase
) : BaseViewModel(), ItemClickListener {

    init {
        Log.d("HistorySheetFragmentVM", "init..${savedStateHandle.get<Tabs>(TAB_TYPE) as Tabs}")
    }

    private val tab: Tabs = savedStateHandle.get<Tabs>(TAB_TYPE) as Tabs

    private val _searchHistoryResult = MutableLiveData<List<HistoryModel>>()
    val searchHistoryResult: LiveData<List<HistoryModel>> get() = _searchHistoryResult

    private val _clickedQuery = MutableLiveData<String>()
    val clickedQuery: LiveData<String> get() = _clickedQuery

    fun getSearchQueryHistory() =
        viewModelScope.launch(exceptionDispatchers) {
            when (val result = getContentQueriesUseCase.execute(tab.name)) {
                is Result.OnSuccess -> _searchHistoryResult.value = mappingQuery(result.data)
                is Result.OnError -> {
                    _errorMsg.value =
                        when (result.exception) {
                            is InvalidSingleException -> R.string.error_single_fail
                            is InvalidTabTypeException -> R.string.error_tab_fail
                            else -> R.string.error_load_fail
                        }
                }
            }
        }

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