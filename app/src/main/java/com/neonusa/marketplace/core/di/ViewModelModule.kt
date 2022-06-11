package com.neonusa.marketplace.core.di

import androidx.lifecycle.ViewModel
import com.neonusa.marketplace.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{LoginViewModel(get())}
}