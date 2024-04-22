package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.CobrosACuenta_Schema
import com.advantys.brsmovilidaderp.Utils.getDate
import com.advantys.brsmovilidaderp.Utils.getDouble
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getShort
import java.util.Date

class CobrosACuenta_Entity (
    var acuenta: Int? = null,
    var cliente: Int? = null,
    var nDelegacion: Short? = null,
    var fecha: Date? = null,
    var centro: Short? = null,
    var importe: Double? =null,
    var aplicado: Double? = null
) {
    companion object {
        fun fromCursor(cursor: Cursor): CobrosACuenta_Entity {
            val modelo = CobrosACuenta_Entity()
            modelo.acuenta = cursor.getInt(CobrosACuenta_Schema.ACUENTA_FIELD,null)
            modelo.cliente = cursor.getInt(CobrosACuenta_Schema.CLIENTE_FIELD,null)
            modelo.nDelegacion = cursor.getShort(CobrosACuenta_Schema.DELEGACION_FIELD,null)
            modelo.fecha = cursor.getDate(CobrosACuenta_Schema.FECHA_FIELD,null)
            modelo.centro = cursor.getShort(CobrosACuenta_Schema.CENTRO_FIELD,null)
            modelo.importe = cursor.getDouble(CobrosACuenta_Schema.IMPORTE_FIELD,null)
            modelo.aplicado = cursor.getDouble(CobrosACuenta_Schema.APLICADO_FIELD,null)
            return modelo
        }
    }
}