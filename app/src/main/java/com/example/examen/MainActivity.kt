package com.example.examen

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import java.util.stream.Stream

class MainActivity : AppCompatActivity() {

    lateinit var button: Button
    lateinit var emailText: EditText
    lateinit var passwordText: EditText
    lateinit var switch: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        emailText = findViewById(R.id.editTextTextPostalAddress)
        passwordText = findViewById(R.id.editTextTextPassword)
        switch = findViewById(R.id.switch1)

        title = "Authentication"
        button.setOnClickListener {
            if (emailText.text.isNotEmpty() && passwordText.text.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(
                        emailText.text.toString(),
                        passwordText.text.toString()
                    )
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            if (switch.isChecked){
                                saveSession(applicationContext)
                            }
                            showHome()

                        } else {
                            showAlert1()

                        }
                    }
            }
            else{
                showAlert("Ingrese sus datos para loguearse")
            }
        }
    }

    private fun showAlert(message:String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showAlert1(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun saveSession(context:Context){
        var sharedPreferences = getSharedPreferences("recordar", MODE_PRIVATE)
        var edit = sharedPreferences.edit()
        edit.putString("email", emailText.text.toString()).putString("password", passwordText.text.toString())
        edit.apply()
    }

    private fun showHome(){
        startActivity(Intent(applicationContext, menu::class.java).putExtra("email", emailText.text.toString()).putExtra("password", passwordText.text.toString()))
    }
}