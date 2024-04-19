package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.ClientesDescuento_Schema
import com.advantys.brsmovilidaderp.Utils.getDate
import com.advantys.brsmovilidaderp.Utils.getFloat
import com.advantys.brsmovilidaderp.Utils.getInt
import java.util.Date

class ClientesDescuento_Entity (
    var id: Int? = null,
    var cliente: Int? = null,
    var familia: Int? = null,
    var porcCal: Float? = null,
    var porcCal2: Float? = null,
    var desde: Date? = null,
    var hasta: Date? = null,
    var impCal: Float? = null,
    var impCal2: Float? = null
) {
    companion object {
        fun fromCursor(cursor: Cursor): ClientesDescuento_Entity {
            val modelo = ClientesDescuento_Entity()
            modelo.id = cursor.getInt(ClientesDescuento_Schema.ID_FIELD,null)
            modelo.cliente = cursor.getInt(ClientesDescuento_Schema.CLIENTE_FIELD,null)
            modelo.familia = cursor.getInt(ClientesDescuento_Schema.FAMILIA_FIELD,null)
            modelo.porcCal = cursor.getFloat(ClientesDescuento_Schema.PORCCAL_FIELD,null)
            modelo.porcCal2 = cursor.getFloat(ClientesDescuento_Schema.PORCCAL2_FIELD,null)
            modelo.desde = cursor.getDate(ClientesDescuento_Schema.DESDE_FIELD,null)
            modelo.hasta = cursor.getDate(ClientesDescuento_Schema.HASTA_FIELD,null)
            modelo.impCal = cursor.getFloat(ClientesDescuento_Schema.IMPCAL_FIELD,null)
            modelo.impCal2 = cursor.getFloat(ClientesDescuento_Schema.IMPCAL2_FIELD,null)
            return modelo
        }
    }
}