package com.example.examen

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class listar : AppCompatActivity() {

    lateinit var recycler: RecyclerView
    var firebaseHelper: firebaseHelper = firebaseHelper()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar)
        recycler = findViewById(R.id.rv_list_product)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        firebaseHelper.listProduct().addOnSuccessListener {productQS->

            var productoList: ArrayList<productoModel> = arrayListOf()

            productQS.documents.forEach { productDocuments->
                productoList.add(
                    productoModel(
                        productDocuments.id,
                        productDocuments.data?.get("nombre") as String,
                        (productDocuments.data?.get("precio") as Double),
                        (productDocuments.data?.get("stock") as Long).toInt(),
                        productDocuments.data?.get("categoria") as String
                    )
                )
            }

            productoList.forEach {
                Log.w(ContentValues.TAG, "${it.idproducto}")
            }
            recycler.adapter = productoAdapter(
                productoList
            )

            var button4: Button = findViewById(R.id.button4)

            button4.setOnClickListener {
                startActivity(Intent(this,menu::class.java))
            }
        }
    }
}