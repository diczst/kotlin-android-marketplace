package com.neonusa.marketplace.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.neonusa.marketplace.core.data.repository.AppRepository
import com.neonusa.marketplace.core.data.source.remote.request.LoginRequest

class LoginViewModel(val repo: AppRepository): ViewModel() {
    fun login(data: LoginRequest) = repo.login(data).asLiveData()
}