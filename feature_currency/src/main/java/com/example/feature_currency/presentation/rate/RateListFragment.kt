package com.example.feature_currency.presentation.rate

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feature_currency.R
import com.example.foundation.presentation.fragment.BaseContainerFragment
import kotlinx.android.synthetic.main.fragment_rates.*
import org.kodein.di.generic.instance

class RateListFragment : BaseContainerFragment() {

    override val layoutResourceId = R.layout.fragment_rates

    private val viewModel: RateListViewModel by instance()
    private val rateAdapter: RateAdapter by instance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = rateAdapter
        }

        rateAdapter.setOnDebouncedClickListener {
            viewModel.setBaseCurrencyRate(it)
            recyclerView.scrollToPosition(0)
        }

        rateAdapter.setOnRateChangedListener {
            viewModel.setChangedCurrencyRate(it)
        }

        disposables.add(viewModel.observeSuccess().subscribe {
            rateAdapter.currencyRateList = it.toMutableList()
        })
    }
}