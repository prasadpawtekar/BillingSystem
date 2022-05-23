package com.aapolis.apolisbillingsystem.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.aapolis.apolisbillingsystem.data.Sell
import com.aapolis.apolisbillingsystem.databinding.ViewHolderProductBinding
import com.aapolis.apolisbillingsystem.databinding.ViewHolderSalesDetailsBinding

class SaleDetailsViewHolder(val binding: ViewHolderSalesDetailsBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(sell: Sell) {
        binding.tvProductName.text = sell.productName
        binding.tvQtyu.text = "${sell.quantity}"
        binding.tvPrice.text = "${sell.price}"
        binding.tvAmount.text = "\$ ${sell.price*sell.quantity}"

    }
}