package com.sungjae.portfolio.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val disposable = CompositeDisposable()

    protected val mutableErrorMsg = MutableLiveData<Int>()
    val errorMsg: LiveData<Int> get() = mutableErrorMsg

    protected fun Disposable.addDisposable() {
        disposable.add(this)
    }

    override fun onCleared() {
        unBindViewModel()
        super.onCleared()
    }

    fun unBindViewModel() {
        disposable.clear()
    }
}