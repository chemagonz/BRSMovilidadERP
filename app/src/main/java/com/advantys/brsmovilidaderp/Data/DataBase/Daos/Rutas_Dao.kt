package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import android.content.Context
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Rutas_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Rutas_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil

class Rutas_Dao (context: Context) {

    private val databaseManager: BDUtil=  BDUtil(context)

    fun getAll():List<Rutas_Entity?>{
        var sql= "SELECT * FROM ${Rutas_Schema.TABLE_NAME} ORDER BY ${Rutas_Schema.RUTA_FIELD} DESC"
        return databaseManager.query(sql){ cursor ->
            Rutas_Entity.fromCursor(cursor)
        }

    }

    fun updateCheck(valor:Boolean?, ruta: String?){
        var sql= "UPDATE ${Rutas_Schema.TABLE_NAME} SET  ${Rutas_Schema.LMARCADO_FIELD} ='${valor}' WHERE ${Rutas_Schema.RUTA_FIELD} ='${ruta}'"
         databaseManager.queryUp(sql)
    }
}