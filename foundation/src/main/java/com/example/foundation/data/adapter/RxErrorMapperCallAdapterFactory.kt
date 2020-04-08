package com.example.foundation.data.adapter

import com.example.foundation.data.extensions.adaptErrorMapper
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.lang.reflect.Type

class RxErrorMapperCallAdapterFactory private constructor(
      private val errorMapper: GenericErrorMapper
) : CallAdapter.Factory() {

   companion object {
      fun createAsync(errorMapper: GenericErrorMapper) = RxErrorMapperCallAdapterFactory(errorMapper)
   }

   private val original: RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.createAsync()

   override fun get(
         returnType: Type,
         annotations: Array<Annotation>,
         retrofit: Retrofit
   ): CallAdapter<*, *>? = RxCallAdapterWrapper(
         original.get(returnType, annotations, retrofit) as CallAdapter<out Any, *>,
         errorMapper
   )

   private class RxCallAdapterWrapper<R> internal constructor(
         private val callAdapter: CallAdapter<R, *>,
         private val errorMapper: GenericErrorMapper
   ) : CallAdapter<R, Any> {

      override fun adapt(call: Call<R>) = callAdapter.adaptErrorMapper(call) {
         errorMapper.transform(it)
      }

      override fun responseType(): Type = callAdapter.responseType()
   }
}
