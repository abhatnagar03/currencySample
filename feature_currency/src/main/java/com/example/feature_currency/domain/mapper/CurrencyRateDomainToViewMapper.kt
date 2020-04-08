package com.example.feature_currency.domain.mapper

import com.example.feature_currency.domain.model.CurrencyNameModel
import com.example.feature_currency.domain.model.RateModel
import com.example.feature_currency.presentation.rate.model.CurrencyRateModel
import com.example.foundation.domain.DomainToViewMapper

class CurrencyRateDomainToViewMapper :
    DomainToViewMapper<Pair<RateModel, List<CurrencyNameModel>>, List<CurrencyRateModel>> {
    override fun transform(domainModel: Pair<RateModel, List<CurrencyNameModel>>): List<CurrencyRateModel> {
        val rates = domainModel.first.currencies
        val currencyNames = domainModel.second
        return rates.map {
            CurrencyRateModel(
                code = it.code,
                rate = it.rate,
                name = currencyNames.firstOrNull() { currency ->
                    currency.code == it.code
                }?.name ?: ""
            )
        }
    }
}