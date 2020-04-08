package com.example.feature_currency.domain.repository

import com.example.feature_currency.domain.model.RateModel
import io.reactivex.Observable

interface RateListRepository {
    fun getRates(baseCurrency: String): Observable<RateModel>
}