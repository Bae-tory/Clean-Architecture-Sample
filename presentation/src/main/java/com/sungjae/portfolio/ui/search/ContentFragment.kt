package com.sungjae.portfolio.ui.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.sungjae.portfolio.R
import com.sungjae.portfolio.base.BaseFragment
import com.sungjae.portfolio.components.Constants.TAB_TYPE
import com.sungjae.portfolio.components.Tabs
import com.sungjae.portfolio.databinding.FragmentContentBinding
import com.sungjae.portfolio.extensions.toast
import com.sungjae.portfolio.ui.search.bottomsheet.HistorySheetFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ContentFragment :
    BaseFragment<FragmentContentBinding, ContentFragmentViewModel>(R.layout.fragment_content) {

    private lateinit var historyFrag: HistorySheetFragment

    //    val tab: Tabs by lazy {
//        arguments?.get(TAB_TYPE) as? Tabs ?: error(getString(R.string.wrong_enum_type))
//    }
    override val vm: ContentFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!historyFrag.isAdded) {
            Log.d("HistoryFragment", "Added")
            childFragmentManager.beginTransaction()
                .replace(R.id.history_sheet_fragment_container, historyFrag)
                .addToBackStack(null)
                .commit()
        }


        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.isResultEmptyError.observe(viewLifecycleOwner, Observer {
            if (it) requireActivity().toast(getString(R.string.error_load_fail))
        })

        vm.errorMsg.observe(viewLifecycleOwner, Observer {
            requireActivity().toast(getString(it))
        })

        vm.invokeWebBrowser.observe(viewLifecycleOwner, Observer {
            ContextCompat.startActivity(
                requireContext(),
                Intent(Intent.ACTION_VIEW, Uri.parse(it)),
                null
            )
        })
    }

    override fun onResume() {
        vm.getCacheContents()
        super.onResume()
    }


    fun loadContentsByHistoryQuery(query: String?) {
        query?.let {
            vm.loadContentByHistory(it)
        }
    }

    companion object {
        fun newInstance(type: Tabs) = ContentFragment().apply {
            arguments = bundleOf(TAB_TYPE to type)
            historyFrag = HistorySheetFragment.newInstance(type)
        }
    }
}