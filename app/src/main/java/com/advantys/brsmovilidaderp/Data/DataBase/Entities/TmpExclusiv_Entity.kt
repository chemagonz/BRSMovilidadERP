package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.TmpExclusiv_Schema
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString

class TmpExclusiv_Entity  (
    var articulo: String?=null,
    var fabricante: Short?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): TmpExclusiv_Entity {
            val modelo=  TmpExclusiv_Entity()
            modelo.articulo = cursor.getString(TmpExclusiv_Schema.ARTICULO_FIELD,null)
            modelo.fabricante = cursor.getShort(TmpExclusiv_Schema.FABRICANTE_FIELD,null)
            return modelo
        }
    }
}