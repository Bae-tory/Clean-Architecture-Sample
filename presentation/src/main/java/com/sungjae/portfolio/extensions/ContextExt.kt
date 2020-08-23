package com.sungjae.portfolio.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

fun Context.startActivity(targetActivity: Class<out Any>) {
    val i = Intent(this, targetActivity)
    startActivity(i)
}

fun Context.startActivityWithData(targetActivity: Class<out Any>, block: Intent.() -> Unit) {
    val i = Intent(this, targetActivity)
    block(i)
    startActivity(i)
}

fun Context.resetActivity(targetActivity: Class<out Any>, block: Intent.() -> Unit) {
    val i = Intent(this, targetActivity)
    block(i)
    startActivity(i)
    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
}

fun Context.density(): Float {
    return resources.displayMetrics.density
}

fun Context.hideKeyboard(view: View? = null) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
}

fun Context.toast(message: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, duration).show()
}