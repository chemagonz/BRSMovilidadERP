package com.advantys.brsmovilidaderp.Data.DataBase.Daos
import android.content.Context
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Centros_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Centros_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil


class Centros_Dao (context:Context) {

    private val databaseManager:BDUtil= BDUtil(context)

    fun getAll(): List<Centros_Entity?> {
        var sql= "SELECT * FROM ${Centros_Schema.TABLE_NAME} ORDER BY ${Centros_Schema.CENTRO_FIELD} DESC"
       return databaseManager.query(sql) { cursor ->
           Centros_Entity.fromCursor(cursor)
       }
    }
    fun getAllDetalles(centro:Int?):Centros_Entity?{
        var sql= "SELECT * FROM ${Centros_Schema.TABLE_NAME} WHERE ${Centros_Schema.CENTRO_FIELD} = ${centro}"
        return  databaseManager.queryDetalles(sql){cursor ->
            Centros_Entity.fromCursor(cursor)
        }
    }
}