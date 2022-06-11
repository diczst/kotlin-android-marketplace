package com.neonusa.marketplace.core.data.repository

import com.neonusa.marketplace.core.data.source.local.LocalDataSource
import com.neonusa.marketplace.core.data.source.remote.RemoteDataSource

class AppRepository(val local: LocalDataSource, val remote: RemoteDataSource) {


}