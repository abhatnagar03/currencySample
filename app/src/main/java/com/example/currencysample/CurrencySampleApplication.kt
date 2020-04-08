package com.example.currencysample

import android.app.Application
import com.example.feature_currency.data.dataModule
import com.example.feature_currency.domain.domainModule
import com.example.feature_currency.presentation.presentationModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule

class CurrencySampleApplication : Application(), KodeinAware {
    override val kodein: Kodein
        get() = Kodein.lazy {
            import(androidXModule(this@CurrencySampleApplication))
            import(networkModule)
            import(dataModule)
            import(domainModule)
            import(presentationModule)
        }
}