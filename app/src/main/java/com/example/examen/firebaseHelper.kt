package com.example.examen

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class firebaseHelper {

    private val firestore = Firebase.firestore

    private val producto= firestore.collection("producto")

    //Registrar
    fun createProduct(product: productoModel): Task<DocumentReference> {
        val newProduct = hashMapOf(
            "nombre" to product.nombre,
            "precio" to product.precio,
            "stock" to product.stock,
            "categoria" to product.categoria
        )
        return producto.add(newProduct)
    }

    //Listar
    fun listProduct(): Task<QuerySnapshot> {
        return producto.get()
    }

    //Actualizar
    fun updateProduct(product: productoModel): Task<Void> {
        val newProduct: Map<String, *> = hashMapOf(
            "nombre" to product.nombre,
            "precio" to product.precio,
            "stock" to product.stock,
            "categoria" to product.categoria
        )
        return producto.document(product.idproducto.toString()).update(newProduct)
    }

    //Eliminar
    fun delProduct(idproducto: String): Task<Void> {
        return producto.document(idproducto).delete()
    }

    fun findProductById(idproduct: String): Task<DocumentSnapshot> {
        return producto.document(idproduct).get()
    }
}