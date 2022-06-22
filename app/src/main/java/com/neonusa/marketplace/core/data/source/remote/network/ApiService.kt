package com.neonusa.marketplace.core.data.source.remote.network

import com.neonusa.marketplace.core.data.source.remote.request.LoginRequest
import com.neonusa.marketplace.core.data.source.remote.request.RegisterRequest
import com.neonusa.marketplace.core.data.source.remote.response.LoginResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("login")
    suspend fun login(
      @Body
      loginRequest: LoginRequest
    ): Response<LoginResponse>


    @POST("register")
    suspend fun register(
        @Body
        registerRequest: RegisterRequest
    ): Response<LoginResponse>

}