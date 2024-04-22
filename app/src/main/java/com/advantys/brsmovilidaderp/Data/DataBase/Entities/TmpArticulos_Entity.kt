package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.TmpArticulos_Schema
import com.advantys.brsmovilidaderp.Utils.getBoolean
import com.advantys.brsmovilidaderp.Utils.getFloat
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString

class TmpArticulos_Entity (
    var fabricante: Short?=null,
    var articulo: String?=null,
    var venta1: Float?=null,
    var venta2: Float?=null,
    var regalo1: Float?=null,
    var regalo2: Float?=null,
    var marcado: Boolean?=null,
    var cMarcado: String?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): TmpArticulos_Entity {
            val modelo=  TmpArticulos_Entity()
            modelo.fabricante = cursor.getShort(TmpArticulos_Schema.FABRICANTE_FIELD,null)
            modelo.articulo = cursor.getString(TmpArticulos_Schema.ARTICULO_FIELD,null)
            modelo.venta1 = cursor.getFloat(TmpArticulos_Schema.VENTA1_FIELD,null)
            modelo.venta2 = cursor.getFloat(TmpArticulos_Schema.VENTA2_FIELD,null)
            modelo.regalo1 = cursor.getFloat(TmpArticulos_Schema.REGALO1_FIELD,null)
            modelo.regalo2 = cursor.getFloat(TmpArticulos_Schema.REGALO2_FIELD,null)
            modelo.marcado = cursor.getBoolean(TmpArticulos_Schema.LMARCADO_FIELD,null)
            modelo.cMarcado = cursor.getString(TmpArticulos_Schema.CMARCADO_FIELD,null)
            return modelo

        }
    }
}