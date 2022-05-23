package com.aapolis.apolisbillingsystem.data

data class Sell(
    val productId: Long,
    val productName: String,
    val price: Float,
    var quantity: Int
)
