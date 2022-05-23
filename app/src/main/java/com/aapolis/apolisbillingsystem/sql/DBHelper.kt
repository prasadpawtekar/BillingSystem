package com.aapolis.apolisbillingsystem.sql

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import com.aapolis.apolisbillingsystem.Constants

class DBHelper(context: Context):
    SQLiteOpenHelper(context, Constants.DB_NAME, null, Constants.DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        try {
            db?.let {
                it.execSQL(Constants.CREATE_CUSTOMER_TABLE)
                it.execSQL(Constants.CREATE_PRODUCT_TABLE)
                it.execSQL(Constants.CREATE_BILL_TABLE)
                it.execSQL(Constants.CREATE_SALE_DETAILS_TABLE)
                it.execSQL(Constants.CREATE_REWARDS_TABLE)
                it.execSQL(Constants.CREATE_PICK_UP_STORE_TABLE)

            }
        } catch (e: SQLiteException) {
            e.printStackTrace()
        }
    }

    // users -> 1
    // users -> 2
    // current db version = 3
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {


        if(oldVersion < 2) {
            db?.execSQL("ALTER TABLE customer ADD address TEXT")
            db?.execSQL(Constants.CREATE_REWARDS_TABLE)
            Log.d("DatabaseUpgrade", "onUpgrade: Database upgraded to new version successfully.")
        }

        if(oldVersion < 3) {
            db?.execSQL(Constants.CREATE_PICK_UP_STORE_TABLE)
            Log.d("DatabaseUpgrade", "onUpgrade: Database upgraded to new version successfully.")
        }

    }



}