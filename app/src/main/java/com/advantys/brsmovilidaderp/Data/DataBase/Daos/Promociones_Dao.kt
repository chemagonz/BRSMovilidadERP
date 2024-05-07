package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Promociones_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Promociones_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil
import javax.inject.Inject

class Promociones_Dao @Inject constructor(private val databaseManager: BDUtil) {

    fun getPromocionesGenerales(): List<Promociones_Entity>{

        val sql = "SELECT ${Promociones_Schema.PROMOCION_FIELD}, ${Promociones_Schema.DESCRIPCION_FIELD} FROM ${Promociones_Schema.TABLE_NAME}"
        return databaseManager.query(sql){cursor ->
            Promociones_Entity.fromCursor(cursor)
        }
    }
}