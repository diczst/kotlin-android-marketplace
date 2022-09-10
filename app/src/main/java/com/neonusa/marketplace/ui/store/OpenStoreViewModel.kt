package com.neonusa.marketplace.ui.store

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.neonusa.marketplace.core.data.repository.AppRepository
import com.neonusa.marketplace.core.data.source.remote.request.CreateTokoRequest

class OpenStoreViewModel(val repo: AppRepository) : ViewModel() {
    fun createToko(data: CreateTokoRequest) = repo.createToko(data).asLiveData()
}