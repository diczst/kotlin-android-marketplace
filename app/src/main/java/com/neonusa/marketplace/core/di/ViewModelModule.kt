package com.neonusa.marketplace.core.di

import com.neonusa.marketplace.ui.auth.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{ AuthViewModel(get()) }
}