package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.ArtiAlt_Schema
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString

data class ArtiAlt_Entity(
    var fabricante: Short? = null,
    var articulo: String? = null,
    var fabricanteAlt: Short? = null,
    var articuloAlt: String? = null,
    var tipo: String? = null
) {
    companion object {
        fun fromCursor(cursor: Cursor): ArtiAlt_Entity {
            val modelo = ArtiAlt_Entity()
            modelo.fabricante = cursor.getShort(ArtiAlt_Schema.FABRICANTE_FIELD,null)
            modelo.articulo = cursor.getString(ArtiAlt_Schema.ARTICULO_FIELD,null)
            modelo.fabricanteAlt = cursor.getShort(ArtiAlt_Schema.FABRICANTEALT_FIELD,null)
            modelo.articuloAlt = cursor.getString(ArtiAlt_Schema.ARTICULOALT_FIELD,null)
            modelo.tipo = cursor.getString(ArtiAlt_Schema.TIPO_FIELD,null)
            return modelo
        }
    }
}