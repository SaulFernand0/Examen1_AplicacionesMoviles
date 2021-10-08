package com.example.examen

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class registrar : AppCompatActivity() {

    lateinit var nombreText: EditText
    lateinit var precioText: EditText
    lateinit var stockText: EditText
    lateinit var categoriaText: EditText
    lateinit var addButton: Button
    lateinit var backButton: Button
    lateinit var cancelButton: Button
    private var idproducto: String = ""

    var firebaseHelper: firebaseHelper = firebaseHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)


        nombreText = findViewById(R.id.editTextTextPersonName)
        precioText = findViewById(R.id.editTextNumberDecimal)
        stockText = findViewById(R.id.editTextNumber)
        categoriaText = findViewById(R.id.editTextTextPersonName1)
        addButton = findViewById(R.id.button)
        backButton = findViewById(R.id.button4)
        cancelButton = findViewById(R.id.button5)

        intent.extras?.let {

            nombreText.setText(it.getString("nombre"))
            precioText.setText(it.getDouble("precio").toString())
            stockText.setText(it.getInt("stock").toString())
            categoriaText.setText(it.getInt("categoria").toString())

            if (it.getString("idproducto") != "") {
                idproducto = it.getString("idproducto").toString()
                addButton.text = "Editar Producto"
            }

        }

        addButton.setOnClickListener {
            validateForm(
                idproducto,
                nombreText.text.toString(),
                precioText.text.toString(),
                stockText.text.toString(),
                categoriaText.text.toString()
            )

        }

        backButton.setOnClickListener {
            startActivity(Intent(this,menu::class.java))
        }

        cancelButton.setOnClickListener {
            startActivity(Intent(this,menu::class.java))
        }
    }

    fun validateForm(
        idproducto: String,
        name: String,
        price: String,
        stock: String,
        categoria: String
    ) {
        if (name.isBlank() && price.isBlank() && stock.isBlank() && categoria.isBlank()) {
            Toast.makeText(applicationContext, "Empty fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (idproducto == "") {
            firebaseHelper.createProduct(
                productoModel(
                    idproducto,
                    name,
                    price.toDouble(),
                    stock.toInt(),
                    categoria
                )
            ).addOnSuccessListener {
                Toast.makeText(applicationContext, "Se ha registrado el producto con éxito", Toast.LENGTH_SHORT)
                    .show()
            }.addOnFailureListener {
                Toast.makeText(applicationContext, "Error al registrar el producto", Toast.LENGTH_SHORT).show()
            }

        } else {
            firebaseHelper.updateProduct(
                productoModel(
                    idproducto,
                    name,
                    price.toDouble(),
                    stock.toInt(),
                    categoria
                )
            ).addOnSuccessListener {
                Toast.makeText(applicationContext, "Se ha actualizado el producto con éxito", Toast.LENGTH_SHORT)
                    .show()
                startActivity(Intent(applicationContext, listar::class.java))
            }.addOnFailureListener {
                Toast.makeText(applicationContext, "Error al actualizar el producto", Toast.LENGTH_SHORT).show()

            }
        }
    }
}