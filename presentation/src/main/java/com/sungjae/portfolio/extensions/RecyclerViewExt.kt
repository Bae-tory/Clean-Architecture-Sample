package com.sungjae.portfolio.extensions

import android.view.MotionEvent
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.sungjae.portfolio.base.BaseRecyclerViewAdapter

@Suppress("UNCHECKED_CAST")
@BindingAdapter(
    "bindContentItem",
    "bindContentItemLayout",
    "bindHeaderItem",
    "bindHeaderItemLayout",
    "bindFooterItem",
    "bindFooterItemLayout",
    "bindVm",
    "enableTouch",
    "bindEventHolder",
    requireAll = false
)
fun RecyclerView.setRecyclerViewAdapter(
    contentItem: List<Any>? = null,
    @LayoutRes contentItemRes: Int? = null,
    headerItem: Any? = null,
    @LayoutRes headerItemRes: Int? = null,
    footerItem: Any? = null,
    @LayoutRes footerItemRes: Int? = null,
    vm: Any? = null,
    enableTouch: Boolean = true,
    eventHolder: Any? = null
) {
    val adapter = adapter as? BaseRecyclerViewAdapter ?: BaseRecyclerViewAdapter()
    adapter.contentLayoutResId = contentItemRes
    adapter.headerItem = headerItem
    adapter.headerLayoutResId = headerItemRes
    adapter.footerItem = footerItem
    adapter.footerLayoutResId = footerItemRes
    adapter.vm = vm
    adapter.eventHolder = eventHolder ?: vm

    this.adapter = adapter

    contentItem?.let {
        adapter.updateData(it)
        adapter.notifyDataSetChanged()
    }

    addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

    addOnItemTouchListener(object : RecyclerView.SimpleOnItemTouchListener() {
        override fun onInterceptTouchEvent(view: RecyclerView, e: MotionEvent) = enableTouch
    })
}