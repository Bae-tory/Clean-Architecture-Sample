package com.sungjae.portfolio.components

import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

typealias ClickListener = (View) -> Unit

class ThrottleFirstClickListener(
    private val listener: ClickListener
) : View.OnClickListener {

    private val disposable = CompositeDisposable()
    private val onClickListenerBehaviorSubject = PublishSubject.create<View>()

    init {
        onClickListenerBehaviorSubject.throttleFirst(THROTTLE_DURATION, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { it.run(listener) }.addTo(disposable)
    }

    override fun onClick(view: View) {
        onClickListenerBehaviorSubject.onNext(view)
    }

    companion object {
        private const val THROTTLE_DURATION = 1000L
    }
}