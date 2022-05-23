package com.aapolis.apolisbillingsystem.data

data class SaleDatails(
    val sid: Long,
    val billNo: Long,
    val customerId: Long,
    val productId: Long,
    val quantity: Int,
    val rate: Float,
    val amount: Float
)
