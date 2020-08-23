package com.sungjae.portfolio.ui.search

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.sungjae.portfolio.R
import com.sungjae.portfolio.components.Tabs
import com.sungjae.portfolio.databinding.ItemRvContentBinding
import com.sungjae.portfolio.domain.entity.request.ContentItem

class SearchAdapter(
    private val tab: Tabs,
    @LayoutRes private val layoutRes: Int = R.layout.item_rv_content
) : BaseRecyclerView.Adapter<ContentItem, ItemRvContentBinding>(
    layoutRes
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContentViewHolder =
        ContentViewHolder(viewType, parent, layoutRes)

    override fun getItemViewType(position: Int): Int = tab.ordinal
}