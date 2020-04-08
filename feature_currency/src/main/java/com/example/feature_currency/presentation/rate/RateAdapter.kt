package com.example.feature_currency.presentation.rate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_currency.R
import com.example.feature_currency.presentation.rate.model.CurrencyRateModel
import com.example.foundation.presentation.GlideApp
import com.example.foundation.presentation.viewmodel.observer
import kotlinx.android.synthetic.main.rate_item.view.*

internal class RateAdapter : RecyclerView.Adapter<RateAdapter.MyViewHolder>() {

    var rateList: MutableList<CurrencyRateModel>? by observer(mutableListOf()) {
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = rateList?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rate_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(rateList?.get(position)!!)
    }

    internal inner class MyViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: CurrencyRateModel) {
            GlideApp.with(itemView.context)
                .load(
                    String.format(
                        itemView.context.getString(R.string.image_url),
                        item.code.take(2)
                    )
                )
                .error(R.drawable.ic_image)
                .into(itemView.flagImage)

            itemView.currencyName.text = item.name
            itemView.currencyCode.text = item.code
            itemView.rate.setText(item.rate.toString())
            itemView.rate.setSelection(itemView.rate.text.length)
        }
    }
}