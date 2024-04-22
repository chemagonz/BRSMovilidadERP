package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.DetInventario_Schema
import com.advantys.brsmovilidaderp.Utils.getFloat
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getString

class DetInventario_Entity (
    var inventario: Int?=null,
    var articulo: String?=null,
    var fabricante: Int?=null,
    var cantidad1: Float?=null,
    var cantidad2: Float?=null,
    var orden: Int? = null,
    var pvp: Float?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): DetInventario_Entity {
            val modelo = DetInventario_Entity()
            modelo.inventario = cursor.getInt(DetInventario_Schema.INVENTARIO_FIELD,null)
            modelo.articulo = cursor.getString(DetInventario_Schema.ARTICULO_FIELD,null)
            modelo.fabricante = cursor.getInt(DetInventario_Schema.FABRICANTE_FIELD,null)
            modelo.cantidad1 = cursor.getFloat(DetInventario_Schema.CANTIDAD1_FIELD,null)
            modelo.cantidad2 = cursor.getFloat(DetInventario_Schema.CANTIDAD2_FIELD,null)
            modelo.orden = cursor.getInt(DetInventario_Schema.ORDEN_FIELD,null)
            modelo.pvp = cursor.getFloat(DetInventario_Schema.PVP_FIELD,null)
            return modelo
        }
    }
}