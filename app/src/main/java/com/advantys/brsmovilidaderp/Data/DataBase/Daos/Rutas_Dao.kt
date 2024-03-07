package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import android.content.Context
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Rutas_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Rutas_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil

class Rutas_Dao (context: Context) {

    private val ddatabaseManager: BDUtil=  BDUtil(context)

    fun getAll():List<Rutas_Entity?>{
        var sql= "SELECT * FROM ${Rutas_Schema.TABLE_NAME} ORDER BY ${Rutas_Schema.RUTA_FIELD} DESC"
        return ddatabaseManager.query(sql){ cursor ->
            Rutas_Entity.fromCursor(cursor)
        }
    }
}