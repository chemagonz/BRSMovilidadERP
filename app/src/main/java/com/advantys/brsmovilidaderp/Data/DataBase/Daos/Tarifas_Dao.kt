package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Tarifas_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.TarifasArticulo_Schema
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Tarifas_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil
import javax.inject.Inject

class Tarifas_Dao @Inject constructor(private val databaseManager: BDUtil) {

    fun getNombreTarifa(tarifa:Short?): Tarifas_Entity? {
        var sql= "SELECT ${Tarifas_Schema.NOMBRE_FIELD}, ${TarifasArticulo_Schema.TARIFA_FIELD} FROM ${Tarifas_Schema.TABLE_NAME} WHERE ${TarifasArticulo_Schema.TARIFA_FIELD} = ${tarifa}"
        return  databaseManager.queryDetalles(sql){cursor ->
            Tarifas_Entity.fromCursor(cursor)
        }
    }
}