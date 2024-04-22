package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.TmpExclusio_Schema
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString

class TmpExclusio_Entity (
    var articulo: String?=null,
    var fabricante: Short?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): TmpExclusio_Entity {
            val modelo = TmpExclusio_Entity()
            modelo.articulo = cursor.getString(TmpExclusio_Schema.ARTICULO_FIELD,null)
            modelo.fabricante = cursor.getShort(TmpExclusio_Schema.FABRICANTE_FIELD,null)
            return modelo
        }
    }
}
