package com.example.openit.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.openit.R
import com.example.openit.activities.home.HomeActivity
import com.example.openit.constants.BEARER_TOKEN_KEY
import com.example.openit.constants.TOKEN
import com.example.openit.global.AuthorizationData

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AuthorizationData.bear_token = TOKEN
        startActivity( Intent(this, HomeActivity::class.java))
        finish()
    }
}