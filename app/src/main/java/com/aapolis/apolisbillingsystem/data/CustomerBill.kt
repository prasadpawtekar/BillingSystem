package com.aapolis.apolisbillingsystem.data

data class CustomerBill(
    val billNo: Long,
    val dateTime: Long,
    val totalAmount: Float,
    val customerId: Long,
    val name: String,
    val mobileNo: String
)
