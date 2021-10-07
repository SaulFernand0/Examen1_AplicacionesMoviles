package com.example.examen

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        var sharedPreferences = getSharedPreferences("preferens", Context.MODE_PRIVATE)
        var button3: Button = findViewById(R.id.button3)
        var button7: Button = findViewById(R.id.button7)
        var button9: Button = findViewById(R.id.button9)

        button3.setOnClickListener {
            startActivity(Intent(this,listar::class.java))
        }

        button7.setOnClickListener {
            startActivity(Intent(this,registrar::class.java))
        }

        button9.setOnClickListener {
            var edit = sharedPreferences.edit()
            edit.putBoolean("recordar", false)
            edit.apply()
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}