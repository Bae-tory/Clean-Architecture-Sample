package com.sungjae.portfolio.di

import android.content.Context
import com.sungjae.portfolio.ui.search.ConfirmDialog
import org.koin.dsl.module

val dialogModule = module {

    factory { (context: Context) -> ConfirmDialog(context, get()) }
}