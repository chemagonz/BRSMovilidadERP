package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Articulos_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Articulos_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil
import javax.inject.Inject

class Articulos_Dao @Inject constructor(private val databaseManager: BDUtil) {

    fun getAll(): List<Articulos_Entity?> {
        var sql= "SELECT * FROM ${Articulos_Schema.TABLE_NAME} ORDER BY ${Articulos_Schema.ARTICULO_FIELD} DESC"
        return databaseManager.query(sql) { cursor ->
            Articulos_Entity.fromCursor(cursor)
        }
    }

    fun getFilter(tipoConsulta: String?):List<Articulos_Entity?>{
        var sql= "SELECT * FROM ${Articulos_Schema.TABLE_NAME} WHERE ${Articulos_Schema.NOMBRE_FIELD} LIKE '${tipoConsulta}%'"
        return databaseManager.query(sql){cursor ->
            Articulos_Entity.fromCursor(cursor)
        }
    }
}