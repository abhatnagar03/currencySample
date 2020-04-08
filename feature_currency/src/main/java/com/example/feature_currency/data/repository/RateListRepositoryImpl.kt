package com.example.feature_currency.data.repository

import com.example.feature_currency.data.mapper.RatesDtoToDomainMapper
import com.example.feature_currency.data.service.RetrofitService
import com.example.feature_currency.domain.model.RateModel
import com.example.feature_currency.domain.repository.RateListRepository
import io.reactivex.Observable

internal class RateListRepositoryImpl(
    private val api: RetrofitService,
    private val mapper: RatesDtoToDomainMapper
) : RateListRepository {
    override fun getRates(baseCurrency: String): Observable<RateModel> =
        api.getRates(baseCurrency)
            .map { mapper.transform(it) }
}