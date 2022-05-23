package com.aapolis.apolisbillingsystem.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aapolis.apolisbillingsystem.data.CustomerBill
import com.aapolis.apolisbillingsystem.databinding.ViewHolderCustomerBillBinding
import com.aapolis.apolisbillingsystem.viewholders.CustomerBillViewHolder

class CustomerBillAdapter(val list: List<CustomerBill>):
RecyclerView.Adapter<CustomerBillViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerBillViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderCustomerBillBinding.inflate(layoutInflater, parent, false)
        return CustomerBillViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomerBillViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}