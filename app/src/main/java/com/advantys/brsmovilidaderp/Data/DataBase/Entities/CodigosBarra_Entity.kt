package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.CodigosBarra_Schema
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString

class CodigosBarra_Entity (
    var fabricante: Short? = null,
    var articulo: String? = null,
    var codigoEAN: String? = null,
    var tipo: String? = null,
    var fechaAlta: String? = null
) {
    companion object {
        fun fromCursor(cursor: Cursor): CodigosBarra_Entity {
            val modelo = CodigosBarra_Entity()
            modelo.fabricante = cursor.getShort(CodigosBarra_Schema.FABRICANTE_FIELD,null)
            modelo.articulo = cursor.getString(CodigosBarra_Schema.ARTICULO_FIELD,null)
            modelo.codigoEAN = cursor.getString(CodigosBarra_Schema.CODIGOEAN_FIELD,null)
            modelo.tipo = cursor.getString(CodigosBarra_Schema.TIPO_FIELD,null)
            modelo.fechaAlta = cursor.getString(CodigosBarra_Schema.FECHAALTA_FIELD,null)
            return modelo
        }
    }
}