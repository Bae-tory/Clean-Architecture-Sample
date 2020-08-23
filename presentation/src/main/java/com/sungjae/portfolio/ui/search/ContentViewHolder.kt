package com.sungjae.portfolio.ui.search

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.sungjae.portfolio.data.models.ContentItem
import com.sungjae.portfolio.databinding.ItemRvContentBinding

class ContentViewHolder(
    private val viewType: Int,
    parent: ViewGroup,
    @LayoutRes private val layoutRes: Int
) : BaseRecyclerView.BaseViewHolder<ItemRvContentBinding>(
    parent,
    layoutRes
) {

    override fun bindItem(item: Any?) {
        (item as? ContentItem)?.let {
            binding.viewType = viewType
            binding.contentItem = it
        }
    }
}