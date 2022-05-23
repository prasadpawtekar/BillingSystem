package com.aapolis.apolisbillingsystem.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.aapolis.apolisbillingsystem.data.Customer
import com.aapolis.apolisbillingsystem.databinding.ViewHolderCustomerBinding

class CustomerViewHolder(val binding: ViewHolderCustomerBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(customer: Customer) {
        binding.tvCustomerName.text = customer.name
        binding.tvMobileNo.text = customer.mobileNo
    }
}
