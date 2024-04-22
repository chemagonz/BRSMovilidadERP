package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Motivos_Schema
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString

class Motivos_Entity(
    var motivo: Short?=null,
    var nombre: String?=null,
    var categoria: String?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): Motivos_Entity {
            val modelo= Motivos_Entity()
            modelo.motivo = cursor.getShort(Motivos_Schema.MOTIVO_FIELD,null)
            modelo.nombre = cursor.getString(Motivos_Schema.NOMBRE_FIELD,null)
            modelo.categoria = cursor.getString(Motivos_Schema.CATEGORIA_FIELD,null)
            return modelo
        }
    }
}