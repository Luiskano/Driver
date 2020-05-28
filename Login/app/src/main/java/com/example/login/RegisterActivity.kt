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

    private lateinit var nombre:EditText
    private lateinit var apellidoPaterno:EditText
    private lateinit var apellidoMaterno:EditText
    private lateinit var contrasenia:EditText
    private lateinit var correo:EditText
    private lateinit var telefono:EditText
    private lateinit var identificacion:EditText
    private lateinit var RFC:EditText
    private lateinit var licencia:EditText
    private lateinit var circulacion:EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var dbReference:DatabaseReference
    private lateinit var database:FirebaseDatabase
    private lateinit var auth:FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register2)
        nombre = findViewById(R.id.nombre)
        apellidoPaterno = findViewById(R.id.apellidoPaterno)
        apellidoMaterno = findViewById(R.id.apellidoMaterno)
        correo = findViewById(R.id.correo)
        contrasenia = findViewById(R.id.contrasenia)
        telefono = findViewById(R.id.telefono)
        RFC = findViewById(R.id.RFC)
        identificacion = findViewById(R.id.identificacion)
        licencia = findViewById(R.id.licencia)
        circulacion = findViewById(R.id.circulacion)
        progressBar = findViewById(R.id.progressBar)
        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()
        dbReference = database.reference.child("Informacion del conductor")

    }

    fun register(view:View){
        createNewAccount()
    }

    private fun createNewAccount(){
        val nombre:String=nombre.text.toString()
        val apellidoPaterno:String=apellidoPaterno.text.toString()
        val apellidoMaterno:String=apellidoMaterno.text.toString()
        val correo:String=correo.text.toString()
        val contrasenia:String=contrasenia.text.toString()
        val telefono:String=telefono.text.toString()
        val RFC:String=RFC.text.toString()
        val identificacion:String=identificacion.text.toString()
        val licencia:String=licencia.text.toString()
        val circulacion:String=circulacion.text.toString()

        if (!TextUtils.isEmpty(nombre) && !TextUtils.isEmpty(apellidoPaterno) && !TextUtils.isEmpty(apellidoMaterno) && !TextUtils.isEmpty(correo) &&
            !TextUtils.isEmpty(contrasenia) && !TextUtils.isEmpty(telefono) && !TextUtils.isEmpty(RFC) && !TextUtils.isEmpty(identificacion) &&
            !TextUtils.isEmpty(licencia) && !TextUtils.isEmpty(circulacion)){
            progressBar.visibility = View.VISIBLE
            auth.createUserWithEmailAndPassword(correo,contrasenia)
                .addOnCompleteListener(this){
                    task ->

                    if(task.isComplete){
                        val user:FirebaseUser?=auth.currentUser
                        verifyEmail(user)
                        val userBD = dbReference.child(user?.uid.toString())

                        userBD.child("Nombre").setValue(nombre)
                        userBD.child("Apellido Paterno").setValue(apellidoPaterno)
                        userBD.child("Apellido Materno").setValue(apellidoMaterno)
                        userBD.child("Correo").setValue(correo)
                        userBD.child("Contraseña").setValue(contrasenia)
                        userBD.child("Telefono").setValue(telefono)
                        userBD.child("RFC").setValue(RFC)
                        userBD.child("Identificacion").setValue(identificacion)
                        userBD.child("Licencia").setValue(licencia)
                        userBD.child("Circulacion").setValue(circulacion)

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

