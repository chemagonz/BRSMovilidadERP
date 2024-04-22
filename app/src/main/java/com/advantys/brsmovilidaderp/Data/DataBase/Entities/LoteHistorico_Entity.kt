package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.LoteHistorico_Schema
import com.advantys.brsmovilidaderp.Utils.getFloat
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString
import java.util.Date

class LoteHistorico_Entity(
    var fabricante: Short?=null,
    var articulo: String?=null,
    var lote: String?=null,
    var orden: Short?=null,
    var cantidad1: Float?=null,
    var cantidad2: Float?=null,
    var caducidad: Date?=null,
    var tipo: Short?=null,
    var serAlbCli: String?=null,
    var numAlbCli: Int?=null,
    var ordAlbCli: Int?=null,
    var numMovAlm: Int?=null,
    var ordMovAlm: Int?=null,
    var ejerAlbCli: Int?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): LoteHistorico_Entity {
            val modelo= LoteHistorico_Entity()
            modelo.fabricante = cursor.getShort(LoteHistorico_Schema.FABRICANTE_FIELD,null)
            modelo.articulo = cursor.getString(LoteHistorico_Schema.ARTICULO_FIELD,null)
            modelo.lote = cursor.getString(LoteHistorico_Schema.LOTE_FIELD,null)
            modelo.orden = cursor.getShort(LoteHistorico_Schema.ORDEN_FIELD,null)
            modelo.cantidad1 = cursor.getFloat(LoteHistorico_Schema.CANTIDAD1_FIELD,null)
            modelo.cantidad2 = cursor.getFloat(LoteHistorico_Schema.CANTIDAD2_FIELD,null)
            modelo.tipo = cursor.getShort(LoteHistorico_Schema.TIPO_FIELD,null)
            modelo.serAlbCli = cursor.getString(LoteHistorico_Schema.SERALBCLI_FIELD,null)
            modelo.numAlbCli = cursor.getInt(LoteHistorico_Schema.NUMALBCLI_FIELD,null)
            modelo.ordAlbCli = cursor.getInt(LoteHistorico_Schema.ORDALBCLI_FIELD,null)
            modelo.numMovAlm = cursor.getInt(LoteHistorico_Schema.NUMMOVALM_FIELD,null)
            modelo.ordMovAlm = cursor.getInt(LoteHistorico_Schema.ORDMOVALM_FIELD,null)
            modelo.ejerAlbCli = cursor.getInt(LoteHistorico_Schema.EJERALBCLI_FIELD)
            return modelo
        }
    }
}