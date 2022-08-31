package com.example.scalculator_20.ui.percentC

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PercentViewModel : ViewModel() {
    val imageId: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

}