package com.example.scalculator_20.ui.steamC

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SteamViewModel : ViewModel() {
    val commission: MutableLiveData<Float> by lazy {
        MutableLiveData<Float>()
    }

    val result: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}