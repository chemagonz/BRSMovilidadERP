package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Bancos_Schema
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getString

class Bancos_Entity (
    var id: Int? = null,
    var descripcion: String? = null
) {
    companion object {
        fun fromCursor(cursor: Cursor): Bancos_Entity {
            val modelo = Bancos_Entity()
            modelo.id = cursor.getInt(Bancos_Schema.ID_FIELD,null)
            modelo.descripcion = cursor.getString(Bancos_Schema.DESCRIPCION_FIELD,null)
            return modelo
        }
    }
}