package com.sungjae.portfolio.ui.search.bottomsheet

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.core.view.doOnLayout
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import com.sungjae.portfolio.R
import com.sungjae.portfolio.base.BaseFragment
import com.sungjae.portfolio.components.Tabs
import com.sungjae.portfolio.databinding.FragmentHistorySheetBinding
import com.sungjae.portfolio.extensions.addCallback
import com.sungjae.portfolio.extensions.doOnApplyWindowInsets
import com.sungjae.portfolio.ui.search.ContentFragment
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HistorySheetFragment : BaseFragment<FragmentHistorySheetBinding, HistoryViewModel>(R.layout.fragment_history_sheet) {

    private fun getTab(): Tabs = (requireParentFragment() as? ContentFragment)?.tab ?: error(getString(R.string.wrong_enum_type))
    override val vm: HistoryViewModel by viewModel { parametersOf(getTab()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind {
            val behavior = BottomSheetBehavior.from(historySheet)
            val backCallback =
                requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, false) {
                    behavior.state = BottomSheetBehavior.STATE_COLLAPSED
                }

            val historyStartColor = historySheet.context.getColor(R.color.history_200)
            val historyEndColor = historySheet.context.getColorStateList(R.color.history_200).defaultColor

            val sheetBackground =
                MaterialShapeDrawable(ShapeAppearanceModel.builder(context, R.style.ShapeAppearance_History_MinimizedSheet, 0).build()).apply {
                    fillColor = ColorStateList.valueOf(historyStartColor)
                }
            historySheet.background = sheetBackground
            historySheet.doOnLayout {
                val peek = behavior.peekHeight
                val maxTranslationX = (it.width - peek).toFloat()
                historySheet.translationX = (historySheet.width - peek).toFloat()

                behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                        backCallback.isEnabled = newState == BottomSheetBehavior.STATE_EXPANDED
                        if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                            vm?.getSearchQueryHistory()
                        }
                    }

                    override fun onSlide(bottomSheet: View, slideOffset: Float) {
                        historySheet.translationX =
                            lerp(maxTranslationX, 0f, 0f, 0.15f, slideOffset)
                        sheetBackground.interpolation = lerp(1f, 0f, 0f, 0.15f, slideOffset)
                        sheetBackground.fillColor =
                            ColorStateList.valueOf(
                                lerpArgb(historyStartColor, historyEndColor, 0f, 0.3f, slideOffset)
                            )

                        ivHistorylistIcon.alpha = lerp(1f, 0f, 0f, 0.15f, slideOffset)
                        sheetExpand.alpha = lerp(1f, 0f, 0f, 0.15f, slideOffset)
                        tvHistorylistTitle.alpha = lerp(0f, 1f, 0.2f, 0.8f, slideOffset)
                        ivCollapseHistorylist.alpha = lerp(0f, 1f, 0.2f, 0.8f, slideOffset)
                        historylistTitleDivider.alpha = lerp(0f, 1f, 0.2f, 0.8f, slideOffset)
                        rvHistory.alpha = lerp(0f, 1f, 0.2f, 0.8f, slideOffset)
                        sheetExpand.visibility =
                            if (slideOffset < 0.5f) View.VISIBLE else View.GONE
                    }
                })
                historySheet.doOnApplyWindowInsets { _, insets, _, _ ->
                    behavior.peekHeight = peek + insets.systemWindowInsetBottom
                }

            } // doOnLayout

            ivCollapseHistorylist.setOnClickListener {
                behavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
            sheetExpand.setOnClickListener {
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }

            vm?.clickedQuery?.observe(viewLifecycleOwner, Observer { query ->
                (requireParentFragment() as? ContentFragment)?.loadContentsByHistoryQuery(query)
                BottomSheetBehavior.from(binding.historySheet).state = BottomSheetBehavior.STATE_COLLAPSED
            })
        } // end of bind
    }
}