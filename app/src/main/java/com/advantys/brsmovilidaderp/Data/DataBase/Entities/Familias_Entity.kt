package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Familias_Schema
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString

data class Familias_Entity(
    var familia: Short?= null,
    var nombre: String?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): Familias_Entity{
            val modelo= Familias_Entity()
            modelo.familia= cursor.getShort(Familias_Schema.FAMILIA_FIELD,null)
            modelo.nombre=cursor.getString(Familias_Schema.NOMBRE_FIELD,null)
            return modelo
        }
    }
}