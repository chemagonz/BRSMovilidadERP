package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.EnvasesCliente_Schema
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString

class EnvasesCliente_Entity (
    var fabricante: Short?=null,
    var articulo: String?=null,
    var cliente: Int?=null,
    var delegacion: Short?=null,
    var facturaEnvases: String?=null,
    var tipoLinea: String?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): EnvasesCliente_Entity {
            val modelo = EnvasesCliente_Entity()
            modelo.fabricante = cursor.getShort(EnvasesCliente_Schema.FABRICANTE_FIELD,null)
            modelo.articulo = cursor.getString(EnvasesCliente_Schema.ARTICULO_FIELD,null)
            modelo.cliente = cursor.getInt(EnvasesCliente_Schema.CLIENTE_FIELD,null)
            modelo.delegacion = cursor.getShort(EnvasesCliente_Schema.DELEGACION_FIELD,null)
            modelo.facturaEnvases = cursor.getString(EnvasesCliente_Schema.FACTURAENVASES_FIELD,null)
            modelo.tipoLinea = cursor.getString(EnvasesCliente_Schema.TIPOLINEA_FIELD,null)
            return modelo
        }
    }
}