package com.neonusa.marketplace.core.data.source.remote.network

import com.neonusa.marketplace.core.data.source.remote.request.LoginRequest
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // suspend function ?
    // agar function ini berjalan secara asyncronous

    @POST("login")
    suspend fun login(
      // mendapatkan data dalam bentuk object {}
      @Body
      loginRequest: LoginRequest

      //mendapatkan data satu persatu (key:value pair)
//    @Field("email") email: String,
//    @Field("password") password: String
    ): Response<RequestBody>


    @POST("register")
    suspend fun register(
        //@Body user: User
    ): Response<RequestBody>

}