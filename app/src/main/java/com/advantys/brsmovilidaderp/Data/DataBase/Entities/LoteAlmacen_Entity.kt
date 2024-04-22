package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.LoteAlmacen_Schema
import com.advantys.brsmovilidaderp.Utils.getDate
import com.advantys.brsmovilidaderp.Utils.getFloat
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString
import java.util.Date

class LoteAlmacen_Entity  (
    var fabricante: Short?=null,
    var articulo: String?=null,
    var lote: String?=null,
    var cantidad1: Float?=null,
    var cantidad2: Float?=null,
    var caducidad: Date?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): LoteAlmacen_Entity {
            val modelo=  LoteAlmacen_Entity()
            modelo.fabricante = cursor.getShort(LoteAlmacen_Schema.FABRICANTE_FIELD,null)
            modelo.articulo = cursor.getString(LoteAlmacen_Schema.ARTICULO_FIELD,null)
            modelo.lote = cursor.getString(LoteAlmacen_Schema.LOTE_FIELD,null)
            modelo.cantidad1 = cursor.getFloat(LoteAlmacen_Schema.CANTIDAD1_FIELD,null)
            modelo.cantidad2 = cursor.getFloat(LoteAlmacen_Schema.CANTIDAD2_FIELD,null)
            modelo.caducidad = cursor.getDate(LoteAlmacen_Schema.CADUCIDAD_FIELD,null)
            return modelo
        }
    }
}