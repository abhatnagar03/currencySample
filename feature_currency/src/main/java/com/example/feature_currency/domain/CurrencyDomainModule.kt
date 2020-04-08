package com.example.feature_currency.domain

import com.example.feature_currency.domain.usecase.LoadRateListUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val domainModule = Kodein.Module("domainModule") {

    bind() from singleton {
        LoadRateListUseCase(instance())
    }
}