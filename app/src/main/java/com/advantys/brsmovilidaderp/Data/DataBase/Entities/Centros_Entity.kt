package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Centros_Schema

 data class Centros_Entity(
     var nCentro: Int? = null,
     var nombre: String? = null,
     var NIF: String?= null,
     var direccion: String?= null,
     var codigoPostal: Int?= null,
     var poblacion: String?= null,
     var provincia: String?= null,
     var telefono: Int?= null,
     var serie:String? = null,
     var aplicaCargo: Boolean =true,
     var ventaMenorA: Int?= null) {
    companion object{
        fun fromCursor(cursor: Cursor): Centros_Entity{
            var modelo= Centros_Entity()
            modelo.nCentro= cursor.getInt(cursor.getColumnIndexOrThrow(Centros_Schema.CENTRO_FIELD))
            modelo.nombre= cursor.getString(cursor.getColumnIndexOrThrow(Centros_Schema.NOMBRE_FIELD))
            modelo.NIF= cursor.getString(cursor.getColumnIndexOrThrow(Centros_Schema.NIF_FIELD))
            modelo.direccion= cursor.getString(cursor.getColumnIndexOrThrow(Centros_Schema.DIRECCION_FIELD))
            modelo.codigoPostal= cursor.getInt(cursor.getColumnIndexOrThrow(Centros_Schema.CODPOSTAL_FIELD))
            modelo.poblacion= cursor.getString(cursor.getColumnIndexOrThrow(Centros_Schema.POBLACION_FIELD))
            modelo.provincia= cursor.getString(cursor.getColumnIndexOrThrow(Centros_Schema.PROVINCIA_FIELD))
            modelo.telefono= cursor.getInt(cursor.getColumnIndexOrThrow(Centros_Schema.TELEFONO_FIELD))
            modelo.serie= cursor.getString(cursor.getColumnIndexOrThrow(Centros_Schema.SERIE_FIELD))
            modelo.aplicaCargo= cursor.getInt(cursor.getColumnIndexOrThrow(Centros_Schema.APLCARGO_FIELD))!=0
            modelo.ventaMenorA= cursor.getInt(cursor.getColumnIndexOrThrow(Centros_Schema.VENTAMENORA_FIELD))
            return modelo
        }
    }
}
