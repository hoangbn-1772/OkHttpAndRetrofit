package com.sun.okhttp_retrofit.ui.okhttp

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.sun.okhttp_retrofit.R
import kotlinx.android.synthetic.main.activity_ok_http.*
import okhttp3.OkHttpClient
import okhttp3.Request
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
        private const val URL = "https://raw.github.com/square/okhttp/master/README.md"

        class OkHttpHandler : AsyncTask<String, Void, String>() {

            val client = OkHttpClient()

            override fun doInBackground(vararg params: String?): String {
                val request = Request.Builder()
                    .url(URL)
                    .header("Content-Type", "application/json")
                    .build()

                try {
                    val response = client.newCall(request).execute()
                    return response.body().toString()
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                return ""
            }

            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)
                Log.d("TAG", result.toString())
            }
        }
    }

    interface Callback{
        fun getDate(data: String)
    }
}
