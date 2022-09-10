package com.neonusa.marketplace.core.data.source.model

data class User(
    val created_at: String?,
    val email: String?,
    val email_verified_at: Any?,
    val id: Int?,
    val name: String?,
    val phone: String?,
    val image: String?,
    val updated_at: String?,
    var toko: Toko?
)