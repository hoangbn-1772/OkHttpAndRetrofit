package com.sun.okhttp_retrofit.data.datasource

import com.sun.okhttp_retrofit.data.model.Account
import com.sun.okhttp_retrofit.data.model.UserWrapper
import retrofit2.Call

interface UserDataSource {

    fun login(account: Account): Call<UserWrapper>
}
