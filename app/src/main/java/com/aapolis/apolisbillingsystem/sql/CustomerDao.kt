package com.aapolis.apolisbillingsystem.sql

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import com.aapolis.apolisbillingsystem.data.Customer

class CustomerDao(context: Context) {
    private val db = DBHelper(context).writableDatabase
    public fun addCustomer(customer: Customer): Long {

        val values = ContentValues()
        values.put("name", customer.name)
        values.put("mobile_no", customer.mobileNo)
        return db.insert("customer", null, values)
    }

    @SuppressLint("Range")
    fun getCustomers(): ArrayList<Customer> {
        val customers = ArrayList<Customer>()

        val cursor = db.query("customer", null, null, null, null, null, null)

        while(cursor.moveToNext()) {
            val customerId = cursor.getLong(cursor.getColumnIndex("customer_id"))
            val name = cursor.getString(cursor.getColumnIndex("name"))
            val mobileNo = cursor.getString(cursor.getColumnIndex("mobile_no"))

            customers.add(Customer(customerId, name, mobileNo))
        }


        return customers
    }
}