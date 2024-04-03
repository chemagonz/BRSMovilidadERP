package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Tarifas_Schema
import com.advantys.brsmovilidaderp.Utils.getBoolean
import com.advantys.brsmovilidaderp.Utils.getFloat
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString

data class Tarifas_Entity(

    var numTarifa : Short?=null,
    var nombre: String?=null,
    var tarifaAux: Short? =null,
    var porcCargo: Float? = null,
    var tarblqvta: Boolean? =null
) {
    companion object{
        fun fromCursor(cursor: Cursor): Tarifas_Entity{
            val modelo= Tarifas_Entity()
            modelo.numTarifa= cursor.getShort(Tarifas_Schema.TARIFA_FIELD,null)
            modelo.nombre= cursor.getString(Tarifas_Schema.NOMBRE_FIELD,null)
            modelo.tarifaAux= cursor.getShort(Tarifas_Schema.TARIFA_AUX_FIELD,null)
            modelo.porcCargo= cursor.getFloat(Tarifas_Schema.PORC_CARGO_FIELD, null)
            modelo.tarblqvta= cursor.getBoolean(Tarifas_Schema.TAR_BLQ_VTA_FIELD,null)
            return modelo
        }
    }
}