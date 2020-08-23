package com.sungjae.portfolio.base

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

open class BaseRecyclerViewAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    protected open val items = mutableListOf<Any>()
    var headerItem: Any? = null
    var contentItem: Any? = null
    var footerItem: Any? = null
    var vm: Any? = null

    private var headerCount: Int = 0

    @LayoutRes
    var headerLayoutResId: Int? = null
        set(value) {
            if (value == field) {
                return
            }
            field = value
            headerCount = if (value == null) 0 else 1
        }

    @LayoutRes
    var contentLayoutResId: Int? = null
        set(value) {
            if (value == field) {
                return
            }
            field = value
        }

    private var footerCount: Int = 0

    @LayoutRes
    var footerLayoutResId: Int? = null
        set(value) {
            if (value == field) {
                return
            }
            field = value
            footerCount = if (value == null) 0 else 1
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return object : BaseViewHolder(viewType, parent) {}
    }

    override fun getItemCount(): Int = items.count() + headerCount + footerCount

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = when {
            isHeaderPosition(position) -> headerItem
            isFooterPosition(position) -> footerItem
            else -> items[position - headerCount]
        }
        holder.onBindViewHolder(item, vm)
    }

    protected fun getItem(position: Int): Any =
        items.getOrNull(position) ?: throw ArrayIndexOutOfBoundsException()

    fun updateData(items: List<Any>?) {
        items?.let {
            this.items.run {
                clear()
                addAll(it)
            }
        }
        notifyDataSetChanged()
    }


    open fun clearItems() {
        val itemCnt = items.count()
        this.items.clear()
        notifyItemRangeRemoved(0, itemCnt)
    }

    override fun getItemViewType(position: Int): Int {
        if (isHeaderPosition(position)) {
            return headerLayoutResId!!
        }
        if (isFooterPosition(position)) {
            return footerLayoutResId!!
        }
        return contentLayoutResId!!
    }

    private fun isHeaderPosition(position: Int) = headerCount != 0 && position == 0

    private fun isFooterPosition(position: Int) = footerCount != 0 && position == itemCount - 1

}
