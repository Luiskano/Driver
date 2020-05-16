package com.example.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    private lateinit var name:EditText
    private lateinit var surname:EditText
    private lateinit var correo:EditText
    private lateinit var pass:EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var dbReference:DatabaseReference
    private lateinit var database:FirebaseDatabase
    private lateinit var auth:FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register2)
        name = findViewById(R.id.name)
        surname = findViewById(R.id.surname)
        correo = findViewById(R.id.correo)
        pass = findViewById(R.id.pass)
        progressBar = findViewById(R.id.progressBar)
        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()
        dbReference = database.reference.child("User")

    }

    fun register(view:View){
        createNewAccount()
    }

    private fun createNewAccount(){
        val name:String=name.text.toString()
        val surname:String=surname.text.toString()
        val correo:String=correo.text.toString()
        val pass:String=pass.text.toString()

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(surname) && !TextUtils.isEmpty(correo) && !TextUtils.isEmpty(pass)){
            progressBar.visibility = View.VISIBLE
            auth.createUserWithEmailAndPassword(correo,pass)
                .addOnCompleteListener(this){
                    task ->

                    if(task.isComplete){
                        val user:FirebaseUser?=auth.currentUser
                        verifyEmail(user)
                        val userBD = dbReference.child(user?.uid.toString())

                        userBD.child("name").setValue(name)
                        userBD.child("surname").setValue(surname)
                        action()
                    }
                }
        }

    }


    private fun action(){
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun verifyEmail(user: FirebaseUser?){
        user?.sendEmailVerification()
            ?.addOnCompleteListener(this){
                    task ->

                if(task.isComplete){
                    Toast.makeText(this, "Email enviado", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this, "Error al enviar el correo", Toast.LENGTH_LONG).show()
                }
            }
    }
}

