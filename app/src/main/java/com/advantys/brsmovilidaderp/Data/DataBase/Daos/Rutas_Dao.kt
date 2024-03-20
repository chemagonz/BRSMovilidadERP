package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Rutas_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Rutas_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil
import javax.inject.Inject

class Rutas_Dao @Inject constructor(private val databaseManager: BDUtil) {
    fun getAll():List<Rutas_Entity?>{
        var sql= "SELECT * FROM ${Rutas_Schema.TABLE_NAME} ORDER BY ${Rutas_Schema.RUTA_FIELD} DESC"
        return databaseManager.query(sql){ cursor ->
            Rutas_Entity.fromCursor(cursor)
        }
    }
    fun updateCheck(ruta: String?,valor:Boolean?){
        val valorConvertido = if (valor == true) 1 else 0
        var sql= "UPDATE ${Rutas_Schema.TABLE_NAME} SET  ${Rutas_Schema.LMARCADO_FIELD} ='${valorConvertido}' WHERE ${Rutas_Schema.RUTA_FIELD} ='${ruta}'"
        databaseManager.queryUp(sql)
    }
}