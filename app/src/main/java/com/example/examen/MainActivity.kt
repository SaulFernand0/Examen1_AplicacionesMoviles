package com.example.examen

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var sharedPreferences = getSharedPreferences("preferens", Context.MODE_PRIVATE)
        var button: Button = findViewById(R.id.button)
        var editTex: EditText = findViewById(R.id.editTextTextPostalAddress)
        var editText: EditText = findViewById(R.id.editTextTextPassword)
        var switch: Switch = findViewById(R.id.switch1)

        button.setOnClickListener {
            val correo = editTex.text.toString().trim()
            val contraseña = editTex.text.toString().trim()
            val recordar = switch.isChecked
            val edit = sharedPreferences.edit()
            edit.putString("correo", correo)
            edit.putString("contraseña", contraseña)
            edit.putBoolean("recordar", recordar)
            edit.apply()
            startActivity(Intent(this,menu::class.java))
        }

        if (sharedPreferences.getBoolean("recordar", false)){
            startActivity(Intent(this,menu::class.java))
        }
    }
}