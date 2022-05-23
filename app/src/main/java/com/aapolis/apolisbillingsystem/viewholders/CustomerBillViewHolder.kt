package com.aapolis.apolisbillingsystem.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.aapolis.apolisbillingsystem.data.CustomerBill
import com.aapolis.apolisbillingsystem.databinding.ViewHolderCustomerBillBinding
import java.text.SimpleDateFormat
import java.util.*

class CustomerBillViewHolder(val binding: ViewHolderCustomerBillBinding) :
    RecyclerView.ViewHolder(binding.root) {

        fun bind(customerBill: CustomerBill) {
            binding.tvBillNo.text = "${customerBill.billNo}"
            val sdf = SimpleDateFormat("d MMM, yyyy h:m a")
            val dateTime = sdf.format(Date(customerBill.dateTime))

            binding.tvDateTime.text = dateTime
            binding.tvTotalAmount.text = "\$ ${customerBill.totalAmount}"

            binding.tvName.text = customerBill.name
            binding.tvCustomerId.text = "${customerBill.customerId}"

        }
}