package com.sungjae.portfolio.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.sungjae.portfolio.BR


abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel>(@LayoutRes private val layoutResId: Int) : Fragment() {

    protected abstract val vm: VM
    protected lateinit var binding: B
        private set

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        bind {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.vm, vm)
        }
        return binding.root
    }

    protected fun bind(action: B.() -> Unit) {
        binding.run(action)
    }
}