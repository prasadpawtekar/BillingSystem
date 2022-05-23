package com.aapolis.apolisbillingsystem.sql

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import com.aapolis.apolisbillingsystem.data.Bill
import com.aapolis.apolisbillingsystem.data.CustomerBill

class BillDao(context: Context) {
    private val db = DBHelper(context).writableDatabase

    fun createBill(bill: Bill): Long {
        val values = ContentValues()
        values.put("dateTime", bill.dateTime)
        values.put("totalAmount", bill.totalAmount)
        values.put("customer_id", bill.customerId)

        return db.insert("bill", null, values)
    }

    @SuppressLint("Range")
    fun getAllBills(): ArrayList<CustomerBill> {
        val list = ArrayList<CustomerBill>()
        val query = """
            SELECT billNo, dateTime, customer.customer_id, name, mobile_no, totalAmount
            FROM bill, customer
            WHERE bill.customer_id=customer.customer_id
        """.trimIndent()

        val cursor = db.rawQuery(query, null)
        while (cursor.moveToNext()) {
            val billNo = cursor.getLong(cursor.getColumnIndex("billNo"))
            val dateTime = cursor.getLong(cursor.getColumnIndex("dateTime"))
            val totalAmount = cursor.getFloat(cursor.getColumnIndex("totalAmount"))
            val customerId = cursor.getLong(cursor.getColumnIndex("customer_id"))
            val name = cursor.getString(cursor.getColumnIndex("name"))
            val mobileNo = cursor.getString(cursor.getColumnIndex("mobile_no"))

            val customerBill = CustomerBill(billNo, dateTime, totalAmount, customerId, name, mobileNo)
            list.add(customerBill)
        }
        return list
    }
}