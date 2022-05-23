package com.aapolis.apolisbillingsystem.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aapolis.apolisbillingsystem.data.Sell
import com.aapolis.apolisbillingsystem.databinding.ViewHolderSalesDetailsBinding
import com.aapolis.apolisbillingsystem.viewholders.SaleDetailsViewHolder

class SaleDetailsAdapter(val sellItems: ArrayList<Sell>): RecyclerView.Adapter<SaleDetailsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaleDetailsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderSalesDetailsBinding.inflate(layoutInflater, parent, false)
        return SaleDetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SaleDetailsViewHolder, position: Int) {
        holder.bind(sellItems[position])
        holder.binding.ivAdd.setOnClickListener {
            if(this::qtyIncrementedListener.isInitialized) {
                qtyIncrementedListener(sellItems[position], position)
            }
        }

        holder.binding.ivMinus.setOnClickListener {
            if(this::qtyDecrementedListener.isInitialized) {
                qtyDecrementedListener(sellItems[position], position)
            }
        }
    }

    override fun getItemCount() = sellItems.size

    private lateinit var qtyDecrementedListener: (Sell, Int) -> Unit
    private lateinit var qtyIncrementedListener: (Sell, Int) -> Unit


    fun setOnQtyDecrementedListener(listener: (Sell, Int)-> Unit) {
        qtyDecrementedListener = listener
    }

    fun setOnQtyIncrementedListener(listener: (Sell, Int) -> Unit) {
        qtyIncrementedListener = listener
    }
}