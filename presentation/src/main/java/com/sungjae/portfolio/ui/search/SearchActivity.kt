package com.sungjae.portfolio.ui.search

import com.sungjae.portfolio.R
import com.sungjae.portfolio.base.BaseActivity
import com.sungjae.portfolio.databinding.ActivitySearchBinding
import org.koin.android.viewmodel.ext.android.viewModel

class SearchActivity : BaseActivity<ActivitySearchBinding, SearchViewModel>(R.layout.activity_search) {
    override val vm: SearchViewModel by viewModel()
}