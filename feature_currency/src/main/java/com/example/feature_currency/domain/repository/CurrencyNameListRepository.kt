package com.example.feature_currency.domain.repository

import com.example.feature_currency.domain.model.CurrencyNameModel
import io.reactivex.Observable

interface CurrencyNameListRepository {
    fun getCurrencyNames(): Observable<List<CurrencyNameModel>>
}