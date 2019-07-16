package com.sun.okhttp_retrofit.ui.okhttp

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.sun.okhttp_retrofit.R
import com.sun.okhttp_retrofit.data.model.Account
import com.sun.okhttp_retrofit.utils.LoggingInterceptor
import kotlinx.android.synthetic.main.activity_ok_http.*
import okhttp3.*
import org.json.JSONObject
import java.lang.Exception

class OkHttpActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ok_http)
        initComponents()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_request_data -> getData()
        }
    }

    private fun initComponents() {
        btn_request_data.setOnClickListener(this)
    }

    private fun getData() {
        val handler = OkHttpHandler()
        handler.execute()
    }

    companion object {

        private val JSON = MediaType.parse("application/json; charset=utf-8")

        private const val BASE_URL = "https://spring-boot-wall-tags.herokuapp.com/adsharingspace/"

        private const val LOGIN = "auth/login"

        class OkHttpHandler : AsyncTask<String, Void, String>() {

            private val client = OkHttpClient.Builder()
                .addInterceptor(LoggingInterceptor())
                .build()

            override fun doInBackground(vararg params: String?): String {
                val acc = Account("123", "hoang123")
                val jsonObject = JSONObject().put("String", acc)
                val requestBody = RequestBody.create(JSON, jsonObject.toString())

                val request = Request.Builder()
                    .url(BASE_URL + LOGIN)
//                    .header("Content-Type", "application/json")
//                    .method("POST",requestBody)
                    .post(requestBody)
                    .build()

                try {
                    // synchronous
                    val response = client.newCall(request).execute()
                    Log.d("TAG", response.code().toString())
                    return response.body().toString()
                    // Asynchronous call enqueue
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                return ""
            }

            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)
            }
        }
    }

    interface Callback {
        fun getDate(data: String)
    }
}
