package com.aapolis.apolisbillingsystem

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.aapolis.apolisbillingsystem.activities.AddCustomerActivity
import com.aapolis.apolisbillingsystem.activities.AddProductActivity
import com.aapolis.apolisbillingsystem.activities.AllBillsActivity
import com.aapolis.apolisbillingsystem.activities.CustomersActivity
import com.aapolis.apolisbillingsystem.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnManageCustomers.setOnClickListener {
            startActivity(Intent(baseContext, AddCustomerActivity::class.java))
        }

        binding.btnManageProducts.setOnClickListener {
            startActivity(Intent(baseContext, AddProductActivity::class.java))
        }

        binding.btnNewBill.setOnClickListener {
            startActivity(Intent(baseContext, CustomersActivity::class.java).apply {
                putExtra("title", "Select Customer")
            })
        }

        binding.btnAllBills.setOnClickListener {
            startActivity(Intent(baseContext, AllBillsActivity::class.java))
        }

        binding.btnSelectDate.setOnClickListener {
            selectDate()
        }

        binding.btnSelectTime.setOnClickListener {
            selectTime()
        }


    }

    private fun selectDate() {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            {
                dialog, year, month, day ->
                val selectedDate = "$year-$month-$day"
                binding.btnSelectDate.setText(selectedDate)
            },
            currentYear,
            currentMonth,
            currentDay
        )

        dpd.show()

    }

    private fun selectTime() {
        val calendar = Calendar.getInstance()
        val currentHours = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinutes = calendar.get(Calendar.MINUTE)

        val tpd = TimePickerDialog(
            this,
            { dialog, hours, minutes ->
                val selectedTime = "$hours:$minutes"
                binding.btnSelectTime.setText(selectedTime)
            },
            currentHours,
            currentMinutes,
            true
        )

        tpd.show()
    }
}