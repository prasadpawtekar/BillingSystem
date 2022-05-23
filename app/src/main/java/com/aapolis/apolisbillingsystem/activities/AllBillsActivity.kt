package com.aapolis.apolisbillingsystem.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.aapolis.apolisbillingsystem.R
import com.aapolis.apolisbillingsystem.adapters.CustomerBillAdapter
import com.aapolis.apolisbillingsystem.data.CustomerBill
import com.aapolis.apolisbillingsystem.databinding.ActivityAllBillsBinding
import com.aapolis.apolisbillingsystem.sql.BillDao

class AllBillsActivity : AppCompatActivity() {
    lateinit var binding: ActivityAllBillsBinding
    lateinit var billDao: BillDao
    lateinit var bills: ArrayList<CustomerBill>
    lateinit var adapter: CustomerBillAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllBillsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.rvBills.layoutManager = LinearLayoutManager(baseContext)
        billDao = BillDao(baseContext)
        bills = billDao.getAllBills()
        adapter = CustomerBillAdapter(bills)

        binding.rvBills.adapter = adapter

    }
}