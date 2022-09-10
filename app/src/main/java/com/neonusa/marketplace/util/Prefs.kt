package com.neonusa.marketplace.util

import com.chibatching.kotpref.KotprefModel
import com.inyongtisto.myhelper.extension.toJson
import com.inyongtisto.myhelper.extension.toModel
import com.neonusa.marketplace.core.data.source.model.User

object Prefs: KotprefModel() {
    var isLogin by booleanPref(false)
    var user by stringPref()

    fun setUser(user: User?){
        this.user = user.toJson()
    }

    fun getUser(): User? {
        if(user.isEmpty()) return null
        return user.toModel(User::class.java)
    }
}

fun getTokoId() = Prefs.getUser()?.toko?.id