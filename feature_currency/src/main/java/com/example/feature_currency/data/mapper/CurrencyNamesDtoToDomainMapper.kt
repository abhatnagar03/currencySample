package com.example.feature_currency.data.mapper

import com.example.feature_currency.data.model.CurrenciesDto
import com.example.feature_currency.domain.model.CurrencyNameModel
import com.example.foundation.data.mapper.DataToDomainMapper

internal class CurrencyNamesDtoToDomainMapper :
    DataToDomainMapper<CurrenciesDto, List<CurrencyNameModel>> {
    override fun transform(dataModel: CurrenciesDto): List<CurrencyNameModel> =
        dataModel.currencies?.filter {
            it.name?.isNotEmpty() == true && it.code?.isNotEmpty() == true
        }?.map {
            CurrencyNameModel(
                name = it.name!!,
                code = it.code!!
            )
        } ?: emptyList()
}