package com.example.feature_currency.domain.model

data class RateModel(
    val base: String? = null,
    val currencies: MutableList<CurrencyModel>? = null
)

data class CurrencyModel(
    var name: String? = null,
    var rate: Double = 0.0
)