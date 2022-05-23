package com.aapolis.apolisbillingsystem.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.aapolis.apolisbillingsystem.R
import com.aapolis.apolisbillingsystem.adapters.ProductAdapter
import com.aapolis.apolisbillingsystem.data.Message
import com.aapolis.apolisbillingsystem.databinding.ActivityProductsBinding
import com.aapolis.apolisbillingsystem.sql.ProductDao

class ProductsActivity : AppCompatActivity() {

    lateinit var binding: ActivityProductsBinding
    lateinit var productDao: ProductDao
    lateinit var adapter: ProductAdapter
    val dataList = ArrayList<Any>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        productDao = ProductDao(baseContext)
        binding.rvProducts.layoutManager = LinearLayoutManager(baseContext)
        val list = productDao.getProducts()

        if(list.size == 0) {
            dataList.add(Message("No product added yet."))
        } else {
            dataList.addAll(list)
        }

        adapter = ProductAdapter(dataList)
        binding.rvProducts.adapter = adapter

        adapter.setOnProductSelectedListener { product, position ->
            val resultIntent = Intent()

            resultIntent.putExtra("product", product)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}