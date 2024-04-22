package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Promobas_Schema
import com.advantys.brsmovilidaderp.Utils.getFloat
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString

class Promobas_Entity (
    var promo: String?=null,
    var cliente: Short?=null,
    var articulo: String?=null,
    var fabricante: Short?=null,
    var cantidad1: Float?=null,
    var cantidad2: Float?=null,
    var pvp: Float?=null,
    var porcDto1: Float?=null,
    var impDto1Uni: Float?=null,
    var porcDto2: Float?=null,
    var impDto2Uni: Float?=null,
    var tipoDto: String?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): Promobas_Entity {
            val modelo=  Promobas_Entity()
            modelo.promo = cursor.getString(Promobas_Schema.PROMO_FIELD,null)
            modelo.cliente = cursor.getShort(Promobas_Schema.CLIENTE_FIELD,null)
            modelo.articulo = cursor.getString(Promobas_Schema.ARTICULO_FIELD,null)
            modelo.fabricante = cursor.getShort(Promobas_Schema.FABRICANTE_FIELD,null)
            modelo.cantidad1 = cursor.getFloat(Promobas_Schema.CANTIDAD1_FIELD,null)
            modelo.cantidad2 = cursor.getFloat(Promobas_Schema.CANTIDAD2_FIELD,null)
            modelo.pvp = cursor.getFloat(Promobas_Schema.PVP_FIELD,null)
            modelo.porcDto1 = cursor.getFloat(Promobas_Schema.PORCDTO1_FIELD,null)
            modelo.impDto1Uni = cursor.getFloat(Promobas_Schema.IMPDTO1UNI_FIELD,null)
            modelo.porcDto2 = cursor.getFloat(Promobas_Schema.PORCDTO2_FIELD,null)
            modelo.impDto2Uni = cursor.getFloat(Promobas_Schema.IMPDTO2UNI_FIELD,null)
            modelo.tipoDto = cursor.getString(Promobas_Schema.TIPODTO_FIELD,null)
            return modelo
        }
    }
}