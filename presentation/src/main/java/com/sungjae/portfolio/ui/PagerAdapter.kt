package com.sungjae.portfolio.ui

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.sungjae.portfolio.components.Tabs
import com.sungjae.portfolio.ui.search.ContentFragment

class PagerAdapter(fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int) = ContentFragment.newInstance(Tabs.values()[position])
    override fun getCount(): Int = Tabs.values().count()
}