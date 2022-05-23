package com.aapolis.apolisbillingsystem.sql

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import com.aapolis.apolisbillingsystem.data.Product

class ProductDao(context: Context) {
    private val db = DBHelper(context).writableDatabase

    public fun addProduct(product: Product): Long {
        val values = ContentValues()
        values.put("name", product.name)
        values.put("unit_price", product.unit_price)

        return db.insert("product", null, values)
    }

    @SuppressLint("Range")
    public fun getProducts(): ArrayList<Product> {
        val products = ArrayList<Product>()
        val cursor = db.query("product", null, null, null, null, null, null)
        while(cursor.moveToNext()) {
            val id = cursor.getLong(cursor.getColumnIndex("product_id"))
            val name = cursor.getString(cursor.getColumnIndex("name"))
            val unit_price = cursor.getFloat(cursor.getColumnIndex("unit_price"))

            val product = Product(id, name, unit_price)

            products.add(product)
        }
        return products
    }

}