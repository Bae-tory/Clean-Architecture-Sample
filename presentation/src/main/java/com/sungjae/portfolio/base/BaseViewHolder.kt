package com.sungjae.portfolio.base

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


open class BaseViewHolder(@LayoutRes layoutRes: Int, parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)),
    LifecycleOwner {

    private val binding: ViewDataBinding = DataBindingUtil.bind(itemView)!!
    private val lifecycleRegistry by lazy { LifecycleRegistry(this) }

    open fun onBindViewHolder(
        item: Any?,
        vm: Any?
    ) {
        item?.let {
            with(binding) {
                lifecycleOwner = this@BaseViewHolder
                setVariable(BR.index, adapterPosition)
                setVariable(BR.item, item)
                setVariable(BR.vm, vm)
                executePendingBindings()
            }
        }
    }

    override fun getLifecycle(): Lifecycle = lifecycleRegistry
}