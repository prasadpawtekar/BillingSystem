package com.aapolis.apolisbillingsystem.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.aapolis.apolisbillingsystem.data.Customer
import com.aapolis.apolisbillingsystem.data.Product
import com.aapolis.apolisbillingsystem.databinding.ViewHolderCustomerBinding
import com.aapolis.apolisbillingsystem.databinding.ViewHolderProductBinding

class ProductViewHolder(val binding: ViewHolderProductBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(product: Product) {
        binding.tvProductName.text = product.name
        binding.tvPrice.text = "Price = ${product.unit_price}"

    }
}