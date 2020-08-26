package com.sungjae.portfolio.components

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.sungjae.portfolio.BR
import com.sungjae.portfolio.base.BaseViewModel


abstract class BaseDialogHelper<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val dialogLayoutRes: Int,
    private val context: Context
) : LifecycleOwner {

    protected var binding: B? = null
        private set
    protected abstract val vm: VM
    private val lifecycleRegistry by lazy { LifecycleRegistry(this) }

    open var bottomAnim = false
    open var cancelable: Boolean = true
    open var gravity: Int = Gravity.CENTER
    open var isBackgroundTransparent: Boolean = true
    open var totalSize = false
    private var dialog: AlertDialog? = null

    fun initDialog() {
        setBinding()
        setDialog()
    }

    private fun setBinding() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), dialogLayoutRes, null, false)
        bind {
            lifecycleOwner = this@BaseDialogHelper
            setVariable(BR.vm, vm)
        }
    }

    private fun setDialog() {
        dialog = AlertDialog.Builder(context).setView(binding?.root).setCancelable(cancelable).create()
        val bgRes = if (isBackgroundTransparent) android.R.color.transparent else android.R.color.black
        dialog?.window?.setBackgroundDrawableResource(bgRes)
//        if (bottomAnim) dialog?.window?.attributes?.windowAnimations = R.style.dialogAnim
    }

    protected fun bind(action: B.() -> Unit) {
        binding?.run(action)
    }

    protected fun show() {
        dialog?.show()

        val windows = dialog?.window
        if (totalSize) {
            windows?.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT)
        } else {
            windows?.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
        }
        if (gravity != Gravity.CENTER) {
            dialog?.window?.setGravity(gravity)
        }
    }


    open fun dismiss() {
        if (isShowing() == true) dialog?.dismiss()
    }

    private fun isShowing() = dialog?.isShowing

    override fun getLifecycle(): Lifecycle = lifecycleRegistry
}