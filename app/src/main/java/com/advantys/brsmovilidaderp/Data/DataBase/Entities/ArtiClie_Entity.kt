package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.ArtiClie_Schema
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString

class ArtiClie_Entity(
    var cliente: Int? = null,
    var articulo: String? = null,
    var fabricante: Short? = null,
    var subreferencia: String? = null
) {
    companion object {
        fun fromCursor(cursor: Cursor): ArtiClie_Entity {
            val modelo = ArtiClie_Entity()
            modelo.cliente = cursor.getInt(ArtiClie_Schema.CLIENTE_FIELD,null)
            modelo.articulo = cursor.getString(ArtiClie_Schema.ARTICULO_FIELD,null)
            modelo.fabricante = cursor.getShort(ArtiClie_Schema.FABRICANTE_FIELD,null)
            modelo.subreferencia = cursor.getString(ArtiClie_Schema.SUBREFERENCIA_FIELD,null)
            return modelo
        }
    }
}