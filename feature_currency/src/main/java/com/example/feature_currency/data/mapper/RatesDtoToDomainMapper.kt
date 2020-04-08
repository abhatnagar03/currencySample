package com.example.feature_currency.data.mapper

import com.example.feature_currency.data.model.CurrencyDto
import com.example.feature_currency.data.model.RateDto
import com.example.feature_currency.domain.model.CurrencyModel
import com.example.feature_currency.domain.model.RateModel
import com.example.foundation.data.mapper.DataToDomainMapper

internal class RatesDtoToDomainMapper : DataToDomainMapper<RateDto, RateModel> {
    override fun transform(dataModel: RateDto): RateModel =
        RateModel(
            base = dataModel.base,
            currencies = transformCurrencies(dataModel.currencies)
        )

    private fun transformCurrencies(model: MutableList<CurrencyDto>?): MutableList<CurrencyModel>? =
        model?.map {
            CurrencyModel(
                name = it.name,
                rate = it.rate
            )
        }?.toMutableList()
}