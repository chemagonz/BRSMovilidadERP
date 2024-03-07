package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Rutas_Schema

data class Rutas_Entity (
    var nRuta: Int? = null,
    var nombre: String? = null,
    var marcado: Int? = null){
    companion object {
        fun fromCursor(cursor: Cursor): Rutas_Entity {
            var modelo = Rutas_Entity()
            modelo.nRuta = cursor.getInt(cursor.getColumnIndexOrThrow(Rutas_Schema.RUTA_FIELD))
            modelo.nombre =
                cursor.getString(cursor.getColumnIndexOrThrow(Rutas_Schema.NOMBRE_FIELD))
            modelo.marcado = cursor.getInt(cursor.getColumnIndexOrThrow(Rutas_Schema.MARCADO_FIELD))
            return modelo
        }
    }
}



