package com.sungjae.portfolio.extensions

import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("setTextHtml")
fun TextView.setTextHtml(text: String?) {
    Log.d("setTextHtml",text.toString())
    setText(text?.fromHtml())
}