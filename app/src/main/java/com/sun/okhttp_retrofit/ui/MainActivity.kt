package com.sun.okhttp_retrofit.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sun.okhttp_retrofit.R
import com.sun.okhttp_retrofit.R.layout
import com.sun.okhttp_retrofit.ui.retrofit.RetrofitActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        initComponents()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_demo_retrofit -> move(RetrofitActivity::class.java)
        }
    }

    private fun initComponents() {
        btn_demo_okhttp.setOnClickListener(this)
        btn_demo_retrofit.setOnClickListener(this)
    }

    private fun move(activity: Class<*>) {
        Intent(this, activity).apply {
            startActivity(this)
        }
    }
}
