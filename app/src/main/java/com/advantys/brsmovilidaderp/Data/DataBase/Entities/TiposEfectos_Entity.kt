package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.TiposEfectos_Schema
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString

class TiposEfectos_Entity (
    var tipoEfecto: Short?=null,
    var nombre: String?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): TiposEfectos_Entity {
            val modelo= TiposEfectos_Entity()
            modelo.tipoEfecto = cursor.getShort(TiposEfectos_Schema.TIPOEFECTO_FIELD,null)
            modelo.nombre = cursor.getString(TiposEfectos_Schema.NOMBRE_FIELD,null)
            return modelo
        }
    }
}