package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Fabricante_Schema
import com.advantys.brsmovilidaderp.Utils.getBoolean
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString

data class Fabricante_Entity(
    var numfabricante: Short? = null,
    var nombre: String? = null,
    var tipodescuento: String? = null,
    var tipolinea: String? = null,
    var esmulcli: Boolean? = null,
    var razonsocial: String? = null,
    var direccion: String? = null,
    var codpostal: String? = null,
    var poblacion: String? = null,
    var provincia: String? = null,
    var nif: String? = null,
    var telefono1: String? = null,
    var telefono2: String ? = null,
    var fax: String? = null,
    var registromercantil: String? = null,
    var pie: String? = null,
    var codigocentro: String?=null
) {

    companion object{
        fun fromCursor(cursor: Cursor): Fabricante_Entity{
            val modelo= Fabricante_Entity()
            modelo.numfabricante= cursor.getShort(Fabricante_Schema.FABRICANTE_FIELD, null)
            modelo.nombre= cursor.getString(Fabricante_Schema.NOMBRE_FIELD, null)
            modelo.tipodescuento= cursor.getString(Fabricante_Schema.TIPO_DESCUENTO_FIELD, null)
            modelo.tipolinea= cursor.getString(Fabricante_Schema.TIPO_LINEA_FIELD, null)
            modelo.esmulcli= cursor.getBoolean(Fabricante_Schema.SEL_MUL_CLI_FIELD, null)
            modelo.razonsocial= cursor.getString(Fabricante_Schema.RAZON_SOCIAL_FIELD, null)
            modelo.direccion= cursor.getString(Fabricante_Schema.DIRECCION_FIELD, null)
            modelo.codpostal= cursor.getString(Fabricante_Schema.COD_POSTAL_FIELD, null)
            modelo.poblacion= cursor.getString(Fabricante_Schema.POBLACION_FIELD, null)
            modelo.provincia= cursor.getString(Fabricante_Schema.PROVINCIA_FIELD, null)
            modelo.nif= cursor.getString(Fabricante_Schema.NIF_FIELD, null)
            modelo.telefono1= cursor.getString(Fabricante_Schema.TELEFONO1_FIELD, null)
            modelo.telefono2= cursor.getString(Fabricante_Schema.TELEFONO2_FIELD, null)
            modelo.fax= cursor.getString(Fabricante_Schema.FAX_FIELD, null)
            modelo.registromercantil= cursor.getString(Fabricante_Schema.REGISTRO_MERCANTIL_FIELD, null)
            modelo.pie= cursor.getString(Fabricante_Schema.PIE_FIELD, null)
            modelo.codigocentro= cursor.getString(Fabricante_Schema.CODIGO_CENTRO_FIELD, null)
            return modelo
        }
    }

}