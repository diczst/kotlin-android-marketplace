package com.neonusa.marketplace.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CartViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Ini adalah fragment keranjang"
    }
    val text: LiveData<String> = _text
}