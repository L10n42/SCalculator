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

    val rememberedOverpaymentP1: MutableLiveData<Float> by lazy {
        MutableLiveData<Float>()
    }
    val overpaymentP1: MutableLiveData<Float> by lazy {
        MutableLiveData<Float>()
    }

    val rememberedOverpaymentP2: MutableLiveData<Float> by lazy {
        MutableLiveData<Float>()
    }
    val overpaymentP2: MutableLiveData<Float> by lazy {
        MutableLiveData<Float>()
    }

    val rememberedOverpaymentP3: MutableLiveData<Float> by lazy {
        MutableLiveData<Float>()
    }
    val overpaymentP3: MutableLiveData<Float> by lazy {
        MutableLiveData<Float>()
    }

    val rememberedOverpaymentP4: MutableLiveData<Float> by lazy {
        MutableLiveData<Float>()
    }
    val overpaymentP4: MutableLiveData<Float> by lazy {
        MutableLiveData<Float>()
    }

}