package com.example.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class Entrar : AppCompatActivity() {

    var myAuth = FirebaseAuth.getInstance()
    private lateinit var salirA: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrar)
        salirA = findViewById(R.id.salirA)

        salirA.setOnClickListener { view ->
            Toast.makeText(this, "Desconectar", Toast.LENGTH_LONG).show()
                signOut()
            }
        myAuth.addAuthStateListener {
            if (myAuth.currentUser==null){
                this.finish()
            }
        }
    }

    fun signOut(){
        myAuth.signOut()
    }


    fun JobMap(view: View) {
        startActivity(Intent(this, JobMap::class.java))
    }

    fun informacion(view: View){
        startActivity(Intent(this, informacion::class.java))
    }

    fun Evaluacion(view: View){
        startActivity(Intent(this, Evaluacion::class.java))
    }

}
