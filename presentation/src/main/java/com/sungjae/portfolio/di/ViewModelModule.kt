package com.sungjae.portfolio.di

import android.content.Context
import com.sungjae.portfolio.providers.ResourceProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ResourceProviderModule {

    @Provides
    @Singleton
    fun provideResourceProvider(@ApplicationContext context: Context): ResourceProviderImpl =
        ResourceProviderImpl(context)

}
/*
val viewModelModule = module {
    viewModel { ContentViewModel() }
    viewModel { (tab: Tabs) -> ContentFragmentViewModel(tab, get(), get(), get()) }
    viewModel { (tab: Tabs) -> HistorySheetFragmentViewModel(tab, get()) }
    viewModel { TabFragmentViewModel() }
}
*/
