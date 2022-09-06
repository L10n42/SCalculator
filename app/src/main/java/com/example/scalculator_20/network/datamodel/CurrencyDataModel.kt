package com.example.scalculator_20.network.datamodel

data class CurrencyDataModel (
    val status: String,
    val updated_date: String,
    val base_currency_code: String,
    val amount: Double,
    val rates: Rates
)
