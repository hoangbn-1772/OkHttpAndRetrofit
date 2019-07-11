package com.sun.okhttp_retrofit.data.local

import com.sun.okhttp_retrofit.data.datasource.UserDataSource
import com.sun.okhttp_retrofit.data.model.Account
import com.sun.okhttp_retrofit.data.model.UserWrapper
import retrofit2.Call


class UserLocalDataSource : UserDataSource {
    override fun login(account: Account): Call<UserWrapper> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
