package com.sun.okhttp_retrofit.data.datasource

import com.sun.okhttp_retrofit.data.model.Account
import com.sun.okhttp_retrofit.data.model.UserWrapper
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    fun login(@Body account: Account): Call<UserWrapper>
}
