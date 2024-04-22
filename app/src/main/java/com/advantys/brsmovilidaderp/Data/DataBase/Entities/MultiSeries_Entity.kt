package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.MultiSeries_Schema
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString

class MultiSeries_Entity (
    var serie: String?=null,
    var fabricante: Short?=null,
    var multiSerie: String?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): MultiSeries_Entity {
            val modelo= MultiSeries_Entity()
            modelo.serie = cursor.getString(MultiSeries_Schema.SERIE_FIELD,null)
            modelo.fabricante = cursor.getShort(MultiSeries_Schema.FABRICANTE_FIELD,null)
            modelo.multiSerie = cursor.getString(MultiSeries_Schema.MULTISERIE_FIELD,null)
            return modelo
        }
    }
}