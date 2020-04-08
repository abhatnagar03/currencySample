package com.example.feature_currency.data.model

data class RateDto(
    val base: String? = null,
    val currencies: MutableList<CurrencyDto>? = null
)

data class CurrencyDto(
    var name: String? = null,
    var rate: Double = 0.0
)