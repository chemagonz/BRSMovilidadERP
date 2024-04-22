package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.DepositoClientes_Schema
import com.advantys.brsmovilidaderp.Utils.getDate
import com.advantys.brsmovilidaderp.Utils.getFloat
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getString
import java.util.Date

class DepositoClientes_Entity (
    var cliente: Int? = null,
    var articulo: String?=null,
    var fechaAlta: Date?= null,
    var fechaUltModif: Date?= null,
    var cantidad: Float? = null
) {
    companion object {
        fun fromCursor(cursor: Cursor): DepositoClientes_Entity {
            val modelo = DepositoClientes_Entity()
            modelo.cliente = cursor.getInt(DepositoClientes_Schema.CLIENTE_FIELD, null)
            modelo.articulo = cursor.getString(DepositoClientes_Schema.ARTICULO_FIELD, null)
            modelo.fechaAlta = cursor.getDate(DepositoClientes_Schema.FECHAALTA_FIELD, null)
            modelo.fechaUltModif = cursor.getDate(DepositoClientes_Schema.FECHAULTMODIF_FIELD, null)
            modelo.cantidad = cursor.getFloat(DepositoClientes_Schema.CANTIDAD_FIELD,null)
            return modelo
        }
    }
}