package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.DetRecuentos_Schema
import com.advantys.brsmovilidaderp.Utils.getFloat
import com.advantys.brsmovilidaderp.Utils.getInt

class DetRecuentos_Entity (
    var jornada: Int?=null,
    var moneda: Int?=null,
    var cantidad: Float?=null,
    var total: Float?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): DetRecuentos_Entity {
            val modelo = DetRecuentos_Entity()
            modelo.jornada = cursor.getInt(DetRecuentos_Schema.JORNADA_FIELD,null)
            modelo.moneda = cursor.getInt(DetRecuentos_Schema.MONEDA_FIELD,null)
            modelo.cantidad = cursor.getFloat(DetRecuentos_Schema.CANTIDAD_FIELD,null)
            modelo.total = cursor.getFloat(DetRecuentos_Schema.TOTAL_FIELD,null)
            return modelo
        }
    }
}