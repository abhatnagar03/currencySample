package com.example.feature_currency.domain.usecase

import com.example.feature_currency.domain.mapper.CurrencyRateDomainToViewMapper
import com.example.feature_currency.domain.model.CurrencyNameModel
import com.example.feature_currency.domain.model.RateModel
import com.example.feature_currency.domain.repository.CurrencyNameListRepository
import com.example.feature_currency.domain.repository.RateListRepository
import com.example.feature_currency.presentation.rate.model.CurrencyRateModel
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

internal class LoadCurrencyRateListUseCase(
    private val rateListRepository: RateListRepository,
    private val currencyNameListRepository: CurrencyNameListRepository,
    private val mapper: CurrencyRateDomainToViewMapper
) {
    fun execute(baseCurrency: String): Observable<List<CurrencyRateModel>> =
        Observable.zip(
            rateListRepository.getRates(baseCurrency),
            currencyNameListRepository.getCurrencyNames(),
            BiFunction { rates: RateModel, currencies: List<CurrencyNameModel> ->
                mapper.transform(Pair(rates, currencies))
            }
        )
}