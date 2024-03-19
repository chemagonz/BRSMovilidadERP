package com.advantys.brsmovilidaderp.Data.DataBase.Daos
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Centros_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Series_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Centros_Schema
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Series_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil
import javax.inject.Inject


class Centros_Dao @Inject constructor(private val databaseManager:BDUtil) {
    fun getAll(): List<Centros_Entity?> {
        var sql= "SELECT * FROM ${Centros_Schema.TABLE_NAME} ORDER BY ${Centros_Schema.CENTRO_FIELD} DESC"
       return databaseManager.query(sql) { cursor ->
           Centros_Entity.fromCursor(cursor)
       }
    }
    fun getSerie(codigo:String?):Series_Entity? {
        var sql = "SELECT ${Series_Schema.NOMBRE_FIELD} FROM ${Series_Schema.TABLE_NAME} WHERE ${Series_Schema.SERIE_FIELD} = '${codigo}'"
        return databaseManager.queryDetalles(sql){ cursor ->
            Series_Entity.fromCursor(cursor)
        }
    }
    fun getAllDetalles(centro:Int?):Centros_Entity?{
        var sql= "SELECT * FROM ${Centros_Schema.TABLE_NAME} WHERE ${Centros_Schema.CENTRO_FIELD} = ${centro}"
        return  databaseManager.queryDetalles(sql){cursor ->
            Centros_Entity.fromCursor(cursor)
        }
    }
}