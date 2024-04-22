package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Gastos_Schema
import com.advantys.brsmovilidaderp.Utils.getBoolean
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getString

class Gastos_Entity (
    var tipo: Int?=null,
    var descripcion: String?=null,
    var interno: Boolean?=null,
    var solicitaKm: String?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): Gastos_Entity {
            val modelo = Gastos_Entity()
            modelo.tipo = cursor.getInt(Gastos_Schema.TIPO_FIELD,null)
            modelo.descripcion = cursor.getString(Gastos_Schema.DESCRIPCION_FIELD,null)
            modelo.interno = cursor.getBoolean(Gastos_Schema.INTERNO_FIELD,null)
            modelo.solicitaKm = cursor.getString(Gastos_Schema.SOLICITAKM_FIELD,null)
            return modelo
        }
    }
}