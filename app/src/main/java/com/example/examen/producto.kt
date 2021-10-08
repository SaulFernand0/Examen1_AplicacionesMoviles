package com.example.examen

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class producto : AppCompatActivity() {

    lateinit var deleteButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producto)
        Log.w(ContentValues.TAG,"Ingeniero")

        deleteButton = findViewById(R.id.btn_product_item_delete)
        deleteButton.setOnClickListener {

        }
    }
}