package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.DetMovAlm_Schema
import com.advantys.brsmovilidaderp.Utils.getFloat
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString

class DetMovAlm_Entity (
    var movimiento: Int?=null,
    var orden: Int?=null,
    var tipo: Short?=null,
    var fabricante: Short? = null,
    var articulo: String? = null,
    var cantidad1: Float?=null,
    var cantidad2: Float?=null,
    var unidadesCaja: Float?=null,
    var pvp: Float?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): DetMovAlm_Entity {
            val modelo = DetMovAlm_Entity()
            modelo.movimiento = cursor.getInt(DetMovAlm_Schema.MOVIMIENTO_FIELD,null)
            modelo.orden = cursor.getInt(DetMovAlm_Schema.ORDEN_FIELD,null)
            modelo.tipo = cursor.getShort(DetMovAlm_Schema.TIPO_FIELD,null)
            modelo.fabricante = cursor.getShort(DetMovAlm_Schema.FABRICANTE_FIELD,null)
            modelo.articulo = cursor.getString(DetMovAlm_Schema.ARTICULO_FIELD,null)
            modelo.cantidad1 = cursor.getFloat(DetMovAlm_Schema.CANTIDAD1_FIELD,null)
            modelo.cantidad2 = cursor.getFloat(DetMovAlm_Schema.CANTIDAD2_FIELD,null)
            modelo.unidadesCaja = cursor.getFloat(DetMovAlm_Schema.UNIDADESCAJA_FIELD,null)
            modelo.pvp = cursor.getFloat(DetMovAlm_Schema.PVP_FIELD,null)
            return modelo
        }
    }
}