package com.example.feature_currency.domain.repository

import com.example.feature_currency.domain.model.RateModel
import io.reactivex.Single

interface RateListRepository {
    fun getRates(baseCurrency: String): Single<RateModel>
}