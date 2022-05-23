package com.aapolis.apolisbillingsystem.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import com.aapolis.apolisbillingsystem.R
import com.aapolis.apolisbillingsystem.data.Product
import com.aapolis.apolisbillingsystem.databinding.ActivityAddProductBinding
import com.aapolis.apolisbillingsystem.sql.ProductDao

class AddProductActivity : BaseActivity() {
    lateinit var binding: ActivityAddProductBinding
    lateinit var productDao: ProductDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        productDao = ProductDao(baseContext)
        binding.btnAdd.setOnClickListener {
            addProduct()
        }

        binding.btnShowProducts.setOnClickListener {
            startActivity(Intent(baseContext, ProductsActivity::class.java))
        }
    }

    private fun addProduct() {

        val name = binding.etProductName.text.toString()
        val price = binding.etUnitPrice.text.toString().toFloat()

        val product = Product(0, name, price)
        val id = productDao.addProduct(product)

        if(id > 0) {
            showSuccessMessage("Success", "Product added with id $id successfully")
        } else {
            showErrorMessage("Failed", "Failid to add product. Please retry.")
        }
    }
}