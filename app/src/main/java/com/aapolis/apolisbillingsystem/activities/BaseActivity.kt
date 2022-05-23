package com.aapolis.apolisbillingsystem.activities

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.aapolis.apolisbillingsystem.R

open class BaseActivity: AppCompatActivity() {

    fun showSuccessMessage(title: String, message: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            .setIcon(R.drawable.ic_success)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Ok") {
                    dialog, which ->
                dialog.dismiss()
            }

        val dialog = builder.create()

        dialog.show()
    }

    fun showErrorMessage(title: String, message: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            .setIcon(R.drawable.ic_error)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Ok") {
                    dialog, which ->
                dialog.dismiss()
            }

        val dialog = builder.create()

        dialog.show()
    }
}