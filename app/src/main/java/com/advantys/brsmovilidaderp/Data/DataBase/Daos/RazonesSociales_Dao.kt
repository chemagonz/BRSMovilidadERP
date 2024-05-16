package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.RazonesSociales_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.RazonesSociales_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil
import javax.inject.Inject

class RazonesSociales_Dao @Inject constructor(private val databaseManager: BDUtil) {

    fun obtenerRazonesSociales(codigo: Int): Map<Int, RazonesSociales_Entity> {
        val sql = "SELECT DISTINCT ${RazonesSociales_Schema.RAZONSOCIAL_FIELD},${RazonesSociales_Schema.NOMBRE_FIELD}  FROM ${RazonesSociales_Schema.TABLE_NAME}"
        return databaseManager.queryMap(sql) { cursor ->
            RazonesSociales_Entity.fromCursorMap(cursor)
        }
    }
}