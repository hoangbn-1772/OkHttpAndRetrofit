package com.sun.okhttp_retrofit.ui.retrofit.login

import com.sun.okhttp_retrofit.data.datasource.GenericRequestHandler
import com.sun.okhttp_retrofit.data.model.Account
import com.sun.okhttp_retrofit.data.model.UserWrapper
import com.sun.okhttp_retrofit.data.repository.UserRepository
import retrofit2.Call

class LoginInteractor(
    private val phoneOrEmail: String,
    private val password: String,
    private val repository: UserRepository
) : GenericRequestHandler<UserWrapper>() {

    override fun makeRequest(): Call<UserWrapper> {
        return repository.login(Account(phoneOrEmail, password))
    }
}
