package com.sun.okhttp_retrofit.data.datasource

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

abstract class ApiCallback<T> : Callback<T> {

    override fun onFailure(call: Call<T>, t: Throwable) {
        if (t is Exception) {
            handleException(t)
        } else {
            // Do something else
        }
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        response.body()?.let {
            handleResponseData(it)
        } ?: handleError(response)
    }

    protected abstract fun handleResponseData(data: T)

    protected abstract fun handleError(response: Response<T>)

    protected abstract fun handleException(t: Exception)
}
