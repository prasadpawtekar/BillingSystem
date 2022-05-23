package com.aapolis.apolisbillingsystem.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.aapolis.apolisbillingsystem.R
import com.aapolis.apolisbillingsystem.adapters.CustomerAdapter
import com.aapolis.apolisbillingsystem.data.Message
import com.aapolis.apolisbillingsystem.databinding.ActivityCustomersBinding
import com.aapolis.apolisbillingsystem.sql.CustomerDao

class CustomersActivity : AppCompatActivity() {
    lateinit var binding: ActivityCustomersBinding
    lateinit var adapter: CustomerAdapter
    val dataList = ArrayList<Any>()
    lateinit var customerDao: CustomerDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent?.extras?.getString("title")?: ""
        if(title.isNotEmpty()) {
            supportActionBar?.title = title
        }
        customerDao = CustomerDao(baseContext)
        val list = customerDao.getCustomers()
        if(list.size == 0) {
            dataList.add(Message("No customer added so far."))
        } else {
            dataList.addAll(list)
        }

        adapter = CustomerAdapter(dataList)

        binding.rvCustomers.layoutManager = LinearLayoutManager(baseContext)
        binding.rvCustomers.adapter = adapter

        adapter.setOnCustomerSelectedListener { customer, position ->
            val bIntent = Intent(baseContext, BillingActivity::class.java)
            bIntent.putExtra("customer", customer)
            startActivity(bIntent)
            finish()
        }
    }
}