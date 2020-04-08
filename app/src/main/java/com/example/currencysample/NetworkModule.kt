package com.example.currencysample

import com.example.feature_currency.data.model.RateDeserializer
import com.example.feature_currency.data.model.RateDto
import com.example.foundation.data.adapter.GenericErrorMapper
import com.example.foundation.data.adapter.RxErrorMapperCallAdapterFactory
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = Kodein.Module("networkModule") {

    bind<HttpLoggingInterceptor>() with singleton {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    bind<Retrofit.Builder>() with provider { Retrofit.Builder() }

    bind<OkHttpClient.Builder>() with singleton { OkHttpClient.Builder() }

    bind<OkHttpClient>() with provider {
        instance<OkHttpClient.Builder>()
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor(instance<HttpLoggingInterceptor>())
            .build()
    }

    bind<GenericErrorMapper>() with singleton { GenericErrorMapper() }

    bind<RxErrorMapperCallAdapterFactory>() with singleton {
        RxErrorMapperCallAdapterFactory.createAsync(
            instance()
        )
    }

    bind<Retrofit>() with provider {
        instance<Retrofit.Builder>()
            .baseUrl("https://hiring.revolut.codes/api/android/")
            .addConverterFactory(GsonConverterFactory.create(instance()))
            .addCallAdapterFactory(instance())
            .client(instance())
            .build()
    }

    bind<Gson>() with singleton {
        GsonBuilder()
            .registerTypeAdapter(RateDto::class.java, RateDeserializer())
            .create()
    }
}