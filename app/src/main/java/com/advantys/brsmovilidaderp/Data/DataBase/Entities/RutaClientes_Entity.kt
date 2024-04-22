package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.RutaClientes_Schema
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString

class RutaClientes_Entity(
    var cliente: Int?=null,
    var delegacion: Short?=null,
    var ruta: String?=null,
    var secuencia: String?=null,
    var diaSemana: String?=null,
    var tipoRuta: String?=null,
    var tipoReparto: String?=null,
    var rr: String?=null,
    var sr: String?=null,
    var diaRep: String?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): RutaClientes_Entity {
            val modelo=  RutaClientes_Entity()
            modelo.cliente = cursor.getInt(RutaClientes_Schema.RUTACLIENTE_FIELD,null)
            modelo.delegacion = cursor.getShort(RutaClientes_Schema.DELEGACION_FIELD,null)
            modelo.ruta = cursor.getString(RutaClientes_Schema.RUTA_FIELD,null)
            modelo.secuencia = cursor.getString(RutaClientes_Schema.SECUENCIA_FIELD,null)
            modelo.diaSemana = cursor.getString(RutaClientes_Schema.DIASEMANA_FIELD,null)
            modelo.tipoRuta = cursor.getString(RutaClientes_Schema.TIPORUTA_FIELD,null)
            modelo.tipoReparto = cursor.getString(RutaClientes_Schema.TIPOREPARTO_FIELD,null)
            modelo.rr = cursor.getString(RutaClientes_Schema.CRR_FIELD,null)
            modelo.sr = cursor.getString(RutaClientes_Schema.CSR_FIELD,null)
            modelo.diaRep = cursor.getString(RutaClientes_Schema.DIAREP_FIELD,null)
            return modelo
        }
    }
}