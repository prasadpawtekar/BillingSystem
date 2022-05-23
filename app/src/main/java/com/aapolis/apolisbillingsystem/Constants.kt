package com.aapolis.apolisbillingsystem

object Constants {

    const val DB_NAME = "ShopDB"
    const val DB_VERSION = 3

    const val CREATE_CUSTOMER_TABLE = """
        CREATE TABLE customer(
            customer_id INTEGER PRIMARY KEY AUTOINCREMENT,
            name TEXT,
            mobile_no TEXT,
            address TEXT
        )
    """

    const val CREATE_PRODUCT_TABLE = """
        CREATE TABLE product(
            product_id INTEGER PRIMARY KEY AUTOINCREMENT,
            name TEXT,
            unit_price FLOAT
        )
    """

    const val CREATE_BILL_TABLE = """
        CREATE TABLE bill(
            billNo INTEGER PRIMARY KEY AUTOINCREMENT,
            dateTime INTEGER,
            totalAmount FLOAT,
            customer_id INTEGER REFERENCES customer(customer_id)
        )
    """

    const val CREATE_SALE_DETAILS_TABLE = """
        CREATE TABLE sales_details(
            sid INTEGER PRIMARY KEY AUTOINCREMENT, 
            billNo INTEGER REFERENCES bill(billNo), 
            customer_id INTEGER REFERENCES customer(customer_id), 
            productId INTEGER REFERENCES product(product_id),
            quantity INTEGER, 
            rate FLOAT, 
            amount FLOAT)
    """

    const val CREATE_REWARDS_TABLE = """
        CREATE TABLE rewards(
            rewardId INTEGER PRIMARY KEY AUTOINCREMENT,
            billNo INTEGER references bill(billNo),
            customer_id INTEGER references customer(customer_id),
            rewardPoints INTEGER
        )
    """

    const val CREATE_PICK_UP_STORE_TABLE = """
        CREATE TABLE pick_up_store (
            storeId INTEGER PRIMARY KEY AUTOINCREMENT,
            name TEXT,
            address TEXT
        )
    """
}