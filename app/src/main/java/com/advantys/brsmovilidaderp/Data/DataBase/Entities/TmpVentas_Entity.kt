package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.TmpVentas_Schema
import com.advantys.brsmovilidaderp.Utils.getBoolean
import com.advantys.brsmovilidaderp.Utils.getDate
import com.advantys.brsmovilidaderp.Utils.getFloat
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString
import java.util.Date

class TmpVentas_Entity (
    var fecha: Date?=null,
    var tipoLinea: String?=null,
    var fabricante: Short?=null,
    var articulo: String?=null,
    var unidadesCaja: Float?=null,
    var concepto: String?=null,
    var cantidad1: Float?=null,
    var cantidad2: Float?=null,
    var pvp: Float?=null,
    var venta1: Float?=null,
    var venta2: Float?=null,
    var regalo1: Float?=null,
    var regalo2: Float?=null,
    var marcado: Boolean?=null,
    var cMARCADO: String?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): TmpVentas_Entity {
            val modelo=  TmpVentas_Entity()
            modelo.fecha = cursor.getDate(TmpVentas_Schema.FECHA_FIELD,null)
            modelo.tipoLinea = cursor.getString(TmpVentas_Schema.TIPOLINEA_FIELD,null)
            modelo.fabricante = cursor.getShort(TmpVentas_Schema.FABRICANTE_FIELD,null)
            modelo.articulo = cursor.getString(TmpVentas_Schema.ARTICULO_FIELD,null)
            modelo.unidadesCaja = cursor.getFloat(TmpVentas_Schema.UNIDADESCAJA_FIELD,null)
            modelo.concepto = cursor.getString(TmpVentas_Schema.CONCEPTO_FIELD,null)
            modelo.cantidad1 = cursor.getFloat(TmpVentas_Schema.CANTIDAD1_FIELD,null)
            modelo.cantidad2 = cursor.getFloat(TmpVentas_Schema.CANTIDAD2_FIELD,null)
            modelo.pvp = cursor.getFloat(TmpVentas_Schema.PVP_FIELD,null)
            modelo.venta1 = cursor.getFloat(TmpVentas_Schema.VENTA1_FIELD,null)
            modelo.venta2 = cursor.getFloat(TmpVentas_Schema.VENTA2_FIELD,null)
            modelo.regalo1 = cursor.getFloat(TmpVentas_Schema.REGALO1_FIELD,null)
            modelo.regalo2 = cursor.getFloat(TmpVentas_Schema.REGALO2_FIELD,null)
            modelo.marcado = cursor.getBoolean(TmpVentas_Schema.LMARCADO_FIELD,null)
            modelo.cMARCADO = cursor.getString(TmpVentas_Schema.CMARCADO_FIELD,null)
            return modelo
        }
    }
}