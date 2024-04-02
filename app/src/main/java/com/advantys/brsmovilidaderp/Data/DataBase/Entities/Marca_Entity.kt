package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Marca_Schema
import com.advantys.brsmovilidaderp.Utils.getString

data class Marca_Entity(
    var marca: String?= null,
    var nombre: String?= null
) {
    companion object {
        fun fromCursor(cursor: Cursor): Marca_Entity{
            val modelo= Marca_Entity()
            modelo.marca= cursor.getString(Marca_Schema.MARCA_FIELD,null)
            modelo.nombre=cursor.getString(Marca_Schema.NOMBRE_FIELD,null)
            return modelo
        }
    }
}