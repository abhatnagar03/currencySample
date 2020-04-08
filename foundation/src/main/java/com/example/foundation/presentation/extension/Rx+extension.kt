package com.example.foundation.presentation.extension

import com.example.foundation.domain.GenericError
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

fun <Type : Any> Single<Type>.applySchedulers(): Single<Type> {
    return compose { upstream ->
        upstream.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}

fun <T> Single<T>.applyLoadingBehavior(loadingSubject: BehaviorSubject<Boolean>): Single<T> {
    return doOnSubscribe { loadingSubject.onNext(true) }
        .doAfterTerminate { loadingSubject.onNext(false) }
        .doOnDispose { loadingSubject.onNext(false) }
}

fun <T> Single<T>.applyErrorBehavior(
    networkErrorSubject: BehaviorSubject<Boolean>,
    generalErrorSubject: BehaviorSubject<Pair<Throwable?, Boolean>>
): Single<T> {
    return doOnError {
        if ((it as? GenericError)?.type == GenericError.Type.OFFLINE_OR_TIMEOUT)
            networkErrorSubject.onNext(true)
        else {
            generalErrorSubject.onNext(Pair(it, true))
        }
    }.doOnSuccess {
        networkErrorSubject.onNext(false)
        generalErrorSubject.onNext(Pair(null, false))
    }
}