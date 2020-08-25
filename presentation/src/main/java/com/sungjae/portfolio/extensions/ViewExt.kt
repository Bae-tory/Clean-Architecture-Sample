package com.sungjae.portfolio.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.sungjae.portfolio.R
import com.sungjae.portfolio.components.ThrottleFirstClickListener
import kotlinx.coroutines.*

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

@BindingAdapter("onThrottleClick")
fun View.setOnThrottleClickListener(listener: View.OnClickListener) {
    setOnClickListener(ThrottleFirstClickListener {
        it.run(listener::onClick)
    }
    )
//    setOnClickListener(throttleFirst(destinationFunction = listener::onClick))
}

fun <T> throttleFirst(
    skipMs: Long = 500L,
    coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main),
    invokedFunc: (T) -> Unit

): (T) -> Unit {
    var throttleJob: Job? = null
    return { param: T ->
        if (throttleJob?.isCompleted != false) {
            throttleJob = coroutineScope.launch {
                invokedFunc(param)
                delay(skipMs)
            }
        }
    }
}

fun <T> throttleLatest(
    intervalMs: Long = 500L,
    coroutineScope: CoroutineScope,
    invokedFunc: (T) -> Unit
): (T) -> Unit {
    var throttleJob: Job? = null
    var latestParam: T
    return { param: T ->
        latestParam = param
        if (throttleJob?.isCompleted != false) {
            throttleJob = coroutineScope.launch {
                delay(intervalMs)
//                invokedFunc(latestParam) 도 사용가능
                latestParam.let(invokedFunc)
            }
        }
    }
}

fun <T> debounce(
    waitMs: Long = 500L,
    coroutineScope: CoroutineScope,
    invokedFunc: (T) -> Unit
): (T) -> Unit {
    var debounceJob: Job? = null
    return { param: T ->
        debounceJob?.cancel()
        debounceJob = coroutineScope.launch {
            delay(waitMs)
            invokedFunc(param)
        }
    }
}