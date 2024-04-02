package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Sabor_Schema
import com.advantys.brsmovilidaderp.Utils.getString

data class Sabor_Entity(
    var sabor: String?= null,
    var nombre:String?= null
) {
    companion object {
        fun fromCursor(cursor: Cursor): Sabor_Entity{
            val modelo= Sabor_Entity()
            modelo.sabor= cursor.getString(Sabor_Schema.SABOR_FIELD,null)
            modelo.nombre=cursor.getString(Sabor_Schema.NOMBRE_FIELD,null)
            return modelo
        }
    }
}