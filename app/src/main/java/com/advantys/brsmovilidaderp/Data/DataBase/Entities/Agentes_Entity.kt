package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Agentes_Schema
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getString

data class Agentes_Entity (
    var agente: Int? = null,
    var nombre: String? = null,
    var cobrador: String? = null,
    var repartidor: String? = null,
    var preventista: String? = null,
    var pin: String? = null,
    var vehiculo: String? = null,
    var nif: String? = null
){
    companion object {
        fun fromCursor(cursor: Cursor): Agentes_Entity {
            val modelo = Agentes_Entity()
            modelo.agente = cursor.getInt(Agentes_Schema.AGENTE_FIELD, null)
            modelo.nombre = cursor.getString(Agentes_Schema.NOMBRE_FIELD, null)
            modelo.cobrador = cursor.getString(Agentes_Schema.COBRADOR_FIELD, null)
            modelo.repartidor = cursor.getString(Agentes_Schema.REPARTIDOR_FIELD, null)
            modelo.preventista = cursor.getString(Agentes_Schema.PREVENTISTA_FIELD, null)
            modelo.pin = cursor.getString(Agentes_Schema.PIN_FIELD, null)
            modelo.vehiculo = cursor.getString(Agentes_Schema.VEHICULO_FIELD, null)
            modelo.nif = cursor.getString(Agentes_Schema.NIF_FIELD, null)
            return modelo
        }
    }
}