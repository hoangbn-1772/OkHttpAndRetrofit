package com.sun.okhttp_retrofit.data.model

import com.squareup.moshi.Json

data class Account(
    @Json(name = "phoneOrEmail") private val phoneOrEmail: String?,
    @Json(name = "password") private val password: String?
)
