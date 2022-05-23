package com.aapolis.apolisbillingsystem.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aapolis.apolisbillingsystem.data.Customer
import com.aapolis.apolisbillingsystem.data.Message
import com.aapolis.apolisbillingsystem.databinding.ViewHolderCustomerBinding
import com.aapolis.apolisbillingsystem.databinding.ViewHolderMessageBinding
import com.aapolis.apolisbillingsystem.viewholders.CustomerViewHolder
import com.aapolis.apolisbillingsystem.viewholders.MessageViewHolder
import java.lang.RuntimeException


class CustomerAdapter(val data: List<Any>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        if(viewType == VIEW_TYPE_MESSAGE) {
            val binding= ViewHolderMessageBinding.inflate(layoutInflater, parent, false)
            return MessageViewHolder(binding)
        } else if(viewType == VIEW_TYPE_CUSTOMER) {
            val binding = ViewHolderCustomerBinding.inflate(layoutInflater, parent, false)
            return CustomerViewHolder(binding)
        }
        throw RuntimeException("Unknown view type.")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is CustomerViewHolder) {
            val customer = data[position] as Customer
            holder.bind(customer)
            holder.itemView.setOnClickListener {
                if(this::customerSelectedListener.isInitialized) {
                    customerSelectedListener(customer, position)
                }
            }
        } else if(holder is MessageViewHolder) {
            holder.bind(data[position] as Message)
        }
    }

    override fun getItemCount() = data.size

    override fun getItemViewType(position: Int): Int {
        if(data[position] is Message) {
            return VIEW_TYPE_MESSAGE
        } else if(data[position] is Customer) {
            return VIEW_TYPE_CUSTOMER;
        }
        return super.getItemViewType(position)
    }

    companion object {
        const val VIEW_TYPE_CUSTOMER = 1
        const val VIEW_TYPE_MESSAGE = 2
    }

    private lateinit var customerSelectedListener: (Customer, Int) -> Unit

    fun setOnCustomerSelectedListener(listener: (Customer, Int)-> Unit) {
        this.customerSelectedListener = listener
    }

}