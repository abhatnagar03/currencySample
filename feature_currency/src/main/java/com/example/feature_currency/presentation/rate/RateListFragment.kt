package com.example.feature_currency.presentation.rate

import android.os.Bundle
import android.view.View
import com.example.feature_currency.R
import com.example.foundation.presentation.fragment.BaseContainerFragment
import org.kodein.di.generic.instance
import timber.log.Timber

class RateListFragment : BaseContainerFragment() {

    override val layoutResourceId = R.layout.fragment_rates

    private val viewModel: RateListViewModel by instance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        disposables.add(viewModel.observeSuccess().subscribe {
            Timber.d("HELLO ${it.base}")
        })
    }
}