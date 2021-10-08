package com.example.examen

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class menu : AppCompatActivity() {

    lateinit var button3: Button
    lateinit var button7: Button
    lateinit var button9: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        button3 = findViewById(R.id.button3)
        button7 = findViewById(R.id.button7)
        button9 = findViewById(R.id.button9)

        var sharedPreferences = getSharedPreferences("recordar", MODE_PRIVATE)
        //FirebaseAuth.getInstance().signInWithEmailAndPassword(sharedPreferences.getString("email")!!, sharedPreferences.getString("password")!!)

        button3.setOnClickListener {
            startActivity(Intent(this,listar::class.java))
        }

        button7.setOnClickListener {
            startActivity(Intent(this,registrar::class.java))
        }

        button9.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}