package com.advantys.brsmovilidaderp.Utils

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.advantys.brsmovilidaderp.Data.DataBase.BD
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.TipoOperacion_Entity

class BDUtil (context: Context){

    private val dbHelper: BD= BD(context)


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

    fun getAllFromQuery(sql: String): List<Map<String, String>> {
        val db = dbHelper.openDatabaseRead()
        val cursor: Cursor = db.rawQuery(sql, null)
        val data = mutableListOf<Map<String, String>>()

        while (cursor.moveToNext()) {
            val rowData = mutableMapOf<String, String>()
            for (i in 0 until cursor.columnCount) {
                rowData[cursor.getColumnName(i)] = cursor.getString(i)
            }
            data.add(rowData)
        }

        cursor.close()
        db.close()
        return data
    }
    fun getAll(tableName: String): List<Map<String, String>> {
      val db = dbHelper.openDatabaseRead()
      val cursor: Cursor = db.query(tableName, null, null, null, null, null, null)
      val data = mutableListOf<Map<String, String>>()

      while (cursor.moveToNext()) {
          val rowData = mutableMapOf<String, String>()
          for (i in 0 until cursor.columnCount) {
              rowData[cursor.getColumnName(i)] = cursor.getString(i)
          }
          data.add(rowData)
      }

      cursor.close()
      db.close()
      return data
  }



    fun query(sql: String): TipoOperacion_Entity? {
        val db = dbHelper.openDatabaseRead()
        val cursor: Cursor = db.rawQuery(sql, null)
        var modelo2 : TipoOperacion_Entity? = null

        try {
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    modelo2 = TipoOperacion_Entity.fromCursorA(cursor)
                }
            }

        }catch (e:Exception){
            e.printStackTrace()
        } finally {
            cursor.cerrar()
            db.close()
        }

        return modelo2
    }

    fun query2(sql: String): TipoOperacion_Entity? {
        lateinit var db: SQLiteDatabase
        lateinit var cursor: Cursor

        var modelo2 : TipoOperacion_Entity? = null

        try {
            db = dbHelper.openDatabaseRead()
            cursor = db.rawQuery(sql, null)
            if (!cursor.isEmpty()) {
                while (cursor.moveToNext()) {
                    modelo2 = TipoOperacion_Entity.fromCursorA(cursor)
                }
            }

        }catch (e:Exception){
            e.printStackTrace()
        } finally {
            cursor.cerrar()
            db.close()
        }

        return modelo2
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

    fun cerrarConexionDB(db: BD){

    }
    fun cerrar(cursor: Cursor, db: BD){
        db.cerrarConexion();

    }

}