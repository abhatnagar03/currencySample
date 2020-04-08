package com.example.feature_currency.presentation.rate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_currency.R
import com.example.feature_currency.presentation.rate.model.CurrencyRateModel
import com.example.foundation.presentation.GlideApp
import com.example.foundation.presentation.viewmodel.observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.rate_item.view.*

internal class RateAdapter : RecyclerView.Adapter<RateAdapter.MyViewHolder>() {

    private var editedAmountDisposable: Disposable? = null

    var currencyRateList: MutableList<CurrencyRateModel>? by observer(mutableListOf()) {
        notifyDataSetChanged()
    }

    private var onDebouncedClickListener: ((item: CurrencyRateModel) -> Unit)? = null
    private var onRateChangedListener: ((item: CurrencyRateModel) -> Unit)? = null

    override fun getItemCount(): Int = currencyRateList?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rate_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(position)
    }

    fun setOnDebouncedClickListener(listener: (item: CurrencyRateModel) -> Unit) {
        this.onDebouncedClickListener = listener
    }

    fun setOnRateChangedListener(listener: (item: CurrencyRateModel) -> Unit) {
        this.onRateChangedListener = listener
    }

    internal inner class MyViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(position: Int) {
            val currentItem = currencyRateList?.get(position)!!
            GlideApp.with(itemView.context)
                .load(
                    String.format(
                        itemView.context.getString(R.string.image_url),
                        currentItem.code.take(2)
                    )
                )
                .error(R.drawable.ic_image)
                .into(itemView.flagImage)

            itemView.currencyName.text = currentItem.name
            itemView.currencyCode.text = currentItem.code
            itemView.rate.setText(currentItem.rate.toString())
            itemView.rate.setSelection(itemView.rate.text.length)

            if (position == 0) {
                itemView.rate.doOnTextChanged { text, _, _, _ ->
                    val changeValue = text.toString()
                    changeValue.toDoubleOrNull()?.let {
                        onRateChangedListener?.invoke(
                            CurrencyRateModel(
                                name = currentItem.name,
                                code = currentItem.code,
                                rate = it
                            )
                        )
                    }
                }
            }

            itemView.rate.setOnTouchListener { _, _ ->
                editedAmountDisposable?.dispose()
                editedAmountDisposable = null
                onDebouncedClickListener?.invoke(
                    currentItem
                )
                false
            }
        }
    }
}