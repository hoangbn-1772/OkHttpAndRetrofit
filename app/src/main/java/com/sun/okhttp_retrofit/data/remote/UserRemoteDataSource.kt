package com.sun.okhttp_retrofit.data.remote

import com.sun.okhttp_retrofit.data.datasource.ApiService
import com.sun.okhttp_retrofit.data.datasource.UserDataSource
import com.sun.okhttp_retrofit.data.model.Account
import com.sun.okhttp_retrofit.data.model.UserWrapper
import retrofit2.Call

class UserRemoteDataSource(private val apiService: ApiService) : UserDataSource.Remote {

    override fun login(account: Account): Call<UserWrapper> = apiService.login(account)
}
