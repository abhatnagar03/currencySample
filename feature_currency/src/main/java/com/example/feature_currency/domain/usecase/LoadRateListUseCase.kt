package com.example.feature_currency.domain.usecase

import com.example.feature_currency.domain.repository.RateListRepository

internal class LoadRateListUseCase(
    private val rateListRepository: RateListRepository
) {
    fun execute(baseCurrency: String) =
        rateListRepository.getRates(baseCurrency)
}