package com.neonusa.marketplace.core.data.source.remote.response

import com.neonusa.marketplace.core.data.source.model.User

data class LoginResponse(
    val code: Int? = null,
    val message: String? = null,
    val data: User? = null
)
