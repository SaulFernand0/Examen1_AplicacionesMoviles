package com.example.examen

import android.content.ContentValues
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class productoAdapter (var scheduleList: ArrayList<productoModel>):

    RecyclerView.Adapter<productoAdapter.ProductViewHolder>() {
        var firebaseHelper:firebaseHelper = firebaseHelper()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
            var view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_producto, parent, false)
            return ProductViewHolder(view)
        }

        override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
            val productElement= scheduleList[position]
            holder.assignData(productElement )

            holder.deleteButton.setOnClickListener {
                Log.w(ContentValues.TAG, "INgeniero ")
                MaterialAlertDialogBuilder(holder.deleteButton.context)
                    .setMessage(holder.deleteButton.context.resources.getString(R.string.delete_dialog))
                    .setNegativeButton(holder.deleteButton.context.resources.getString(R.string.decline)) { dialog, which ->
                        // Respond to negative button press
                    }
                    .setPositiveButton(holder.deleteButton.context.resources.getString(R.string.accept)) { dialog, which ->
                        firebaseHelper.delProduct(productElement.idproducto).addOnSuccessListener {
                            deleteItem(position)
                            Toast.makeText(
                                holder.deleteButton.context,
                                "Producto eliminado correctamente",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    .show()
            }
        }
        fun deleteItem(position: Int){
            scheduleList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,itemCount)
        }

        override fun getItemCount(): Int = scheduleList.size

        class ProductViewHolder : RecyclerView.ViewHolder {
            var nombre: TextView
            var precio: TextView
            var stock: TextView
            var categoria: TextView
            var deleteButton: Button
            var editButton: Button



            constructor(view: View) : super(view) {
                this.nombre = view.findViewById(R.id.tv_product_item_name)
                this.precio= view.findViewById(R.id.tv_product_item_price)
                this.stock = view.findViewById(R.id.tv_product_item_stock)
                this.categoria = view.findViewById(R.id.tv_product_item_category_name)
                this.deleteButton = view.findViewById(R.id.btn_product_item_delete)
                this.editButton = view.findViewById(R.id.btn_product_item_edit)

            }

            fun assignData(productModel: productoModel) {
                this.nombre.text = productModel.nombre
                this.precio.text = productModel.precio.toString()
                this.stock.text = productModel.stock.toString()
                this.categoria.text = productModel.categoria


                editButton.setOnClickListener {

                    val intent = Intent(editButton.context, registrar::class.java)
                    intent.apply {
                        putExtra("idproducto",productModel.idproducto)
                        putExtra("nombre", productModel.nombre)
                        putExtra("precio", productModel.precio)
                        putExtra("stock", productModel.stock)
                        putExtra("categoria", productModel.categoria)
                    }
                    editButton.context.startActivity(intent)
                }

            }



        }
    }
