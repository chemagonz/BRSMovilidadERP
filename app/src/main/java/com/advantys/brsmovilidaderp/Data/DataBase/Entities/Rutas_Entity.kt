package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Rutas_Schema
import com.advantys.brsmovilidaderp.Utils.getBoolean
import com.advantys.brsmovilidaderp.Utils.getString

data class Rutas_Entity (
    var nRuta: String? = null,
    var nombre: String? = null,
    var lmarcado: Boolean?= null,
    var cmarcado: String?=null) {
    companion object {
        fun fromCursor(cursor: Cursor): Rutas_Entity {
            var modelo = Rutas_Entity()
            modelo.nRuta = cursor.getString(Rutas_Schema.RUTA_FIELD,null)
            modelo.nombre = cursor.getString(Rutas_Schema.NOMBRE_FIELD,null)
            modelo.lmarcado = cursor.getBoolean(Rutas_Schema.LMARCADO_FIELD,null)
            modelo.cmarcado = cursor.getString(Rutas_Schema.CMARCADO_FIELD,null)
            return modelo
        }
    }
}




