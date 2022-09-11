package com.neonusa.marketplace.core.data.source.remote.network

import com.neonusa.marketplace.core.data.source.model.AlamatToko
import com.neonusa.marketplace.core.data.source.remote.request.CreateTokoRequest
import com.neonusa.marketplace.core.data.source.remote.request.LoginRequest
import com.neonusa.marketplace.core.data.source.remote.request.RegisterRequest
import com.neonusa.marketplace.core.data.source.remote.request.UpdateProfileRequest
import com.neonusa.marketplace.core.data.source.remote.response.BaseListResponse
import com.neonusa.marketplace.core.data.source.remote.response.BaseSingleResponse
import com.neonusa.marketplace.core.data.source.remote.response.LoginResponse
import com.neonusa.marketplace.core.data.source.remote.response.TokoResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST("login")
    suspend fun login(
      @Body data: LoginRequest
    ): Response<LoginResponse>

    @POST("register")
    suspend fun register(
        @Body data: RegisterRequest
    ): Response<LoginResponse>

    // @path harus sama dengan didalam kurawal
    @PUT("update-user/{id}")
    suspend fun updateUser(
        @Path("id") int: Int,
        @Body data: UpdateProfileRequest
    ): Response<LoginResponse>

    @Multipart
    @POST("upload-user/{id}")
    suspend fun uploadUser(
        @Path("id") int: Int?,
        @Part data: MultipartBody.Part? = null
    ): Response<LoginResponse>

    @POST("toko")
    suspend fun createToko(
        @Body data: CreateTokoRequest
    ): Response<BaseSingleResponse<TokoResponse>>

    @GET("toko-user/{id}")
    suspend fun getUser(
        @Path("id") int: Int? = null
    ): Response<LoginResponse>

    @GET("alamat-toko/{id}")
    suspend fun getAlamatToko(
        @Path("id") idToko: Int? = null
    ): Response<BaseListResponse<AlamatToko>>

    @POST("alamat-toko")
    suspend fun createAlamatToko(
        @Body data: AlamatToko
    ): Response<BaseSingleResponse<AlamatToko>>

    @PUT("alamat-toko/{id}")
    suspend fun updateAlamatToko(
        @Path("id") id: Int? = null,
        @Body data: AlamatToko
    ): Response<BaseSingleResponse<AlamatToko>>
}
