package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Datafonos_Schema
import com.advantys.brsmovilidaderp.Utils.getString

class Datafonos_Entity (
    var id: String?=null,
    var nombre: String? = null,
    var numSerie: String? = null
) {
    companion object {
        fun fromCursor(cursor: Cursor): Datafonos_Entity {
            val modelo = Datafonos_Entity()
            modelo.id= cursor.getString(Datafonos_Schema.ID_FIELD,null)
            modelo.nombre = cursor.getString(Datafonos_Schema.NOMBRE_FIELD,null)
            modelo.numSerie = cursor.getString(Datafonos_Schema.NUMSERIE_FIELD,null)
            return modelo
        }
    }
}