package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.DepoCombu_Schema
import com.advantys.brsmovilidaderp.Utils.getFloat
import com.advantys.brsmovilidaderp.Utils.getString

class DepoCombu_Entity (
    var id: String?= null,
    var descripcion: String? = null,
    var capacidadMax: Float? = null
) {
    companion object {
        fun fromCursor(cursor: Cursor): DepoCombu_Entity {
            val modelo = DepoCombu_Entity()
            modelo.id = cursor.getString(DepoCombu_Schema.ID_FIELD,null)
            modelo.descripcion = cursor.getString(DepoCombu_Schema.DESCRIPCION_FIELD,null)
            modelo.capacidadMax = cursor.getFloat(DepoCombu_Schema.CAPACIDADMAX_FIELD,null)
            return modelo
        }
    }
}