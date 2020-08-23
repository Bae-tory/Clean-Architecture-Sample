package com.sungjae.portfolio.ui.search

import android.content.Context
import com.sungjae.portfolio.R
import com.sungjae.portfolio.components.BaseDialogHelper
import com.sungjae.portfolio.databinding.DialogConfirmBinding

class ConfirmDialog(
    context: Context,
    override val vm: SearchViewModel
) : BaseDialogHelper<ConfirmDialog, DialogConfirmBinding, SearchViewModel>(R.layout.dialog_confirm, context) {

    override fun show() {

    }

    override fun dismiss() {
        vm.unBindViewModel()
        super.dismiss()
    }
}