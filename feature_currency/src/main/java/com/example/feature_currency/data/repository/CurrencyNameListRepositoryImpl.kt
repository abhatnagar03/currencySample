package com.example.feature_currency.data.repository

import com.example.feature_currency.data.mapper.CurrencyNamesDtoToDomainMapper
import com.example.feature_currency.data.service.RetrofitService
import com.example.feature_currency.domain.model.CurrencyNameModel
import com.example.feature_currency.domain.repository.CurrencyNameListRepository
import io.reactivex.Observable

internal class CurrencyNameListRepositoryImpl(
    private val api: RetrofitService,
    private val mapper: CurrencyNamesDtoToDomainMapper
) : CurrencyNameListRepository {

    override fun getCurrencyNames(): Observable<List<CurrencyNameModel>> =
        api.getCurrencies()
            .map { mapper.transform(it) }
}