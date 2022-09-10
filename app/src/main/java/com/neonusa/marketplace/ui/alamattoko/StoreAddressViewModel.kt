package com.neonusa.marketplace.ui.alamattoko

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.neonusa.marketplace.core.data.repository.AppRepository

class StoreAddressViewModel(private val repo: AppRepository) : ViewModel() {
    fun get() = repo.getAlamatToko().asLiveData()
}