package com.sun.okhttp_retrofit.ui.retrofit

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sun.okhttp_retrofit.R
import com.sun.okhttp_retrofit.data.model.Account
import com.sun.okhttp_retrofit.data.model.UserWrapper
import com.sun.okhttp_retrofit.data.repository.UserRepository
import kotlinx.android.synthetic.main.activity_retrofit.*
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitActivity : AppCompatActivity(), View.OnClickListener {

    private val repository: UserRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)
        initComponents()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_request_data -> getDataFromWebService()
        }
    }

    private fun initComponents() {
        btn_request_data.setOnClickListener(this)
    }

    private fun getDataFromWebService() {

        repository.login(Account("123", "hoang123"))
            .enqueue(object : Callback<UserWrapper> {
                override fun onFailure(call: Call<UserWrapper>, t: Throwable) {
                    Log.d("TAG", t.message)
                    call.clone()
                }

                override fun onResponse(call: Call<UserWrapper>, response: Response<UserWrapper>) {
                    Log.d("TAG", response.body()?.message)
                }
            })
    }
}
