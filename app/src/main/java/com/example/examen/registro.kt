package com.example.examen

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class registro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        var sharedPreferences = getSharedPreferences("preferens", Context.MODE_PRIVATE)
        var button: Button = findViewById(R.id.button4)

        button.setOnClickListener {
            var edit = sharedPreferences.edit()
            edit.apply()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}