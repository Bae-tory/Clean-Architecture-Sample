package com.sungjae.portfolio.components

import androidx.hilt.Assisted
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sungjae.portfolio.domain.usecase.GetCacheContentUseCase
import com.sungjae.portfolio.domain.usecase.GetContentQueriesUseCase
import com.sungjae.portfolio.domain.usecase.GetContentUseCase
import com.sungjae.portfolio.domain.usecase.LoadContentByHistoryUseCase
import com.sungjae.portfolio.ui.search.ContentFragmentViewModel
import com.sungjae.portfolio.ui.search.bottomsheet.HistorySheetFragmentViewModel
import javax.inject.Inject

//class ContentFragmentViewModelFactory @Inject constructor(
//    @Assisted private val tab: Tabs,
//    private val getContentUseCase: GetContentUseCase,
//    private val getCacheContentUseCase: GetCacheContentUseCase,
//    private val loadContentByHistoryUseCase: LoadContentByHistoryUseCase
//) : ViewModelProvider.Factory {
//
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
//        ContentFragmentViewModel(
//            tab,
//            getContentUseCase,
//            getCacheContentUseCase,
//            loadContentByHistoryUseCase
//        ) as T
//
//}
//
//class HistorySheetFragmentViewModelFactory @Inject constructor(
//    @Assisted private val tab: Tabs,
//    private val getContentQueriesUseCase: GetContentQueriesUseCase
//) : ViewModelProvider.Factory {
//
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
//        HistorySheetFragmentViewModel(tab, getContentQueriesUseCase)
//                as T
//}