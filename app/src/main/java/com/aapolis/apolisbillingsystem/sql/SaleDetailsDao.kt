package com.aapolis.apolisbillingsystem.sql

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteException
import com.aapolis.apolisbillingsystem.data.SaleDatails

class SaleDetailsDao(context: Context) {
    private val db = DBHelper(context).writableDatabase

    fun addSaleDetails(sales: ArrayList<SaleDatails>): Boolean {
        try {
            db.beginTransaction()

            sales.forEach {
                val values = ContentValues()
                values.put("billNo", it.billNo)
                values.put("customer_id", it.customerId)
                values.put("productId", it.productId)
                values.put("quantity", it.quantity)
                values.put("rate", it.rate)
                values.put("amount", it.amount)

                val id: Long = db.insert("sales_details", null, values)
                if(id == 0L) {
                    // this means that transaction is failed in between
                    db.endTransaction()  // rollback. db.endTransaction() without db.setTransactionSuccessful() is considered as rollback
                    return false
                }
            }
            // If control of execution reaches this point, it means all insert operations are successful
            // Hence we will commit transaction
            // Next two lines combinely indicates commit operation
            db.setTransactionSuccessful()
            db.endTransaction()
            return true
        } catch (e: SQLiteException) {
            // This means that transaction is failed
            // Hence rollback transaction
            db.endTransaction()
        }

        return false
    }
}