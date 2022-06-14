package com.neonusa.marketplace.core.data.repository

import android.util.Log
import com.neonusa.marketplace.core.data.source.local.LocalDataSource
import com.neonusa.marketplace.core.data.source.remote.RemoteDataSource
import com.neonusa.marketplace.core.data.source.remote.request.LoginRequest
import kotlinx.coroutines.flow.flow

class AppRepository(val local: LocalDataSource, val remote: RemoteDataSource) {

    fun login(data: LoginRequest) = flow{
        try {
            remote.login(data).let {
                if (it.isSuccessful) {
                    val body = it.body()
                    emit(body)
                    Log.d("TAG", "login: $body")
                } else {
                    Log.e("Login Error : ", it.message())
                }
            }
        } catch (e: Exception) {
            Log.d("TAG", "login: ${e.message}")
        }
    }


}
