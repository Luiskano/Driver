package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RatingBar
import android.widget.Toast

class Evaluacion : AppCompatActivity() {

    private lateinit var btn: Button
    private lateinit var rb: RatingBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evaluacion)

        btn = findViewById<View>(R.id.button) as Button
        rb = findViewById<View>(R.id.rb) as RatingBar
    }

    fun evaluarRating(view: View){
        val ratingvalue = rb.rating
        Toast.makeText(this,"Rating is :" + ratingvalue, Toast.LENGTH_LONG).show()
    }
}
