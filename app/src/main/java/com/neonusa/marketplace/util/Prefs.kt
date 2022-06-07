package com.neonusa.marketplace.util

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class Prefs(activity: Activity) {

    private var sp: SharedPreferences? = null
    private val login = "login"

    // Nama preferences diisi bebas
    init {
        sp = activity.getSharedPreferences("MYPREF", Context.MODE_PRIVATE)
    }

    // key : value
    // login : value
    // mengisi nilai boolean pada key login
    fun setIsLogin(value: Boolean) {
        sp!!.edit().putBoolean(login, value).apply()
    }

    // mendapatkan nilai boolean yang dimiliki key login
    // key login memiliki default value:false
    fun getIsLogin(): Boolean {
        return sp!!.getBoolean(login, false)
    }
}