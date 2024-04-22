package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.ExclusivasCli_Schema
import com.advantys.brsmovilidaderp.Utils.getDate
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString
import java.util.Date

class ExclusivasCli_Entity (
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
        fun fromCursor(cursor: Cursor): ExclusivasCli_Entity {
            val modelo = ExclusivasCli_Entity()
            modelo.cliente = cursor.getInt(ExclusivasCli_Schema.CLIENTE_FIELD,null)
            modelo.articulo = cursor.getString(ExclusivasCli_Schema.ARTICULO_FIELD,null)
            modelo.formato = cursor.getInt(ExclusivasCli_Schema.FORMATO_FIELD,null)
            modelo.fabricante = cursor.getShort(ExclusivasCli_Schema.FABRICANTE_FIELD,null)
            modelo.marca = cursor.getString(ExclusivasCli_Schema.MARCA_FIELD,null)
            modelo.familia = cursor.getShort(ExclusivasCli_Schema.FAMILIA_FIELD,null)
            modelo.subfamilia = cursor.getShort(ExclusivasCli_Schema.SUBFAMILIA_FIELD,null)
            modelo.sabor = cursor.getString(ExclusivasCli_Schema.SABOR_FIELD,null)
            modelo.hasta = cursor.getDate(ExclusivasCli_Schema.HASTA_FIELD,null)
            modelo.desde = cursor.getDate(ExclusivasCli_Schema.DESDE_FIELD,null)
            return modelo
        }
    }
}