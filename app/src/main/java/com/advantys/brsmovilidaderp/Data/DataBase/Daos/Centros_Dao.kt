package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import android.content.Context
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Centros_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Centros_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil

class Centros_Dao(context: Context) {

    //Instancia de clase BDUtil
    private val databaseManager= BDUtil(context)
    fun getAll(): List<Centros_Entity?> {
        var sql= "SELECT * FROM ${Centros_Schema.TABLE_NAME} ORDER BY ${Centros_Schema.CENTRO_FIELD} DESC"
       return databaseManager.query(sql) { cursor ->
           Centros_Entity.fromCursorA(cursor)
       }
    }
    fun getAllDetalles():List<Centros_Entity?>{
        var sql= "SELECT * FROM ${Centros_Schema.TABLE_NAME} ORDER BY ${Centros_Schema.CENTRO_FIELD} DESC"
        return  databaseManager.queryDetalles(sql){cursor ->
            Centros_Entity.fromCursorB(cursor)
        }
    }
}