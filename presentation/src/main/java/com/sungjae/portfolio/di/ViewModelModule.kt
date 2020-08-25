package com.sungjae.portfolio.di

import com.sungjae.portfolio.components.Tabs
import com.sungjae.portfolio.providers.ResourceProvider
import com.sungjae.portfolio.providers.ResourceProviderImpl
import com.sungjae.portfolio.ui.search.ContentFragmentViewModel
import com.sungjae.portfolio.ui.search.SearchViewModel
import com.sungjae.portfolio.ui.search.TabFragmentViewModel
import com.sungjae.portfolio.ui.search.bottomsheet.HistoryViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    single<ResourceProvider> { ResourceProviderImpl(androidApplication()) }

    viewModel { SearchViewModel() }

    viewModel { (tab: Tabs) -> ContentFragmentViewModel(tab, get()) }
    viewModel { (tab: Tabs) -> HistoryViewModel(tab, get()) }
    viewModel { TabFragmentViewModel() }
}