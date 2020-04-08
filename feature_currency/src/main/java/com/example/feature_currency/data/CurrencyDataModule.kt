package com.example.feature_currency.data

import com.example.feature_currency.data.mapper.CurrencyNamesDtoToDomainMapper
import com.example.feature_currency.data.mapper.RatesDtoToDomainMapper
import com.example.feature_currency.data.repository.CurrencyNameListRepositoryImpl
import com.example.feature_currency.data.repository.RateListRepositoryImpl
import com.example.feature_currency.data.service.RetrofitService
import com.example.feature_currency.domain.repository.CurrencyNameListRepository
import com.example.feature_currency.domain.repository.RateListRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

val dataModule = Kodein.Module("dataModule") {

    bind() from singleton {
        instance<Retrofit>().create(
            RetrofitService::class.java
        )
    }

    bind<RatesDtoToDomainMapper>() with singleton { RatesDtoToDomainMapper() }

    bind<CurrencyNamesDtoToDomainMapper>() with singleton { CurrencyNamesDtoToDomainMapper() }

    bind<RateListRepository>() with singleton {
        RateListRepositoryImpl(
            api = instance(),
            mapper = instance()
        )
    }

    bind<CurrencyNameListRepository>() with singleton {
        CurrencyNameListRepositoryImpl(
            api = instance(),
            mapper = instance()
        )
    }
}
