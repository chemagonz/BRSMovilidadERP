package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Familias_Schema
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Subfamilias_Schema
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString

data class Subfamilias_Entity(
    var familia: Short?= null,
    var subfamilia: Short?=null,
    var nombre: String?= null
) {
    companion object {
        fun fromCursor(cursor: Cursor): Subfamilias_Entity{
            val modelo= Subfamilias_Entity()
            modelo.familia= cursor.getShort(Subfamilias_Schema.FAMILIA_FIELD,null)
            modelo.subfamilia= cursor.getShort(Subfamilias_Schema.SUBFAMILIA_FIELD,null)
            modelo.nombre=cursor.getString(Familias_Schema.NOMBRE_FIELD,null)
            return modelo
        }
    }
}