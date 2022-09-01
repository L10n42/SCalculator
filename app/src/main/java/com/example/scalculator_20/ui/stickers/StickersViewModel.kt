package com.example.scalculator_20.ui.stickers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StickersViewModel : ViewModel() {

    val weapon: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val weaponImg: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val commP1: MutableLiveData<Float> by lazy {
        MutableLiveData<Float>()
    }

    val commP2: MutableLiveData<Float> by lazy {
        MutableLiveData<Float>()
    }

    val commP3: MutableLiveData<Float> by lazy {
        MutableLiveData<Float>()
    }

    val commP4: MutableLiveData<Float> by lazy {
        MutableLiveData<Float>()
    }

}