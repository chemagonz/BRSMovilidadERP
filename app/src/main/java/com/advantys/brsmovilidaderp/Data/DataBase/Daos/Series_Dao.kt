package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Series_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Series_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil
import javax.inject.Inject

class Series_Dao @Inject constructor( private val databaseManager:BDUtil) {
    fun getAll():List<Series_Entity?>{
        var sql= "SELECT * FROM ${Series_Schema.TABLE_NAME} ORDER BY ${Series_Schema.SERIE_FIELD} DESC"
        return databaseManager.query(sql){ cursor ->
            Series_Entity.fromCursor(cursor)
        }
    }
    fun getAllDetalles(serie: String?):Series_Entity?{
        var sql= "SELECT * FROM ${Series_Schema.TABLE_NAME} WHERE ${Series_Schema.SERIE_FIELD} ='${serie}'"
    return databaseManager.queryDetalles(sql) { cursor ->
        Series_Entity.fromCursor(cursor)
        }
    }
}