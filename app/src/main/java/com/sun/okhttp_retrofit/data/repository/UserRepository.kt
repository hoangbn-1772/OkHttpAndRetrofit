package com.sun.okhttp_retrofit.data.repository

import com.sun.okhttp_retrofit.data.datasource.UserDataSource
import com.sun.okhttp_retrofit.data.model.Account
import com.sun.okhttp_retrofit.data.model.UserWrapper
import com.sun.okhttp_retrofit.data.remote.UserRemoteDataSource
import retrofit2.Call

class UserRepository(private val userDataSource: UserDataSource) : UserDataSource {

    override fun login(account: Account): Call<UserWrapper> = userDataSource.login(account)
}
