package com.example.feature_currency.presentation.rate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_currency.R
import com.example.feature_currency.domain.model.CurrencyModel
import com.example.foundation.presentation.extension.setOnDebouncedClickListener
import com.example.foundation.presentation.viewmodel.observer
import kotlinx.android.synthetic.main.rate_item.view.*

internal class RateAdapter : RecyclerView.Adapter<RateAdapter.MyViewHolder>() {

    var rateList: MutableList<CurrencyModel>? by observer(mutableListOf()) {
        notifyDataSetChanged()
    }

    private var onDebouncedClickListener: ((item: CurrencyModel) -> Unit)? = null

    override fun getItemCount(): Int = rateList?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rate_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(rateList?.get(position)!!)
    }

    fun setOnDebouncedClickListener(listener: (item: CurrencyModel) -> Unit) {
        this.onDebouncedClickListener = listener
    }

    internal inner class MyViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: CurrencyModel) {
            itemView.rate.text = item.rate.toString()
            itemView.setOnDebouncedClickListener {
                onDebouncedClickListener?.invoke(
                    item
                )
            }
        }
    }
}