package com.sun.okhttp_retrofit.data.model

import com.squareup.moshi.Json

open class BaseData<T> {
    @Json(name = "success") val success: Boolean? = null
    @Json(name = "code") val code: Int? = null
    @Json(name = "message") val message: String? = null
    @Json(name = "data") val data: T? = null
}
