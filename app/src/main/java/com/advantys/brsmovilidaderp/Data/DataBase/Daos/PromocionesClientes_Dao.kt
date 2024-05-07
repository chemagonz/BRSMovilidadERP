package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.PromocionesClientes_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.PromocionesClientes_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil
import javax.inject.Inject

class PromocionesClientes_Dao @Inject constructor(private val databaseManager: BDUtil) {

    fun getPromocionesParticulares(): List<PromocionesClientes_Entity>{

        val sql = "SELECT  ${PromocionesClientes_Schema.PROMOCION_FIELD}, ${PromocionesClientes_Schema.DESCRIPCION_FIELD} FROM ${PromocionesClientes_Schema.TABLE_NAME} ORDER BY ${PromocionesClientes_Schema.CLIENTE_FIELD},${PromocionesClientes_Schema.DELEGACION_FIELD},${PromocionesClientes_Schema.PROMOCION_FIELD} "
        return databaseManager.query(sql){cursor ->
            PromocionesClientes_Entity.fromCursor(cursor)
        }
    }
}