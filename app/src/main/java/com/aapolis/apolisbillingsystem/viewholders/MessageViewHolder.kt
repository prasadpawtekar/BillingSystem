package com.aapolis.apolisbillingsystem.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.aapolis.apolisbillingsystem.data.Message
import com.aapolis.apolisbillingsystem.databinding.ViewHolderMessageBinding

class MessageViewHolder(val binding: ViewHolderMessageBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(message: Message) {
        binding.tvMessage.text = message.message
    }
}