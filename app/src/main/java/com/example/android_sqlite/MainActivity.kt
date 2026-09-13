package com.example.android_sqlite

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import db.DatabaseHelper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val dbHelper = DatabaseHelper(this)
        val db = dbHelper.writableDatabase

        val contenedorVentas = findViewById<LinearLayout>(
            R.id.contenedor_ventas
        )

        val cursor = db.rawQuery(
            "SELECT * FROM ventas",
            null
        )

        while (cursor.moveToNext()) {

            val codigo = cursor.getString(
                cursor.getColumnIndexOrThrow("codigo")
            )

            val nombre = cursor.getString(
                cursor.getColumnIndexOrThrow("nombre")
            )

            val precio = cursor.getDouble(
                cursor.getColumnIndexOrThrow("precio")
            )

            val cantidad = cursor.getInt(
                cursor.getColumnIndexOrThrow("cantidad")
            )

            val tipo = cursor.getString(
                cursor.getColumnIndexOrThrow("tipo")
            )

            val fechaVenta = cursor.getString(
                cursor.getColumnIndexOrThrow("fecha_venta")
            )

            // Crear una tarjeta usando item_venta.xml
            val tarjeta = LayoutInflater.from(this).inflate(
                R.layout.item_venta,
                contenedorVentas,
                false
            )

            // Buscar los TextView de la tarjeta
            val tvTipo = tarjeta.findViewById<TextView>(R.id.tv_tipo)
            val tvCodigo = tarjeta.findViewById<TextView>(R.id.tv_codigo)
            val tvNombre = tarjeta.findViewById<TextView>(R.id.tv_nombre)
            val tvPrecio = tarjeta.findViewById<TextView>(R.id.tv_precio)
            val tvCantidad = tarjeta.findViewById<TextView>(R.id.tv_cantidad)
            val tvFecha = tarjeta.findViewById<TextView>(R.id.tv_fecha)

            // Colocar los datos de SQLite en la tarjeta
            tvTipo.text = tipo.uppercase()
            tvCodigo.text = "Código: $codigo"
            tvNombre.text = "Producto: $nombre"
            tvPrecio.text = "Precio: S/ $precio"
            tvCantidad.text = "Cantidad: $cantidad"
            tvFecha.text = "Fecha: $fechaVenta"

            // Cambiar color según el tipo de venta
            if (tipo == "factura") {
                tvTipo.setTextColor(Color.RED)
            } else if (tipo == "boleta") {
                tvTipo.setTextColor(Color.GREEN)
            }

            // Agregar la tarjeta al contenedor
            contenedorVentas.addView(tarjeta)
        }

        cursor.close()
        db.close()

        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v, insets ->

            val systemBars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }
    }
}