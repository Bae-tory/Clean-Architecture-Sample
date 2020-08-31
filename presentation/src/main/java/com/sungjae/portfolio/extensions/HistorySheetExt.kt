package com.sungjae.portfolio.extensions

import android.content.res.ColorStateList
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.doOnLayout
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import com.sungjae.portfolio.R
import com.sungjae.portfolio.ui.search.bottomsheet.HistorySheetFragmentViewModel
import com.sungjae.portfolio.ui.search.bottomsheet.lerp
import com.sungjae.portfolio.ui.search.bottomsheet.lerpArgb

@BindingAdapter(
    "attachSheet",
    "attachDivider",
    "attachIvCollapseIcon",
    "attachIvIcon",
    "attachRvHistory",
    "attachSheetExpand",
    "attachTvTitle",
    "bindLifecycleOwner",
    "bindActivity",
    "bindVm"
)
fun View.setHistorySheet(
    @IdRes sheetId: Int,
    @IdRes dividerId: Int,
    @IdRes ivCollapseIconId: Int,
    @IdRes ivIconId: Int,
    @IdRes rvHistoryId: Int,
    @IdRes sheetExpandId: Int,
    @IdRes tvTitleId: Int,
    lifecycleOwner: LifecycleOwner,
    activity: FragmentActivity,
    vm: HistorySheetFragmentViewModel
) {
    val sheet = findViewById<ConstraintLayout>(sheetId)
    val divider = findViewById<View>(dividerId)
    val ivCollapseIcon = findViewById<ImageView>(ivCollapseIconId)
    val ivIcon = findViewById<ImageView>(ivIconId)
    val rvHistory = findViewById<RecyclerView>(rvHistoryId)
    val sheetExpand = findViewById<View>(sheetExpandId)
    val tvTitle = findViewById<TextView>(tvTitleId)

    val behavior = BottomSheetBehavior.from(sheet)
    val backCallback =
        activity.onBackPressedDispatcher.addCallback(lifecycleOwner, false) {
            behavior.state = STATE_COLLAPSED
        }

    val historyStartColor = sheet.context.getColor(R.color.history_200)
    val historyEndColor = sheet.context.getColorStateList(R.color.history_200).defaultColor

    val sheetBackground =
        MaterialShapeDrawable(ShapeAppearanceModel.builder(context, R.style.ShapeAppearance_History_MinimizedSheet, 0).build()).apply {
            fillColor = ColorStateList.valueOf(historyStartColor)
        }
    sheet.background = sheetBackground
    sheet.doOnLayout {
        val peek = behavior.peekHeight
        val maxTranslationX = (it.width - peek).toFloat()
        sheet.translationX = (sheet.width - peek).toFloat()

        behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                backCallback.isEnabled = newState == STATE_EXPANDED
                if (newState == STATE_EXPANDED) {
                    vm.getSearchQueryHistory().start()
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                sheet.translationX =
                    lerp(maxTranslationX, 0f, 0f, 0.15f, slideOffset)
                sheetBackground.interpolation = lerp(1f, 0f, 0f, 0.15f, slideOffset)
                sheetBackground.fillColor =
                    ColorStateList.valueOf(
                        lerpArgb(historyStartColor, historyEndColor, 0f, 0.3f, slideOffset)
                    )

                ivIcon.alpha = lerp(1f, 0f, 0f, 0.15f, slideOffset)
                sheetExpand.alpha = lerp(1f, 0f, 0f, 0.15f, slideOffset)
                tvTitle.alpha = lerp(0f, 1f, 0.2f, 0.8f, slideOffset)
                ivCollapseIcon.alpha = lerp(0f, 1f, 0.2f, 0.8f, slideOffset)
                divider.alpha = lerp(0f, 1f, 0.2f, 0.8f, slideOffset)
                rvHistory.alpha = lerp(0f, 1f, 0.2f, 0.8f, slideOffset)
                sheetExpand.visibility = if (slideOffset < 0.5f) View.VISIBLE else View.GONE
            }
        })
        sheet.doOnApplyWindowInsets { _, insets, _, _ ->
            behavior.peekHeight = peek + insets.systemWindowInsetBottom
        }

    } // doOnLayout

    ivCollapseIcon.setOnClickListener { behavior.state = STATE_COLLAPSED }
    sheetExpand.setOnClickListener { behavior.state = STATE_EXPANDED }

}