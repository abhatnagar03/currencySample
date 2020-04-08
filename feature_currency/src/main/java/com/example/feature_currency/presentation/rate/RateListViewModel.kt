package com.example.feature_currency.presentation.rate

import com.example.feature_currency.domain.usecase.LoadCurrencyRateListUseCase
import com.example.feature_currency.presentation.rate.model.CurrencyRateModel
import com.example.foundation.presentation.extension.applyErrorBehavior
import com.example.foundation.presentation.extension.applyLoadingBehavior
import com.example.foundation.presentation.extension.applySchedulers
import com.example.foundation.presentation.viewmodel.BaseViewModel
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber
import java.util.concurrent.TimeUnit

internal class RateListViewModel(
    private val loadCurrencyRateListUseCase: LoadCurrencyRateListUseCase
) : BaseViewModel<List<CurrencyRateModel>>() {

    private var defaultCurrencyRate = CurrencyRateModel("EUR", 1.0, "Euro")
    private var prevDisposable: Disposable? = null

    private var baseCurrencySubject = BehaviorSubject.create<CurrencyRateModel>()
    private var changedCurrencySubject = BehaviorSubject.create<CurrencyRateModel>()

    init {
        baseCurrencySubject.doOnNext {
            defaultCurrencyRate = CurrencyRateModel(it.code, 1.0, it.name)
            prevDisposable?.dispose()
            loadSites()
        }.subscribe()

        changedCurrencySubject.doOnNext {
            defaultCurrencyRate = CurrencyRateModel(it.code, it.rate, it.name)
            prevDisposable?.dispose()
            loadSites()
        }.subscribe()

        baseCurrencySubject.onNext(defaultCurrencyRate)
    }

    internal fun setBaseCurrencyRate(model: CurrencyRateModel) {
        baseCurrencySubject.onNext(model)
    }

    fun setChangedCurrencyRate(it: CurrencyRateModel) {
        changedCurrencySubject.onNext(it)
    }

    private fun loadSites() {
        disposable.add(
            Observable.interval(0, 1, TimeUnit.SECONDS)
                .flatMap {
                    return@flatMap loadCurrencyRateListUseCase.execute(defaultCurrencyRate.code)
                }
                .applySchedulers()
                .applyLoadingBehavior(isLoading)
                .applyErrorBehavior(networkError, error)
                .subscribe({
                    it.add(0, defaultCurrencyRate)
                    success.onNext(it)
                }, {
                    Timber.e(it, "loading currency rate list failed")
                }).apply {
                    prevDisposable = this
                }
        )
    }
}