package com.neonusa.marketplace.core.data.repository

import android.util.Log
import com.inyongtisto.myhelper.extension.getErrorBody
import com.inyongtisto.myhelper.extension.logs
import com.neonusa.marketplace.core.data.source.local.LocalDataSource
import com.neonusa.marketplace.core.data.source.model.AlamatToko
import com.neonusa.marketplace.core.data.source.remote.RemoteDataSource
import com.neonusa.marketplace.core.data.source.remote.network.Resource
import com.neonusa.marketplace.core.data.source.remote.request.CreateTokoRequest
import com.neonusa.marketplace.core.data.source.remote.request.LoginRequest
import com.neonusa.marketplace.core.data.source.remote.request.RegisterRequest
import com.neonusa.marketplace.core.data.source.remote.request.UpdateProfileRequest
import com.neonusa.marketplace.util.Prefs
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody

class AppRepository(val local: LocalDataSource, val remote: RemoteDataSource) {

    fun login(data: LoginRequest) = flow{
        emit(Resource.loading(null))
        try {
            remote.login(data).let {
                if (it.isSuccessful) {
                    Prefs.isLogin = true
                    val body = it.body()
                    val user = body?.data

                    Prefs.setUser(user)
                    emit(Resource.success(body?.data))

                    Log.d("TAG", "login: $body")
                } else {

                    // REMINDER : method getErrorBody ini berasal dari library milik mas inyong tisto
                    // fungsinya agar kita tetap mengambil error message dari api
                    // meskipun response nya tidak successful

                    // dalam kasus aplikasi marketplace
                    emit(Resource.error( it.getErrorBody()?.message ?: "Error Default", null))

                    // dalam kasus lain apabila error response berbeda
//                    emit(Resource.error( it.getErrorBody(ErrorCustom::class.java)?
//                                             .namaAtribut ?: "Error Default", null))

                    Log.e("Login Error : ", it.message())
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
            Log.e("Login Error : ", e.message ?: "Terjadi Kesalahan")
        }
    }


    fun register(data: RegisterRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.register(data).let {
                if (it.isSuccessful) {
                    Prefs.isLogin = true
                    val body = it.body()
                    val user = body?.data
                    Prefs.setUser(user)
                    emit(Resource.success(user))
                    logs("succes:" + body.toString())
                } else {
                    emit(Resource.error(it.getErrorBody()?.message ?: "Default error dongs", null))
                    logs("Error:" + "keteragan error")
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
            logs("Error:" + e.message)
        }
    }

    fun updateUser(data: UpdateProfileRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.updateUser(data).let {
                if (it.isSuccessful) {
                    val body = it.body()
                    val user = body?.data

                    // agar kalau user rubah data, data toko tidak hilang
                    user?.toko = Prefs.getUser()?.toko

                    Prefs.setUser(user)
                    emit(Resource.success(user))
                    logs("succes:" + body.toString())
                    Log.i("TAG", "updateUser: $user")
                } else {
                    emit(Resource.error(it.getErrorBody()?.message ?: "Default error dongs", null))
                    logs("Error:" + "keteragan error")
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
            logs("Error:" + e.message)
        }
    }

    fun uploadUser(id: Int? = null, fileImage: MultipartBody.Part? = null) = flow {
        emit(Resource.loading(null))
        try {
            remote.uploadUser(id, fileImage).let {
                if (it.isSuccessful) {
                    val body = it.body()
                    val user = body?.data
                    Prefs.setUser(user)
                    emit(Resource.success(user))
                    logs("succes:" + body.toString())
                } else {
                    emit(Resource.error(it.getErrorBody()?.message ?: "Default error dongs", null))
                    logs("Error:" + "keteragan error")
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
            logs("Error:" + e.message)
        }
    }

    fun createToko(data: CreateTokoRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.createToko(data).let {
                if (it.isSuccessful) {
                    val body = it.body()?.data
                    emit(Resource.success(body))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message ?: "Default error dongs", null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }

    fun getUser(id: Int? = null) = flow {
        emit(Resource.loading(null))
        try {
            remote.getUser(id).let {
                if (it.isSuccessful) {
                    val body = it.body()
                    val user = body?.data
                    Prefs.setUser(user)
                    emit(Resource.success(user))
                    Log.i("TAG", "getUser: {${it.body()}}")
                } else {
                    emit(Resource.error(it.getErrorBody()?.message ?: "Default error dongs", null))
                    Log.i("TAG", "getUserError: ${it.getErrorBody()?.message}")
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }

    fun getAlamatToko() = flow {
        emit(Resource.loading(null))
        try {
            remote.getAlamatToko().let {
                if (it.isSuccessful) {
                    val body = it.body()
                    val data = body?.data

                    emit(Resource.success(data))
                    Log.i("TAG", "getUser: {${it.body()}}")
                } else {
                    emit(Resource.error(it.getErrorBody()?.message ?: "Default error dongs", null))
                    Log.i("TAG", "getUser: {${it.getErrorBody()?.message}}")

                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }

    fun createAlamatToko(data: AlamatToko) = flow {
        emit(Resource.loading(null))
        try {
            remote.createAlamatToko(data).let {
                if (it.isSuccessful) {
                    val body = it.body()?.data
                    emit(Resource.success(body))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message ?: "Default error dongs", null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }

    fun updateAlamatToko(data: AlamatToko) = flow {
        emit(Resource.loading(null))
        try {
            remote.updateAlamatToko(data).let {
                if (it.isSuccessful) {
                    val body = it.body()?.data
                    emit(Resource.success(body))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message ?: "Default error dongs", null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }

    // beberapa kasus jika jsonObject errorbody berbeda maka buat class baru dengan
    // atribut-atribut yang sama seperti yang ada di api
    class ErrorCustom(
        val ok: Boolean,
        val error_code: Int,
        val description: String? = null
    )

}
