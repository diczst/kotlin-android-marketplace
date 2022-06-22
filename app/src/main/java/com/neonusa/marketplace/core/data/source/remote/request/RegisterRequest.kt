package com.neonusa.marketplace.core.data.source.remote.request

// harus sama dengan key
data class RegisterRequest (
    val name: String,
    val email: String,
    val phone: String,
    val password: String
    )