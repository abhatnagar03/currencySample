package com.example.feature_currency.presentation.rate

import com.example.feature_currency.domain.model.RateModel
import com.example.feature_currency.domain.usecase.LoadRateListUseCase
import com.example.foundation.presentation.extension.applyErrorBehavior
import com.example.foundation.presentation.extension.applyLoadingBehavior
import com.example.foundation.presentation.extension.applySchedulers
import com.example.foundation.presentation.viewmodel.BaseViewModel
import timber.log.Timber

internal class RateListViewModel(
    private val loadRateListUseCase: LoadRateListUseCase
) : BaseViewModel<RateModel>() {

    init {
        loadSites("EUR")
    }

    internal fun loadSites(baseCurrency: String) {
        disposable.add(
            loadRateListUseCase.execute(baseCurrency)
                .applySchedulers()
                .applyLoadingBehavior(isLoading)
                .applyErrorBehavior(networkError, error)
                .subscribe({
                    success.onNext(it)
                }, {
                    Timber.e(it, "loading site list failed")
                })
        )
    }
}