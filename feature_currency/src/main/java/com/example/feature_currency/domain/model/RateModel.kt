package com.example.feature_currency.domain.model

data class RateModel(
    val base: String,
    val currencies: MutableList<CurrencyModel>
)

data class CurrencyModel(
    var code: String,
    var rate: Double
)