package com.neonusa.marketplace.core.data.source.remote.request

// harus sama dengan key
data class LoginRequest (
    val email: String,
    val password: String
    )