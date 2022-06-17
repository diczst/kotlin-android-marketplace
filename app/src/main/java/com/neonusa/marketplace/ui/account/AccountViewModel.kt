package com.neonusa.marketplace.ui.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AccountViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Ini adalah fragment akun"
    }
    val text: LiveData<String> = _text
}