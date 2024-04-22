package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Comentarios_Schema
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getString

class Comentarios_Entity (
    var id: Int? = null,
    var comentario: String? = null
) {
    companion object {
        fun fromCursor(cursor: Cursor): Comentarios_Entity {
            val modelo = Comentarios_Entity()
            modelo.id = cursor.getInt(Comentarios_Schema.ID_FIELD,null)
            modelo.comentario = cursor.getString(Comentarios_Schema.COMENTARIO_FIELD,null)
            return modelo
        }
    }
}