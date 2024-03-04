package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import android.content.Context
import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.BD
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Centros_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Centros_Schema

class Centros_Dao(context: Context) {

    //Instancia de clase BDUtil
    //private val databaseManager= BDUtil(context)
    private val dbHelper= BD(context)

    fun getAll(): List<Centros_Entity?> {
        var sql= "SELECT * FROM ${Centros_Schema.TABLE_NAME} ORDER BY ${Centros_Schema.CENTRO_FIELD} DESC"
       return query(sql)
    }

    fun getAllDetalles():List<Centros_Entity?>{
        var sql= "SELECT * FROM ${Centros_Schema.TABLE_NAME} ORDER BY ${Centros_Schema.CENTRO_FIELD} DESC"
        return  queryDetalles(sql)
    }


    //Funcion que muestra los centros disponibles
    fun query(sql: String): List<Centros_Entity?> {
        val db = dbHelper.openDatabaseRead()
        val cursor: Cursor = db.rawQuery(sql, null)
        var lista: MutableList<Centros_Entity?> = arrayListOf()

        try {
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    lista.add((Centros_Entity().fromCursorA(cursor)))
                }
            }

        }catch (e:Exception){
            e.printStackTrace()
        } finally {
            cursor.cerrar()
            db.close()
        }

        return lista
    }
    //Funcion que muestra los detalles de los centros

    fun queryDetalles(sql: String):List<Centros_Entity?>{
        val db= dbHelper.openDatabaseRead()
        val cursor: Cursor = db.rawQuery(sql, null)
        var lista: MutableList<Centros_Entity?> = arrayListOf()

        try{
            if(cursor!= null){
                while(cursor.moveToNext()){
                    lista.add(Centros_Entity().fromCursorB(cursor))
                }
            }
        }catch (e: Exception){
            e.printStackTrace()
        }finally {
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