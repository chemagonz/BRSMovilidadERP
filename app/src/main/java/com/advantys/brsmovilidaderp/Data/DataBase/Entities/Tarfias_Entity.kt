package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Tarfias_Schema
import com.advantys.brsmovilidaderp.Utils.getBoolean
import com.advantys.brsmovilidaderp.Utils.getFloat
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString

data class Tarfias_Entity(

    var numTarifa : Short?=null,
    var nombre: String?=null,
    var tarifaAux: Short? =null,
    var porcCargo: Float? = null,
    var tarblqvta: Boolean? =null
) {
    companion object{
        fun fromCursor(cursor: Cursor): Tarfias_Entity{
            val modelo= Tarfias_Entity()
            modelo.numTarifa= cursor.getShort(Tarfias_Schema.TARIFA_FIELD,null)
            modelo.nombre= cursor.getString(Tarfias_Schema.NOMBRE_FIELD,null)
            modelo.tarifaAux= cursor.getShort(Tarfias_Schema.TARIFA_AUX_FIELD,null)
            modelo.porcCargo= cursor.getFloat(Tarfias_Schema.PORC_CARGO_FIELD, null)
            modelo.tarblqvta= cursor.getBoolean(Tarfias_Schema.TAR_BLQ_VTA_FIELD,null)
            return modelo
        }
    }
}