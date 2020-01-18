package com.vikram.mynotes.ui.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BaseViewModel : ViewModel(), LifecycleObserver {
    private var compositeDisposable: CompositeDisposable? = null

    override fun onCleared() {
        super.onCleared()
        compositeDisposable?.let {
            it.clear()
            it.dispose()
        }
    }

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable?.add(disposable) ?: kotlin.run {
            compositeDisposable = CompositeDisposable()
        }
    }
}