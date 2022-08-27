package com.example.scalculator_20.ui.stickers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StickersViewModel : ViewModel() {

    private var _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    fun updateText(newText: String) {
    }
}