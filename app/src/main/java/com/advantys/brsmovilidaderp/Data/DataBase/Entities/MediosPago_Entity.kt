package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.MediosPago_Schema
import com.advantys.brsmovilidaderp.Utils.getDouble
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString

class MediosPago_Entity (
    var medio: Short?=null,
    var descripcion: String?=null,
    var limiteCobro: Double?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): MediosPago_Entity {
            val modelo= MediosPago_Entity()
            modelo.medio = cursor.getShort(MediosPago_Schema.MEDIO_FIELD,null)
            modelo.descripcion = cursor.getString(MediosPago_Schema.DESCRIPCION_FIELD,null)
            modelo.limiteCobro = cursor.getDouble(MediosPago_Schema.LIMITECOBRO_FIELD,null)
            return modelo
        }
    }
}