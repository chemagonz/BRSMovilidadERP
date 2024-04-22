package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.GpsClientes_Schema
import com.advantys.brsmovilidaderp.Utils.getBoolean
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString

class GpsClientes_Entity (
    var cliente: Int?=null,
    var delegacion: Short?=null,
    var latitud: String?=null,
    var longitud: String?=null,
    var enviado: Boolean?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): GpsClientes_Entity {
            val modelo = GpsClientes_Entity()
            modelo.cliente = cursor.getInt(GpsClientes_Schema.CLIENTE_FIELD,null)
            modelo.delegacion = cursor.getShort(GpsClientes_Schema.DELEGACION_FIELD,null)
            modelo.latitud = cursor.getString(GpsClientes_Schema.LATITUD_FIELD,null)
            modelo.longitud = cursor.getString(GpsClientes_Schema.LONGITUD_FIELD,null)
            modelo.enviado = cursor.getBoolean(GpsClientes_Schema.ENVIADO_FIELD,null)
            return modelo
        }
    }
}