package com.sungjae.portfolio.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.sungjae.portfolio.BR


abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutResId: Int
) : AppCompatActivity() {

    protected abstract val vm: VM
    protected lateinit var binding: B
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@BaseActivity, layoutResId)
        bind {
            lifecycleOwner = this@BaseActivity
            setVariable(BR.vm, vm)
        }
    }

    protected fun bind(action: B.() -> Unit) {
        binding.run(action)
    }
}