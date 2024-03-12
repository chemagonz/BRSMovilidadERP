package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Rutas_Schema

data class Rutas_Entity (
    var nRuta: String? = null,
    var nombre: String? = null,
    var lmarcado: Boolean= true,
    var cmarcado: String?=null) {
    companion object {
        fun fromCursor(cursor: Cursor): Rutas_Entity {
            var modelo = Rutas_Entity()
            modelo.nRuta = cursor.getString(cursor.getColumnIndexOrThrow(Rutas_Schema.RUTA_FIELD))
            modelo.nombre = cursor.getString(cursor.getColumnIndexOrThrow(Rutas_Schema.NOMBRE_FIELD))
            modelo.lmarcado = if(cursor.getInt(cursor.getColumnIndexOrThrow(Rutas_Schema.LMARCADO_FIELD)) == 0) false else true
            modelo.cmarcado = cursor.getString(cursor.getColumnIndexOrThrow(Rutas_Schema.CMARCADO_FIELD))
            return modelo
        }
    }






}




