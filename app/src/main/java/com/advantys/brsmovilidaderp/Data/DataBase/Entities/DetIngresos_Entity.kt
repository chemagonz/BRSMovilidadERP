package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.DetIngresos_Schema
import com.advantys.brsmovilidaderp.Utils.getDate
import com.advantys.brsmovilidaderp.Utils.getFloat
import com.advantys.brsmovilidaderp.Utils.getInt
import java.util.Date

class DetIngresos_Entity (
    var identificador: Int?=null,
    var jornada: Int?=null,
    var banco: Int?=null,
    var fecha: Date?=null,
    var importe: Float?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): DetIngresos_Entity {
            val modelo = DetIngresos_Entity()
            modelo.identificador = cursor.getInt(DetIngresos_Schema.IDENTIFICADOR_FIELD,null)
            modelo.jornada = cursor.getInt(DetIngresos_Schema.JORNADA_FIELD,null)
            modelo.banco = cursor.getInt(DetIngresos_Schema.BANCO_FIELD,null)
            modelo.fecha = cursor.getDate(DetIngresos_Schema.FECHA_FIELD,null)
            modelo.importe = cursor.getFloat(DetIngresos_Schema.IMPORTE_FIELD,null)
            return modelo
        }
    }
}