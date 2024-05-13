package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.DetUltAlbaranes_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.DetUltAlbaranes_Schema
import com.advantys.brsmovilidaderp.Domain.Models.CabPedido
import com.advantys.brsmovilidaderp.Utils.BDUtil
import javax.inject.Inject

class DetUltAlbaranes_Dao @Inject constructor(private val databaseManager: BDUtil) {

    fun ObtenerLineasPedido(pedido: CabPedido, esHistorico: Boolean): List<DetUltAlbaranes_Entity?> {
        val sql = "SELECT * FROM ${DetUltAlbaranes_Schema.TABLE_NAME} WHERE ${DetUltAlbaranes_Schema.TABLE_NAME}.${DetUltAlbaranes_Schema.SERIE_FIELD} = '${pedido.serie}' AND ${DetUltAlbaranes_Schema.TABLE_NAME}.${DetUltAlbaranes_Schema.ALBARAN_FIELD} AND ${DetUltAlbaranes_Schema.TABLE_NAME}.${DetUltAlbaranes_Schema.TIPOLINEA_FIELD} <> 'D' ORDER BY ${DetUltAlbaranes_Schema.TABLE_NAME}.${DetUltAlbaranes_Schema.PORCIVA_FIELD} , ${DetUltAlbaranes_Schema.TABLE_NAME}.${DetUltAlbaranes_Schema.PORCREC_FIELD} "
        return databaseManager.query(sql) { cursor ->
            DetUltAlbaranes_Entity.fromCursor(cursor)
        }
    }
}