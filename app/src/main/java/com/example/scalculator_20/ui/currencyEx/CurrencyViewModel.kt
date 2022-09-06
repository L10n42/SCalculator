package com.example.scalculator_20.ui.currencyEx

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CurrencyViewModel : ViewModel()  {

    val currencyFrom: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val currencyTo: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val result: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }



}