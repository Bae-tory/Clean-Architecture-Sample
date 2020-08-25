package com.sungjae.portfolio.ui.search

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayout
import com.sungjae.portfolio.R
import com.sungjae.portfolio.base.BaseFragment
import com.sungjae.portfolio.components.Tabs
import com.sungjae.portfolio.databinding.FragmentTabBinding
import com.sungjae.portfolio.ui.PagerAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class TabFragment : BaseFragment<FragmentTabBinding, TabFragmentViewModel>(R.layout.fragment_tab) {

    override val vm: TabFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
    }

    private fun initViewPager() {
        binding.run {
            vpContent.run {
                adapter = fragmentManager?.let { PagerAdapter(it) }
                addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(layTab))
            }

            with(layTab) {
                Tabs.values().forEach { addTab(newTab().setText(it.id)) }
                addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        tab?.run { vpContent.currentItem = position }
                    }

                    override fun onTabReselected(tab: TabLayout.Tab?) {}
                    override fun onTabUnselected(tab: TabLayout.Tab?) {}
                })
            }
        }
    }
}