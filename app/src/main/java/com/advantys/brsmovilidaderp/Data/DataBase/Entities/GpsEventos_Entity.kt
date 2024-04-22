package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.GpsEventos_Schema
import com.advantys.brsmovilidaderp.Utils.getBoolean
import com.advantys.brsmovilidaderp.Utils.getDate
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString
import java.util.Date

class GpsEventos_Entity (
    var registro: Int?=null,
    var tipo: String?=null,
    var fecha: Date?=null,
    var latitud: String?=null,
    var longitud: String?=null,
    var serieDoc: String?=null,
    var doc: Int?=null,
    var cliente: Int?=null,
    var delegacion: Short?=null,
    var activacion: String?=null,
    var enviado: Boolean?=null
    ) {
        companion object {
            fun fromCursor(cursor: Cursor): GpsEventos_Entity {
                val modelo= GpsEventos_Entity()
                modelo.registro = cursor.getInt(GpsEventos_Schema.REGISTRO_FIELD,null)
                modelo.tipo = cursor.getString(GpsEventos_Schema.TIPO_FIELD,null)
                modelo.fecha = cursor.getDate(GpsEventos_Schema.FECHA_FIELD,null)
                modelo.latitud = cursor.getString(GpsEventos_Schema.LATITUD_FIELD,null)
                modelo.longitud= cursor.getString(GpsEventos_Schema.LONGITUD_FIELD,null)
                modelo.serieDoc = cursor.getString(GpsEventos_Schema.SERIEDOC_FIELD,null)
                modelo.doc = cursor.getInt(GpsEventos_Schema.DOC_FIELD,null)
                modelo.cliente = cursor.getInt(GpsEventos_Schema.CLIENTE_FIELD,null)
                modelo.delegacion = cursor.getShort(GpsEventos_Schema.DELEGACION_FIELD,null)
                modelo.activacion = cursor.getString(GpsEventos_Schema.ACTIVACION_FIELD,null)
                modelo.enviado = cursor.getBoolean(GpsEventos_Schema.ENVIADO_FIELD,null)
                return modelo
            }
        }
    }