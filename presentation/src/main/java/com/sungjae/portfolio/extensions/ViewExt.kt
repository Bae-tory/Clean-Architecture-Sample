package com.sungjae.portfolio.extensions

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.sungjae.portfolio.R

fun View.setTextColor(color: Int?) {
    color?.let {
        when (this) {
            is TextView -> setTextColor(ContextCompat.getColor(context, it))
            is Button -> setTextColor(ContextCompat.getColor(context, it))
        }
    }
}

fun View.setBgColor(color: Int?) {
    color?.let { setBackgroundColor(ContextCompat.getColor(context, it)) }

}

fun View.setBgDrawable(resourceId: Int?) {
    resourceId?.let { background = ContextCompat.getDrawable(context, it) }
}

fun View.setBgTint(colorCode: String?) {
    colorCode?.let { backgroundTintList = ColorStateList.valueOf(Color.parseColor("#$it")) }
}


class InitialPadding(val left: Int, val top: Int, val right: Int, val bottom: Int)
class InitialMargin(val left: Int, val top: Int, val right: Int, val bottom: Int)


@BindingAdapter("hideKeyboard")
fun View.hideKeyboard(value: Any? = null) {
    setOnClickListener {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}

fun View.doOnApplyWindowInsets(
    block: (View, WindowInsets, InitialPadding, InitialMargin) -> Unit
) {
    val initialPadding = recordInitialPaddingForView(this)
    val initialMargin = recordInitialMarginForView(this)

    setOnApplyWindowInsetsListener { v, insets ->
        block(v, insets, initialPadding, initialMargin)
        insets
    }
    requestApplyInsetsWhenAttached()
}


private fun recordInitialPaddingForView(view: View) = InitialPadding(
    view.paddingLeft, view.paddingTop, view.paddingRight, view.paddingBottom
)

private fun recordInitialMarginForView(view: View): InitialMargin {
    val lp = view.layoutParams as? ViewGroup.MarginLayoutParams
        ?: throw IllegalArgumentException(view.context.getString(R.string.layout_error_params))
    return InitialMargin(lp.leftMargin, lp.topMargin, lp.rightMargin, lp.bottomMargin)
}

fun View.requestApplyInsetsWhenAttached() {
    if (isAttachedToWindow) {
        requestApplyInsets()
    } else {
        addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                v.removeOnAttachStateChangeListener(this)
                v.requestApplyInsets()
            }

            override fun onViewDetachedFromWindow(v: View) = Unit
        })
    }
}

@BindingAdapter("onClick")
fun View.onClick(url: String) {
    setOnClickListener {
        url.let { url ->
            ContextCompat.startActivity(
                context,
                Intent(
                    Intent.ACTION_VIEW, Uri.parse(url)
                ),
                null
            )
        }
    }
}