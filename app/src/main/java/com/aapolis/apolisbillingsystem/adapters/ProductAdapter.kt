package com.aapolis.apolisbillingsystem.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aapolis.apolisbillingsystem.data.Customer
import com.aapolis.apolisbillingsystem.data.Message
import com.aapolis.apolisbillingsystem.data.Product
import com.aapolis.apolisbillingsystem.databinding.ViewHolderCustomerBinding
import com.aapolis.apolisbillingsystem.databinding.ViewHolderMessageBinding
import com.aapolis.apolisbillingsystem.databinding.ViewHolderProductBinding
import com.aapolis.apolisbillingsystem.viewholders.CustomerViewHolder
import com.aapolis.apolisbillingsystem.viewholders.MessageViewHolder
import com.aapolis.apolisbillingsystem.viewholders.ProductViewHolder
import java.lang.RuntimeException


class ProductAdapter(val data: List<Any>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        if(viewType == VIEW_TYPE_MESSAGE) {
            val binding= ViewHolderMessageBinding.inflate(layoutInflater, parent, false)
            return MessageViewHolder(binding)
        } else if(viewType == VIEW_TYPE_PRODUCT) {
            val binding = ViewHolderProductBinding.inflate(layoutInflater, parent, false)
            return ProductViewHolder(binding)
        }
        throw RuntimeException("Unknown view type.")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ProductViewHolder) {
            val product = data[position] as Product
            holder.bind(product)

            holder.itemView.setOnClickListener {
                if(this::productSelectedListener.isInitialized) {
                    productSelectedListener(product, position)
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
        } else if(data[position] is Product) {
            return VIEW_TYPE_PRODUCT;
        }
        return super.getItemViewType(position)
    }

    companion object {
        const val VIEW_TYPE_PRODUCT = 1
        const val VIEW_TYPE_MESSAGE = 2
    }

    private lateinit var productSelectedListener: (Product, Int) -> Unit

    fun setOnProductSelectedListener(listner: (Product, Int) -> Unit) {
        productSelectedListener = listner
    }
}