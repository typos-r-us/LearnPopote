package com.kennethmwenda.learnpopote.activities

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kennethmwenda.learnpopote.R
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        // Set user name or dashboard welcome text
        val sharedPrefs = getSharedPreferences("userMap",Context.MODE_PRIVATE)
        val _userName: String? = sharedPrefs.getString("fname",", welcome.")
        tv_userName.text=_userName
        //

    }
}