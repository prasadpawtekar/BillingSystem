package com.aapolis.apolisbillingsystem.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.aapolis.apolisbillingsystem.R
import com.aapolis.apolisbillingsystem.adapters.SaleDetailsAdapter
import com.aapolis.apolisbillingsystem.data.*
import com.aapolis.apolisbillingsystem.databinding.ActivityBillingBinding
import com.aapolis.apolisbillingsystem.sql.BillDao
import com.aapolis.apolisbillingsystem.sql.SaleDetailsDao

class BillingActivity : BaseActivity() {
    lateinit var binding: ActivityBillingBinding
    lateinit var customer: Customer
    val sellItems =  ArrayList<Sell>()
    lateinit var adapter: SaleDetailsAdapter
    lateinit var billDao: BillDao
    lateinit var saleDetailsDao: SaleDetailsDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBillingBinding.inflate(layoutInflater)
        setContentView(binding.root)


        billDao = BillDao(baseContext)
        saleDetailsDao = SaleDetailsDao(baseContext)

        intent.extras?.getParcelable<Customer>("customer")?.let {
            customer = it
            binding.tvCustomerName.text = customer.name
            binding.tvMobileNo.text = customer.mobileNo
        }

        binding.rvSellItems.layoutManager = LinearLayoutManager(baseContext)
        adapter = SaleDetailsAdapter(sellItems)
        binding.rvSellItems.adapter = adapter

        adapter.setOnQtyDecrementedListener { sell, position ->
            if(sell.quantity == 1) {
                sellItems.removeAt(position)
                calculateTotal()
                adapter.notifyDataSetChanged()
            } else {
                sellItems[position].quantity--
                calculateTotal()
                adapter.notifyItemChanged(position)
            }
        }

        adapter.setOnQtyIncrementedListener { sell, position ->
            sellItems[position].quantity++
            calculateTotal()
            adapter.notifyItemChanged(position)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if(menu != null) {
            menuInflater.inflate(R.menu.billing_menu, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_add_product) {
            val pIntent = Intent(baseContext, ProductsActivity::class.java)

            startActivityForResult(pIntent, REQUEST_CODE_SELECT_PRODUCT)
        } else if(item.itemId == R.id.action_checkout) {
            checkout()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val REQUEST_CODE_SELECT_PRODUCT = 10
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE_SELECT_PRODUCT) {
            if(resultCode == RESULT_OK) {
                val product = data?.extras?.getParcelable<Product>("product")
                product?.let {
                    val sellItem = Sell(it.product_id, it.name, it.unit_price, 1)
                    sellItems.add(sellItem)
                    calculateTotal()
                    adapter.notifyItemChanged(sellItems.size - 1)

                }
            }
        }
    }
    var totalBillAmount = 0f
    fun calculateTotal() {
        totalBillAmount = 0f
        sellItems.forEach{
            totalBillAmount = totalBillAmount + it.price * it.quantity
        }

        binding.tvTotalBillAmount.text = "Total bill amount = $totalBillAmount"
    }

    fun checkout() {

        val currentTime: Long = System.currentTimeMillis()


        val bill = Bill(0, currentTime, totalBillAmount, customer.customerId)
        val billNo = billDao.createBill(bill)

        val saleDetails = ArrayList<SaleDatails>()

        sellItems.forEach {
            val saleDetail = SaleDatails(0, billNo, customer.customerId, it.productId, it.quantity,it.price, it.quantity*it.price )
            saleDetails.add(saleDetail)
        }

        val saved = saleDetailsDao.addSaleDetails(saleDetails)
        if(saved) {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_success)
                .setTitle("Bill Created!")
                .setMessage("Bill details saved succesfully.")
                .setPositiveButton("Ok") {
                        dialog, which ->
                    dialog.dismiss()
                    finish()
                }

            val dialog = builder.create()

            dialog.show()

        } else {
            showErrorMessage("Failed", "Failed to save all bill details. Please retry.")
        }
    }
}