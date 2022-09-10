package com.neonusa.marketplace.core.di

import com.neonusa.marketplace.ui.alamattoko.StoreAddressViewModel
import com.neonusa.marketplace.ui.auth.AuthViewModel
import com.neonusa.marketplace.ui.navigation.NavViewModel
import com.neonusa.marketplace.ui.store.OpenStoreViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{ AuthViewModel(get()) }
    viewModel { OpenStoreViewModel(get()) }
    viewModel { NavViewModel(get()) }
    viewModel { StoreAddressViewModel(get()) }
}