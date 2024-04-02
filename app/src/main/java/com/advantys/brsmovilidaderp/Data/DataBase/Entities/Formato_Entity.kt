package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Formato_Schema
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getString

data class Formato_Entity(
    var formato: Int?= null,
    var nombre: String?=null

) {
    companion object {
        fun fromCursor(cursor: Cursor): Formato_Entity{
            val modelo= Formato_Entity()
            modelo.formato= cursor.getInt(Formato_Schema.FORMATO_FIELD,null)
            modelo.nombre=cursor.getString(Formato_Schema.NOMBRE_FIELD,null)
            return modelo
        }
    }
}