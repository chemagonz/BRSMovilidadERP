package com.advantys.brsmovilidaderp.Utils

import android.content.ContentValues
import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.BD
import javax.inject.Inject

class BDUtil @Inject constructor (private val dbHelper:BD){


    fun insert(tabla: String, parametros : ContentValues) {
        val db = dbHelper.openDatabaseWrite()
        db.insert(tabla, null, parametros)
        db.close()
    }

    fun insertIfNotExists(tabla: String, parametros : ContentValues, where: String) {
        if(!existe(tabla, where))
            insert(tabla, parametros)
    }
    fun update(tabla: String, parametros : ContentValues, where: String) {
        val db = dbHelper.openDatabaseWrite()
        db.update(tabla, parametros, where, null)
        db.close()
    }
    fun upsert(tabla: String, parametros : ContentValues, where: String) {
        if(existe(tabla, where))
            update(tabla, parametros, where)
        else insert(tabla, parametros)
    }
    fun delete(tabla: String, where: String) {
        val db = dbHelper.openDatabaseWrite()
        db.delete(tabla, where, null)
        db.close()
    }

    fun deleteTabla(tabla: String) {
        val db = dbHelper.openDatabaseWrite()
        db.delete(tabla, null, null)
        db.close()
    }

    fun existe(tabla: String, where: String): Boolean {
        val db = dbHelper.openDatabaseRead()
        val selectQuery = "SELECT COUNT(*) FROM ${tabla} WHERE ${where}"
        val cursor = db.rawQuery(selectQuery, null)
        var resultado = false

        try {
            if (cursor.count != 0) {
                if (cursor.count > 0) resultado = true
            }
        }catch (e:Exception){
            e.printStackTrace()
        } finally {
            cursor.cerrar()
            db.close()
        }

        return resultado
    }

    //Funcion generica para mostrar una lista    04/03/2024
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
    fun queryUp(sql:String){
        val db = dbHelper.openDatabaseRead()
        try{
            db.execSQL(sql)
        }catch (e:Exception){
            e.printStackTrace()
        }finally{
            db.close()
        }
    }
    fun <T> queryDetalles(sql:String, fromCursor:(cursor:Cursor)->T):T?{
        val db = dbHelper.openDatabaseRead()
        val cursor: Cursor = db.rawQuery(sql, null)
        var result: T? = null
        try{
            if (cursor!=null&&cursor.moveToNext()){
                result=fromCursor(cursor)
            }
        }catch(e:Exception){
            e.printStackTrace()
        }finally {
            cursor?.close()
            db.close()
        }
    return result
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
    ///region Funciones SelectScalar

    fun getSelectScalarInt(sql: String) :Int {
        return getSelectScalar(sql) as Int
    }

    fun getSelectScalarBoolean(sql: String) :Boolean {
        return getSelectScalar(sql) as Boolean
    }

    fun getSelectScalarString(sql: String) :String {
        return getSelectScalar(sql) as String
    }

    fun getSelectScalarFloat(sql: String) :Float {
        return getSelectScalar(sql) as Float
    }

    fun getSelectScalar(sql: String): Any? {
        var db = dbHelper.openDatabaseRead()
        val cursor = db.rawQuery(sql, null)
        var resultado: Any? = null

        try {
            if (!cursor.isEmpty()) {
                resultado = cursor.getColumnIndex(cursor.getColumnName(1))
            }
        }catch (e:Exception){
            e.printStackTrace()
        } finally {
            cursor.cerrar()
            db.close()
        }

        return resultado
    }

    fun Cursor.getBooleanInt(column: Int) : Boolean {
        var resultado = true
        if(!this.esNulo()) {
            if (this.count > 0){
                this.moveToFirst()
                resultado = false
            }
        }
        return resultado
    }

    fun cerrarConexionDB(db: BD){

    }
    fun cerrar(cursor: Cursor, db: BD){
        db.cerrarConexion();

    }



}

fun Cursor.esNulo(nombreColumna:String):Boolean{
    return this.isNull(this.getColumnIndexOrThrow(nombreColumna))
}

fun Cursor.getInt(nombreColumna: String):Int?{
    return this.getInt(nombreColumna, null)
}

fun Cursor.getString(nombreColumna: String): String?{
    if(this.esNulo(nombreColumna)) return null
    else return this.getString(this.getColumnIndexOrThrow(nombreColumna))
}

fun Cursor.getInt(nombreColumna: String, valorDefecto: Int?):Int?{
    if(this.esNulo(nombreColumna)) return valorDefecto
    else return this.getInt(this.getColumnIndexOrThrow(nombreColumna))
}

fun Cursor.getString(nombreColumna: String, valorDefecto: String?):String?{
if(this.esNulo(nombreColumna)) return valorDefecto
    else return this.getString(this.getColumnIndexOrThrow(nombreColumna))
}