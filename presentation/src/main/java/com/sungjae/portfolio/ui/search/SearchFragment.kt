package com.sungjae.portfolio.ui.search

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.sungjae.portfolio.R
import com.sungjae.portfolio.base.BaseFragment
import com.sungjae.portfolio.components.Constants.TAB_TYPE
import com.sungjae.portfolio.components.Tabs
import com.sungjae.portfolio.databinding.FragmentContentsBinding
import com.sungjae.portfolio.databinding.FragmentSearchBinding
import com.sungjae.portfolio.extensions.toast
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>(R.layout.fragment_search) {

    override val vm: SearchViewModel by viewModel { parametersOf(tab) }


    val tab: Tabs by lazy {
        arguments?.get(TAB_TYPE) as? Tabs ?: error(getString(R.string.wrong_enum_type))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind {
            vm = this@SearchFragment.vm
            rvContents.addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            )

            rvContents.adapter = SearchAdapter(tab)
        }

        vm.isResultEmptyError.observe(viewLifecycleOwner, Observer { empty ->
            if (empty) requireActivity().toast(getString(R.string.error_load_fail))
        })
    }

    override fun onResume() {
        vm.getCacheContents()
        super.onResume()
    }


    fun loadContentsByHistoryQuery(query: String) {
        vm.loadContentsByHistory(query)
    }

    companion object {

        fun newInstance(type: Tabs) = SearchFragment().apply {
            arguments = bundleOf(TAB_TYPE to type)
        }

    }
}