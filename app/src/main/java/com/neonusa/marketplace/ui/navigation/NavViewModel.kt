package com.neonusa.marketplace.ui.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.neonusa.marketplace.core.data.repository.AppRepository

class NavViewModel(private val repo: AppRepository) : ViewModel() {
    fun getUser(id: Int) = repo.getUser(id).asLiveData()
}