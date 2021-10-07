package com.example.examen

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class registrar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)

        var sharedPreferences = getSharedPreferences("preferens", Context.MODE_PRIVATE)
        var button4: Button = findViewById(R.id.button4)
        var button5: Button = findViewById(R.id.button5)

        button4.setOnClickListener {
            startActivity(Intent(this,menu::class.java))
        }

        button5.setOnClickListener {
            startActivity(Intent(this,menu::class.java))
        }
    }
}