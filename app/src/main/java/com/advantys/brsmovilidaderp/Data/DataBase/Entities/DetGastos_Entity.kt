package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.DetGastos_Schema
import com.advantys.brsmovilidaderp.Utils.getFloat
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getString

class DetGastos_Entity (
    var identificador: Int?=null,
    var jornada: Int?=null,
    var tipoGasto: Int?=null,
    var nota: String? = null,
    var precio: Float? = null,
    var cantidad: Int? = null,
    var total: Float? = null,
    var kilometros: Int? = null,
    var litros: Float? = null
) {
    companion object {
        fun fromCursor(cursor: Cursor): DetGastos_Entity {
            val modelo = DetGastos_Entity()
            modelo.identificador = cursor.getInt(DetGastos_Schema.IDENTIFICADOR_FIELD,null)
            modelo.jornada = cursor.getInt(DetGastos_Schema.JORNADA_FIELD,null)
            modelo.tipoGasto = cursor.getInt(DetGastos_Schema.TIPOGASTO_FIELD,null)
            modelo.nota = cursor.getString(DetGastos_Schema.NOTA_FIELD,null)
            modelo.precio = cursor.getFloat(DetGastos_Schema.PRECIO_FIELD,null)
            modelo.cantidad = cursor.getInt(DetGastos_Schema.CANTIDAD_FIELD,null)
            modelo.total = cursor.getFloat(DetGastos_Schema.TOTAL_FIELD,null)
            modelo.kilometros = cursor.getInt(DetGastos_Schema.KILOMETROS_FIELD,null)
            modelo.litros = cursor.getFloat(DetGastos_Schema.LITROS_FIELD,null)
            return modelo
        }
    }
}