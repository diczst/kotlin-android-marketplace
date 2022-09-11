package com.neonusa.marketplace.core.data.source.remote

import com.neonusa.marketplace.core.data.source.model.AlamatToko
import com.neonusa.marketplace.core.data.source.remote.network.ApiService
import com.neonusa.marketplace.core.data.source.remote.request.CreateTokoRequest
import com.neonusa.marketplace.core.data.source.remote.request.LoginRequest
import com.neonusa.marketplace.core.data.source.remote.request.RegisterRequest
import com.neonusa.marketplace.core.data.source.remote.request.UpdateProfileRequest
import com.neonusa.marketplace.util.getTokoId
import okhttp3.MultipartBody

class RemoteDataSource(private val api: ApiService) {
    suspend fun login(data: LoginRequest) = api.login(data)
    suspend fun register(data: RegisterRequest) = api.register(data)

    suspend fun updateUser(data: UpdateProfileRequest) = api.updateUser(data.id, data)
    suspend fun uploadUser(id: Int? = null, fileImage: MultipartBody.Part? = null) = api.uploadUser(id, fileImage)
    suspend fun createToko(data: CreateTokoRequest) = api.createToko(data)
    suspend fun getUser(id: Int? = null) = api.getUser(id)

    suspend fun getAlamatToko() = api.getAlamatToko(getTokoId())
    suspend fun createAlamatToko(data: AlamatToko) = api.createAlamatToko(data)
    suspend fun updateAlamatToko(data: AlamatToko) = api.updateAlamatToko(data.id, data)
}