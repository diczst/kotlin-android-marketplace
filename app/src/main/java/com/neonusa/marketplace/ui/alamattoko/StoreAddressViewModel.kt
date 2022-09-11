package com.neonusa.marketplace.ui.alamattoko

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.neonusa.marketplace.core.data.repository.AppRepository
import com.neonusa.marketplace.core.data.source.model.AlamatToko

class StoreAddressViewModel(private val repo: AppRepository) : ViewModel() {
    fun get() = repo.getAlamatToko().asLiveData()
    fun create(data: AlamatToko) = repo.createAlamatToko(data).asLiveData()
    fun update(data: AlamatToko) = repo.updateAlamatToko(data).asLiveData()
}