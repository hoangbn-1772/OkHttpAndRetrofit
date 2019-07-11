package com.sun.okhttp_retrofit.ui.retrofit

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sun.okhttp_retrofit.R
import com.sun.okhttp_retrofit.data.datasource.ApiObserver
import com.sun.okhttp_retrofit.data.model.UserWrapper
import com.sun.okhttp_retrofit.ui.retrofit.login.LoginViewModel
import kotlinx.android.synthetic.main.activity_retrofit.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RetrofitActivity : AppCompatActivity(), View.OnClickListener {

    private val viewModel: LoginViewModel by viewModel()

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
        viewModel.getDataFromWebService().observe(
            this,
            ApiObserver(object : ApiObserver.ChangeListener<UserWrapper> {
                override fun onSuccess(dataWrapper: UserWrapper) {
                    Log.d("TAG", dataWrapper.message)
                }

                override fun onException(exception: Exception) {
                    // Handle exception
                }
            })
        )
    }
}
