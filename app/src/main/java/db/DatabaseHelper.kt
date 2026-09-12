package db


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(private val context: Context) : SQLiteOpenHelper(
    context,
    "ventas.db",
    null,
    1
) {

    override fun onCreate(db: SQLiteDatabase) {
        val sql = leerSQL(context)
        val sentencias = sql.split(";")
        for (sentencia in sentencias) {
            if (sentencia.trim().isNotEmpty()) {
                db.execSQL(sentencia)
            }
        }

    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {

    }

    private fun leerSQL(context: Context): String {
        return context.assets.open("productos.sql").bufferedReader().use {
            it.readText()
        }
    }
}