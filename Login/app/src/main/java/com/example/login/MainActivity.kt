package com.example.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun LoginActivity(view: View){
        startActivity(Intent(this, LoginActivity::class.java))
    }

    fun RegisterActivity(view: View){
        startActivity(Intent(this, RegisterActivity::class.java))
    }
}
