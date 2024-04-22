package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.LineasProducto_Schema
import com.advantys.brsmovilidaderp.Utils.getString

class LineasProducto_Entity (
    var id: String?=null,
    var nombre: String?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): LineasProducto_Entity {
            val modelo= LineasProducto_Entity()
            modelo.id = cursor.getString(LineasProducto_Schema.ID_FIELD,null)
            modelo.nombre = cursor.getString(LineasProducto_Schema.NOMBRE_FIELD,null)
            return modelo
        }
    }
}