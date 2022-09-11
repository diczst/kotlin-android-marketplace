package com.neonusa.marketplace.util

object Constants {
//    const val BASE_URL = "http://127.0.0.1:8000/"

    // client mengakses API melalui IP address pc yang terhubung dengan jaringan yang sama
    // dengan device emulator
    const val BASE_URL = "http://192.168.43.181/marketplace-api/public/"
    const val USER_URL = "$BASE_URL/storage/user/"

    const val DEFALUT_ERROR = "Terjadi kesalahan"
}