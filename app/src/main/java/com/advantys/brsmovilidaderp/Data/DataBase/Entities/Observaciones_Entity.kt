package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Observaciones_Schema
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString

class Observaciones_Entity (
    var fabricante: Short?=null,
    var observacion: String?=null,
    var texto: String?=null,
    var tipo: String?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): Observaciones_Entity {
            val modelo=  Observaciones_Entity()
            modelo.fabricante = cursor.getShort(Observaciones_Schema.FABRICANTE_FIELD,null)
            modelo.observacion = cursor.getString(Observaciones_Schema.OBSERVACION_FIELD,null)
            modelo.texto = cursor.getString(Observaciones_Schema.TEXTO_FIELD,null)
            modelo.tipo = cursor.getString(Observaciones_Schema.TIPO_FIELD,null)
            return modelo
        }
    }
}