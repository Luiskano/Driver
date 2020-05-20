package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var correo: EditText
    private lateinit var contrasenia: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        correo=findViewById(R.id.correo)
        contrasenia=findViewById(R.id.contrasenia)
        progressBar=findViewById(R.id.progressBar)
        auth=FirebaseAuth.getInstance()
    }

    fun forgotPassword(view:View){
        startActivity(Intent(this, ForgotPassword()::class.java))
    }

    fun register(view: View){
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    fun login(view: View){
        loginUser()
    }

    private fun loginUser(){
        val correo:String=correo.text.toString()
        val contrasenia:String=contrasenia.text.toString()

        if (!TextUtils.isEmpty(correo) && !TextUtils.isEmpty(contrasenia)){
            progressBar.visibility=View.VISIBLE
            auth.signInWithEmailAndPassword(correo, contrasenia)
                .addOnCompleteListener(this){
                    task ->
                    if(task.isSuccessful){
                        action()
                    }else{
                        Toast.makeText(this, "Error en la autentificacion", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

    private fun action(){
        startActivity(Intent(this, MainActivity::class.java))
    }
}
