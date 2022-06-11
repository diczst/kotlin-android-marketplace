package com.neonusa.marketplace.core.di

import com.neonusa.marketplace.core.data.repository.AppRepository
import org.koin.dsl.module

val repositoryModule = module {
    // tidak perlu mendeklarasikan objek localDataSource dan remoteDataSource
    // hanya perlu menggunakan get() karena pembuatan kedua objek data source tersebut
    // sudah dilakukan di AppModule

    single {AppRepository(get(), get())}
}