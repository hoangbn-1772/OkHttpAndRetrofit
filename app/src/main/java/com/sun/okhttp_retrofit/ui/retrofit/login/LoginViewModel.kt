package com.sun.okhttp_retrofit.ui.retrofit.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sun.okhttp_retrofit.data.model.DataWrapper
import com.sun.okhttp_retrofit.data.model.UserWrapper
import com.sun.okhttp_retrofit.data.repository.UserRepository

class LoginViewModel(private val repository: UserRepository) : ViewModel() {

    fun getDataFromWebService(): LiveData<DataWrapper<UserWrapper>> {
        val login = LoginInteractor("123", "hoang123", repository)

        return login.doRequest()
    }
}
