package com.sungjae.portfolio.ui.search

import com.sungjae.portfolio.R
import com.sungjae.portfolio.base.BaseActivity
import com.sungjae.portfolio.databinding.ActivityContentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ContentActivity : BaseActivity<ActivityContentBinding, ContentViewModel>(R.layout.activity_content) {
    override val vm: ContentViewModel by viewModel()
}