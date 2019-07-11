package com.sun.okhttp_retrofit.data.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sun.okhttp_retrofit.data.model.DataWrapper
import retrofit2.Call
import retrofit2.Response

abstract class GenericRequestHandler<R> {

    protected abstract fun makeRequest(): Call<R>

    fun doRequest(): LiveData<DataWrapper<R>> {
        val liveData = MutableLiveData<DataWrapper<R>>()
        val dataWrapper = DataWrapper<R>()

        makeRequest().enqueue(object : ApiCallback<R>() {
            override fun handleResponseData(data: R) {
                dataWrapper.data = data
                liveData.value = dataWrapper
            }

            override fun handleError(response: Response<R>) {
                // Handle error from response
            }

            override fun handleException(t: Exception) {
                dataWrapper.exception = t
                liveData.value = dataWrapper
            }
        })

        return liveData
    }
}
