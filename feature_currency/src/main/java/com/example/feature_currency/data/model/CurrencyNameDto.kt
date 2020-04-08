package com.example.feature_currency.data.model

data class CurrenciesDto(
    var currencies: List<CurrencyNameDto>? = null
)

data class CurrencyNameDto(
    var code: String? = null,
    var name: String? = null
)