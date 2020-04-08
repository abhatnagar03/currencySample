package com.example.foundation.presentation.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject

open class BaseViewModel<T> : ViewModel(), ViewStates<T> {
    val disposable = CompositeDisposable()

    protected val success = BehaviorSubject.create<T>()
    protected val error = BehaviorSubject.create<Pair<Throwable?, Boolean>>()
    protected val networkError = BehaviorSubject.create<Boolean>()
    protected val isLoading = BehaviorSubject.create<Boolean>()

    override fun observeError(): Observable<Pair<Throwable?, Boolean>> = error

    override fun observeSuccess(): Observable<T> = success

    override fun observeLoading(): Observable<Boolean> = isLoading

    override fun observeNetworkError(): Observable<Boolean> = networkError

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}