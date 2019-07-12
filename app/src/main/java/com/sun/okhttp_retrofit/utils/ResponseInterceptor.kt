package com.sun.okhttp_retrofit.utils

import com.sun.okhttp_retrofit.data.model.User
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class ResponseInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        try {
            val jsonObject = JSONObject()

            val user = User(
                1,
                "Hoang",
                "Bui",
                "123",
                "adsdas",
                "djasjd",
                "dkjahskdjh",
                "Nam",
                "dasd"
            )

            if (response.code() == 200) {
                jsonObject.put("code", 200)
                jsonObject.put("success", "Ok")
                jsonObject.put("message", "approved")
                jsonObject.put("data", JSONObject(response.body()?.string()))
                //response.body()?.string(),user.toString()

            } else {

            }

            val contentType = response.body()?.contentType()
            val body = ResponseBody.create(contentType, jsonObject.toString())
            return response.newBuilder().body(body).build()
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return response
    }
}
