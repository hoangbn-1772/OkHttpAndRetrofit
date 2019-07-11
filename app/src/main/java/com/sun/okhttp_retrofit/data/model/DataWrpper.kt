package com.sun.okhttp_retrofit.data.model

import java.lang.Exception

data class DataWrapper<T>(
    var exception: Exception? = null,
    var data: T? = null
)
