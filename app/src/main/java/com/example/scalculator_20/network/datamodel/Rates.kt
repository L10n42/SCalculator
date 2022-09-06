package com.example.scalculator_20.network.datamodel

import com.google.gson.annotations.SerializedName

data class Rates (

    @SerializedName("UAH")
    val uAH: Currency,

    @SerializedName("USD")
    val uSD: Currency,

    @SerializedName("EUR")
    val eUR: Currency,

    @SerializedName("CNY")
    val cNY: Currency
)
