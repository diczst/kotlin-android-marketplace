package com.neonusa.marketplace.core.data.repository

import android.util.Log
import com.neonusa.marketplace.core.data.source.local.LocalDataSource
import com.neonusa.marketplace.core.data.source.remote.RemoteDataSource
import com.neonusa.marketplace.core.data.source.remote.network.Resource
import com.neonusa.marketplace.core.data.source.remote.request.LoginRequest
import kotlinx.coroutines.flow.flow

class AppRepository(val local: LocalDataSource, val remote: RemoteDataSource) {

    fun login(data: LoginRequest) = flow{
        emit(Resource.loading(null))
        try {
            remote.login(data).let {
                if (it.isSuccessful) {
                    val body = it.body()
                    emit(Resource.success(body?.data))

                    Log.d("TAG", "login: $body")
                } else {
                    emit(Resource.error(it.body()?.message ?: "Error Default", null))
                    Log.e("Login Error : ", it.message())
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
            Log.e("Login Error : ", e.message ?: "Terjadi Kesalahan")
        }
    }


}
