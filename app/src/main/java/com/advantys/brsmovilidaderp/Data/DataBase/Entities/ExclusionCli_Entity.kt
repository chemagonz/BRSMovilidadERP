package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.ExclusionCli_Schema
import com.advantys.brsmovilidaderp.Utils.getDate
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString
import java.util.Date

class ExclusionCli_Entity (
    var cliente: Int?=null,
    var articulo: String?=null,
    var formato: Int?=null,
    var fabricante: Short?=null,
    var marca: String?=null,
    var familia: Short?=null,
    var subfamilia: Short?=null,
    var sabor: String?=null,
    var hasta: Date?=null,
    var desde: Date?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): ExclusionCli_Entity {
            val modelo = ExclusionCli_Entity()
            modelo.cliente = cursor.getInt(ExclusionCli_Schema.CLIENTE_FIELD,null)
            modelo.articulo = cursor.getString(ExclusionCli_Schema.ARTICULO_FIELD,null)
            modelo.formato = cursor.getInt(ExclusionCli_Schema.FORMATO_FIELD,null)
            modelo.fabricante = cursor.getShort(ExclusionCli_Schema.FABRICANTE_FIELD,null)
            modelo.marca = cursor.getString(ExclusionCli_Schema.MARCA_FIELD,null)
            modelo.familia = cursor.getShort(ExclusionCli_Schema.FAMILIA_FIELD,null)
            modelo.subfamilia = cursor.getShort(ExclusionCli_Schema.SUBFAMILIA_FIELD,null)
            modelo.sabor = cursor.getString(ExclusionCli_Schema.SABOR_FIELD,null)
            modelo.hasta = cursor.getDate(ExclusionCli_Schema.HASTA_FIELD,null)
            modelo.desde = cursor.getDate(ExclusionCli_Schema.DESDE_FIELD,null)
            return modelo
        }
    }
}