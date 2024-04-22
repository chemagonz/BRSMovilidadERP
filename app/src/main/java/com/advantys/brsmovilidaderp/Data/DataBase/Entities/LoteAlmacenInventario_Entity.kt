package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.LoteAlmacenInventario_Schema
import com.advantys.brsmovilidaderp.Utils.getDate
import com.advantys.brsmovilidaderp.Utils.getFloat
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString
import java.util.Date

class LoteAlmacenInventario_Entity  (
    var fabricante: Short?=null,
    var articulo: String?=null,
    var lote: String?=null,
    var cantidad1: Float?=null,
    var cantidad2: Float?=null,
    var caducidad: Date?=null,
    var cantidad1Actual: Float?=null,
    var cantidad2Actual: Float?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): LoteAlmacenInventario_Entity {
            val modelo= LoteAlmacenInventario_Entity()
            modelo.fabricante = cursor.getShort(LoteAlmacenInventario_Schema.FABRICANTE_FIELD,null)
            modelo.articulo = cursor.getString(LoteAlmacenInventario_Schema.ARTICULO_FIELD,null)
            modelo.lote = cursor.getString(LoteAlmacenInventario_Schema.LOTE_FIELD,null)
            modelo.cantidad1 = cursor.getFloat(LoteAlmacenInventario_Schema.CANTIDAD1_FIELD,null)
            modelo.cantidad2 = cursor.getFloat(LoteAlmacenInventario_Schema.CANTIDAD2_FIELD,null)
            modelo.caducidad = cursor.getDate(LoteAlmacenInventario_Schema.CADUCIDAD_FIELD,null)
            modelo.cantidad1Actual = cursor.getFloat(LoteAlmacenInventario_Schema.CANTIDAD1ACTUAL_FIELD,null)
            modelo.cantidad2Actual = cursor.getFloat(LoteAlmacenInventario_Schema.CANTIDAD2ACTUAL_FIELD,null)
            return modelo
        }
    }
}