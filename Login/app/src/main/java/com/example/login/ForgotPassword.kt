package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.view.View
import android.webkit.WebView
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ForgotPassword : AppCompatActivity() {

    private lateinit var correo: EditText
    private lateinit var auth: FirebaseAuth
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        auth=FirebaseAuth.getInstance()
        correo=findViewById(R.id.correo)
        progressBar=findViewById(R.id.progressBar)
    }

    fun envio(view: View){
        val correo=correo.text.toString()

        if (!TextUtils.isEmpty(correo)){
            auth.sendPasswordResetEmail(correo)
                .addOnCompleteListener(this){
                    task ->
                    if (task.isSuccessful){
                        progressBar.visibility=View.VISIBLE
                        startActivity(Intent(this, LoginActivity::class.java))
                    }else{
                        Toast.makeText(this, "Error al enviar el email", Toast.LENGTH_LONG).show()
                    }
            }
        }
    }
}
