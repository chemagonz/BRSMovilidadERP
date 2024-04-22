package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.RazonesSociales_Schema
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getString

class RazonesSociales_Entity(
    var razonSocial: Int?=null,
    var nombre: String?=null,
    var nif: String?=null,
    var direccion: String?=null,
    var codPostal: String?=null,
    var poblacion: String?=null,
    var provincia: String?=null,
    var telefono1: String?=null,
    var telefono2: String?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): RazonesSociales_Entity {
            val modelo= RazonesSociales_Entity()
            modelo.razonSocial = cursor.getInt(RazonesSociales_Schema.RAZONSOCIAL_FIELD,null)
            modelo.nombre = cursor.getString(RazonesSociales_Schema.NOMBRE_FIELD,null)
            modelo.nif = cursor.getString(RazonesSociales_Schema.NIF_FIELD,null)
            modelo.direccion = cursor.getString(RazonesSociales_Schema.DIRECCION_FIELD,null)
            modelo.codPostal = cursor.getString(RazonesSociales_Schema.CODPOSTAL_FIELD,null)
            modelo.poblacion = cursor.getString(RazonesSociales_Schema.POBLACION_FIELD,null)
            modelo.provincia = cursor.getString(RazonesSociales_Schema.PROVINCIA_FIELD,null)
            modelo.telefono1 = cursor.getString(RazonesSociales_Schema.TELEFONO1_FIELD,null)
            modelo.telefono2 = cursor.getString(RazonesSociales_Schema.TELEFONO2_FIELD,null)
            return modelo
        }
    }
}