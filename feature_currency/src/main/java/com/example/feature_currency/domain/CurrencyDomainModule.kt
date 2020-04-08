package com.example.feature_currency.domain

import com.example.feature_currency.domain.mapper.CurrencyRateDomainToViewMapper
import com.example.feature_currency.domain.usecase.LoadCurrencyRateListUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val domainModule = Kodein.Module("domainModule") {

    bind() from singleton {
        LoadCurrencyRateListUseCase(instance(), instance(), instance())
    }

    bind<CurrencyRateDomainToViewMapper>() with singleton { CurrencyRateDomainToViewMapper() }
}