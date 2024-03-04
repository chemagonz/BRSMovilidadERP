package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import android.content.Context
import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.BD
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.TipoOperacion_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.TipoOperacion_Schema


class TipoOperacion_Dao(context: Context){

    //Instancia de la clase BDUtil
   // val databaseManager: BDUtil= BDUtil(context)

    private val dbHelper: BD = BD(context)

     fun getAll():List<TipoOperacion_Entity?>{
        var sql= "SELECT * FROM ${TipoOperacion_Schema.TABLE_NAME} ORDER BY ${TipoOperacion_Schema.TIPOOPERACION_FIELD} ASC"
         return query(sql) { cursor ->
             TipoOperacion_Entity.fromCursorA(cursor)
         }
     }



    /*fun query(sql: String): List<TipoOperacion_Entity?> {
        val db = dbHelper.openDatabaseRead()
        val cursor: Cursor = db.rawQuery(sql, null)
        var lista: MutableList<TipoOperacion_Entity?> = arrayListOf()

        try {
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    lista.add((TipoOperacion_Entity.fromCursorA(cursor)))
                }
            }

        }catch (e:Exception){
            e.printStackTrace()
        } finally {
            cursor.cerrar()
            db.close()
        }

        return lista
    }*/

    fun <T> query(sql: String, fromCursor: (cursor: Cursor) -> T): List<T>{
        val db = dbHelper.openDatabaseRead()
        val cursor: Cursor = db.rawQuery(sql, null)
        val lista: MutableList<T> = arrayListOf()

        try {
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    lista.add(fromCursor(cursor))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            cursor.cerrar()
            db.close()
        }

        return lista
    }


    ///region  Utilidades

    fun Any?.esNulo() = this == null

    fun Cursor.cerrar(){
        if(!this.esNulo()) this.close()
    }

    fun Cursor.isEmpty() : Boolean {
        var resultado = true
        if(!this.esNulo()) {
            if (this.count > 0){
                this.moveToFirst()
                resultado = false
            }
        }
        return resultado
    }
 }















