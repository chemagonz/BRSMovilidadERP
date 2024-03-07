package com.advantys.brsmovilidaderp.Data.DataBase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Environment.getExternalStorageDirectory

val DATABASE_NAME="BRSAndroid.db"

class BD (contexto: Context): SQLiteOpenHelper(contexto, DATABASE_NAME, null, 1){

    private val DATABASE_RUTA = getExternalStorageDirectory().path+ "/BRSAndroid/"



    override fun onCreate(p0: SQLiteDatabase?) {
        TODO("Not yet implemented")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun openDatabaseRead(): SQLiteDatabase= openDatabase(SQLiteDatabase.OPEN_READONLY)
    fun openDatabaseWrite(): SQLiteDatabase= openDatabase(SQLiteDatabase.OPEN_READWRITE)

    fun openDatabase(modo:Int): SQLiteDatabase{

        try {
            val db= SQLiteDatabase.openDatabase("${DATABASE_RUTA}${DATABASE_NAME}" , null, modo)
            db.rawQuery("PRAGMA journal_mode = DELETE", null)
            return db
        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException("Error al abrir la base de datos: $e")
        }
    }
    fun cerrarConexion(){
        if(this !=null) this.close()
    }
}