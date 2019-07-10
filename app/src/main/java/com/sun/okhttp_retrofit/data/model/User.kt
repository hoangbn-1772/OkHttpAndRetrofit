package com.sun.okhttp_retrofit.data.model

import com.squareup.moshi.Json

data class User(
    @Json(name = "id") val id: Int,
    @Json(name = "firstName") val firstName: String?,
    @Json(name = "lastName") val lastName: String?,
    @Json(name = "phone") val phone: String?,
    @Json(name = "email") val email: String?,
    @Json(name = "imageUrl") val imageUrl: String?,
    @Json(name = "birthDate") val birthDate: String,
    @Json(name = "gender") val gender: String?,
    @Json(name = "role") val role: String
)

class UserWrapper : BaseData<User>()
