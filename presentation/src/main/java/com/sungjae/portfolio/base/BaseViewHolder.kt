package com.sungjae.portfolio.base

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView
import com.sungjae.portfolio.BR


open class BaseViewHolder(parent: ViewGroup, @LayoutRes layoutResForViewType: Int) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutResForViewType, parent, false)),
    LifecycleOwner {

    private val binding: ViewDataBinding = DataBindingUtil.bind(itemView)!!
    private val lifecycleRegistry by lazy { LifecycleRegistry(this) }

    open fun onBindViewHolder(
        item: Any?,
        vm: Any?,
        eventHolder: Any?
    ) {
        item?.let {
            Log.d("onBindViewHolder", "$it")
            with(binding) {
                lifecycleOwner = this@BaseViewHolder
                setVariable(BR.index, adapterPosition)
                setVariable(BR.item, item)
                setVariable(BR.vm, vm)
                setVariable(BR.eventHolder, eventHolder)
                executePendingBindings()
            }
        }
    }

    fun onAttach() {
        lifecycleRegistry.currentState = Lifecycle.State.RESUMED
    }

    fun onDetach() {
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
    }

    override fun getLifecycle(): Lifecycle = lifecycleRegistry
}