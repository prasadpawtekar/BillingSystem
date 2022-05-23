package com.aapolis.apolisbillingsystem.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.aapolis.apolisbillingsystem.R
import com.aapolis.apolisbillingsystem.data.Customer
import com.aapolis.apolisbillingsystem.databinding.ActivityAddCustomerBinding
import com.aapolis.apolisbillingsystem.sql.CustomerDao

class AddCustomerActivity : BaseActivity() {
    lateinit var binding: ActivityAddCustomerBinding
    lateinit var customerDao: CustomerDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        customerDao = CustomerDao( baseContext)
        binding.btnAdd.setOnClickListener {
            saveCustomerDetails()
        }

        binding.btnShowCustomers.setOnClickListener {
            startActivity(Intent(baseContext, CustomersActivity::class.java))
        }
    }

    private fun saveCustomerDetails() {
        val name = binding.etCustomerName.text.toString()
        val mobileNo = binding.etMobileNo.text.toString()

        val customer = Customer(0, name, mobileNo)

        val id = customerDao.addCustomer(customer)

        if(id > 0) {
            showSuccessMessage("Success", "Customer added with id $id successfully")
        } else {
            showErrorMessage("Failed", "Failed to add customer to table. Please retry.")
        }
    }


}