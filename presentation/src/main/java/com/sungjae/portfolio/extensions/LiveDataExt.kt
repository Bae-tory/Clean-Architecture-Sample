package com.sungjae.portfolio.extensions

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.sungjae.portfolio.components.Constants.DEBOUNCE_DURATION

fun <T> LiveData<T>.debounce(duration: Long = DEBOUNCE_DURATION) = MediatorLiveData<T>().also { mld ->
    val source = this
    val handler = Handler(Looper.getMainLooper())

    val runnable = Runnable {
        mld.value = source.value
    }

    mld.addSource(source) {
        handler.removeCallbacks(runnable)
        handler.postDelayed(runnable, duration)
    }
}

fun <T> MutableLiveData<T>.createDefault(defaultValue: T): MutableLiveData<T> {
    value = defaultValue
    return this
}

fun <T> MutableLiveData<T>.notifyObserver() {
    this.value = this.value
}

