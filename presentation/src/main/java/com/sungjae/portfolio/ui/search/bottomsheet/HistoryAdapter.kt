package com.sungjae.portfolio.ui.search.bottomsheet

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.sungjae.portfolio.R
import com.sungjae.portfolio.databinding.ItemSearchHistoryBinding

class HistoryAdapter(
    private val onClick: (String) -> Unit,
    @LayoutRes private val layoutRes: Int = R.layout.item_search_history
) : BaseRecyclerView.Adapter<String, ItemSearchHistoryBinding>(
    layoutRes
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder =
        HistoryViewHolder(parent).apply {
            itemView.setOnClickListener {
                onClick(getItem(adapterPosition) ?: "")
            }
        }

    inner class HistoryViewHolder(
        parent: ViewGroup
    ) : BaseRecyclerView.BaseViewHolder<SearchHistoryItemBinding>(
        parent,
        layoutRes
    ) {
        override fun bindItem(item: Any?) {
            binding.query = (item as? String) ?: ""
        }
    }
}
