package com.example.foundation.presentation.viewmodel

import io.reactivex.Observable

interface ViewStates<T> {
    fun observeError(): Observable<Pair<Throwable?, Boolean>>
    fun observeSuccess(): Observable<T>
    fun observeLoading(): Observable<Boolean>
    fun observeNetworkError(): Observable<Boolean>
}