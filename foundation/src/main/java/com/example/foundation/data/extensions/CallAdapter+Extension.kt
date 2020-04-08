package com.example.foundation.data.extensions

import com.example.foundation.domain.GenericError
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import retrofit2.Call
import retrofit2.CallAdapter
import timber.log.Timber

fun <R, T : Any> CallAdapter<R, T>.adaptErrorMapper(
      call: Call<R>,
      errorMapper: (Throwable) -> GenericError
): Any {
   return when (val result = adapt(call)) {
      is Single<*> -> result.onErrorResumeNext {
         Timber.e(it)
         Single.error(errorMapper.invoke(it))
      }
      is Completable -> result.onErrorResumeNext {
         Timber.e(it)
         Completable.error(errorMapper.invoke(it))
      }
      is Maybe<*> -> result.onErrorResumeNext { it: Throwable ->
         Timber.e(it)
         Maybe.error { errorMapper.invoke(it) }
      }
      else -> result
   }
}