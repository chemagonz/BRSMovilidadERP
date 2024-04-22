package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.MotivosDev_Schema
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString

class MotivosDev_Entity (
    var motivo: Short?=null,
    var nombre: String?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): MotivosDev_Entity {
            val modelo=  MotivosDev_Entity()
            modelo.motivo = cursor.getShort(MotivosDev_Schema.MOTIVO_FIELD,null)
            modelo.nombre = cursor.getString(MotivosDev_Schema.NOMBRE_FIELD,null)
            return modelo
        }
    }
}