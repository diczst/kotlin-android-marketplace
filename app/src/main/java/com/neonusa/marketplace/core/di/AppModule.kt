package com.neonusa.marketplace.core.di

import com.neonusa.marketplace.core.data.source.local.LocalDataSource
import com.neonusa.marketplace.core.data.source.remote.RemoteDataSource
import com.neonusa.marketplace.core.data.source.remote.network.ApiConfig
import org.koin.dsl.module

val appModule = module {
    single { ApiConfig.provideApiService }

    // Remote Data Source membutuhkan ApiService sebagai argument
    // kita tidak perlu membuat object baru untuk mengakses ApiService
    // langsung gunakan method get()

    single {  RemoteDataSource(get()) }
    single { LocalDataSource() }
}