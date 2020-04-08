package com.example.feature_currency.presentation.rate

import androidx.fragment.app.Fragment
import com.example.foundation.presentation.di.KotlinViewModelProvider
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

val presentationModule = Kodein.Module("presentationModule") {

    bind<RateListViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        KotlinViewModelProvider.of(context) {
            RateListViewModel(
                instance()
            )
        }
    }
}
