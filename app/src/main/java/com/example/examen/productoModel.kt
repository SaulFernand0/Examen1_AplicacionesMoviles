package com.example.examen

class productoModel {
    var idproducto: String
    var nombre: String
    var precio: Double
    var stock: Int
    var categoria: String

    constructor(
        idproducto: String,
        nombre: String,
        precio: Double,
        stock: Int,
        categoria: String
    ) {
        this.idproducto = idproducto
        this.nombre = nombre
        this.precio = precio
        this.stock = stock
        this.categoria = categoria

    }
}