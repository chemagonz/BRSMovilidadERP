package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Exclusividades_Schema
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString

class Exclusividades_Entity (
    var cliente: Int?=null,
    var delegacion: Short?=null,
    var fabricante: Short?=null,
    var articulo: String?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): Exclusividades_Entity {
            val modelo = Exclusividades_Entity()
            modelo.cliente = cursor.getInt(Exclusividades_Schema.CLIENTE_FIELD,null)
            modelo.delegacion = cursor.getShort(Exclusividades_Schema.DELEGACION_FIELD,null)
            modelo.fabricante = cursor.getShort(Exclusividades_Schema.FABRICANTE_FIELD,null)
            modelo.articulo = cursor.getString(Exclusividades_Schema.ARTICULO_FIELD,null)
            return modelo
        }
    }
}