package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import android.content.Context
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Series_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Series_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil

class Series_Dao (context: Context) {
    private val databaseManager:BDUtil= BDUtil(context)
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