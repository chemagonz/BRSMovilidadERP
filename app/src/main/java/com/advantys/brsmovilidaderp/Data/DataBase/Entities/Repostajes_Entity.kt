package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Repostajes_Schema
import com.advantys.brsmovilidaderp.Utils.getBoolean
import com.advantys.brsmovilidaderp.Utils.getDate
import com.advantys.brsmovilidaderp.Utils.getDouble
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString
import java.util.Date

class Repostajes_Entity (
    var repostaje: Int?=null,
    var fecha: Date?=null,
    var tipo: String?=null,
    var lugar: String?=null,
    var depositoCombustible: String?=null,
    var vehiculo: String?=null,
    var litros: Double?=null,
    var medioPago: Short?=null,
    var tarjetaPago: String?=null,
    var importe: Double?=null,
    var enviado: Boolean?=null,
    var jornada: Int?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): Repostajes_Entity {
            val modelo= Repostajes_Entity()
            modelo.repostaje = cursor.getInt(Repostajes_Schema.REPOSTAJE_FIELD,null)
            modelo.fecha = cursor.getDate(Repostajes_Schema.FECHA_FIELD,null)
            modelo.tipo = cursor.getString(Repostajes_Schema.TIPO_FIELD,null)
            modelo.lugar = cursor.getString(Repostajes_Schema.LUGAR_FIELD,null)
            modelo.depositoCombustible = cursor.getString(Repostajes_Schema.DEPOCOMBU_FIELD,null)
            modelo.vehiculo = cursor.getString(Repostajes_Schema.VEHICULO_FIELD,null)
            modelo.litros = cursor.getDouble(Repostajes_Schema.LITROS_FIELD,null)
            modelo.medioPago = cursor.getShort(Repostajes_Schema.MEDIOPAGO_FIELD,null)
            modelo.tarjetaPago = cursor.getString(Repostajes_Schema.TARJETAPAGO_FIELD,null)
            modelo.importe = cursor.getDouble(Repostajes_Schema.IMPORTE_FIELD,null)
            modelo.enviado = cursor.getBoolean(Repostajes_Schema.ENVIADO_FIELD,null)
            modelo.jornada = cursor.getInt(Repostajes_Schema.JORNADA_FIELD,null)
            return modelo
        }
    }
}