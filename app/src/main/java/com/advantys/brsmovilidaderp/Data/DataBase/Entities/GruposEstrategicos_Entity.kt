package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.GruposEstrategicos_Schema
import com.advantys.brsmovilidaderp.Utils.getString

class GruposEstrategicos_Entity (
    var grupo: String?=null,
    var nombre: String?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): GruposEstrategicos_Entity {
            val modelo= GruposEstrategicos_Entity()
            modelo.grupo = cursor.getString(GruposEstrategicos_Schema.GRUPO_FIELD, null)
            modelo.nombre = cursor.getString(GruposEstrategicos_Schema.NOMBRE_FIELD,null)
            return modelo
        }
    }
}