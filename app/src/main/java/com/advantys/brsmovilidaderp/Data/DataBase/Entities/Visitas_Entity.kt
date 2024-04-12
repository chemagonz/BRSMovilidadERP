package com.advantys.brsmovilidaderp.Data.DataBase.Entities


import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Visitas_Schema
import com.advantys.brsmovilidaderp.Utils.getBoolean
import com.advantys.brsmovilidaderp.Utils.getDate
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString
import java.util.Date

data class Visitas_Entity(
    var preventa: Date? = null,
    var cliente: Int? = null,
    var delegacion: Short? = null,
    var rutaventa: String? = null,
    var secuventa: String? = null,
    var motivo: Short? = null,
    var enviado: Boolean? = null
)
{
    companion object {
        fun fromCursor(cursor: Cursor): Visitas_Entity {
            val modelo = Visitas_Entity()
            modelo.preventa = cursor.getDate(Visitas_Schema.PREVENTA_FIELD,null)
            modelo.cliente = cursor.getInt(Visitas_Schema.CLIENTE_FIELD,null)
            modelo.delegacion = cursor.getShort(Visitas_Schema.DELEGACION_FIELD,null)
            modelo.rutaventa = cursor.getString(Visitas_Schema.RUTAVENTA_FIELD,null)
            modelo.secuventa = cursor.getString(Visitas_Schema.SECUVENTA_FIELD,null)
            modelo.motivo = cursor.getShort(Visitas_Schema.MOTIVO_FIELD,null)
            modelo.enviado = cursor.getBoolean(Visitas_Schema.ENVIADO_FIELD,null)
            return modelo
            return modelo
        }
    }
}