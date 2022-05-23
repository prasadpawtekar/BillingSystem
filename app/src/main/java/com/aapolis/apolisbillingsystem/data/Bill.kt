package com.aapolis.apolisbillingsystem.data

data class Bill(
    var billNo: Long,
    val dateTime: Long,
    val totalAmount: Float,
    val customerId: Long
)
