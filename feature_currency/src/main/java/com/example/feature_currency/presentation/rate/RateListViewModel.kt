package com.example.feature_currency.presentation.rate

import com.example.feature_currency.domain.model.RateModel
import com.example.feature_currency.domain.usecase.LoadRateListUseCase
import com.example.foundation.presentation.extension.applyErrorBehavior
import com.example.foundation.presentation.extension.applyLoadingBehavior
import com.example.foundation.presentation.extension.applySchedulers
import com.example.foundation.presentation.viewmodel.BaseViewModel
import io.reactivex.Observable
import timber.log.Timber
import java.util.concurrent.TimeUnit

internal class RateListViewModel(
    private val loadRateListUseCase: LoadRateListUseCase
) : BaseViewModel<RateModel>() {

    init {
        loadSites("EUR")
    }

    internal fun loadSites(baseCurrency: String) {
        disposable.add(
            Observable.interval(0, 1, TimeUnit.SECONDS)
                .flatMap {
                    return@flatMap loadRateListUseCase.execute(baseCurrency)
                }
                .applySchedulers()
                .applyLoadingBehavior(isLoading)
                .applyErrorBehavior(networkError, error)
                .subscribe({
                    success.onNext(it)
                }, {
                    Timber.e(it, "loading rate list failed")
                })
        )
    }
}