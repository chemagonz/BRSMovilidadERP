package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Monedas_Schema
import com.advantys.brsmovilidaderp.Utils.getFloat
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getString

class Monedas_Entity (
    var tipoMoneda: Int?=null,
    var descripcion: String?=null,
    var moneda: String?=null,
    var valor: Float?=null,
    var tipo: String?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): Monedas_Entity {
            val modelo= Monedas_Entity()
            modelo.tipoMoneda = cursor.getInt(Monedas_Schema.TIPOMONEDA_FIELD,null)
            modelo.descripcion = cursor.getString(Monedas_Schema.DESCRIPCION_FIELD,null)
            modelo.moneda = cursor.getString(Monedas_Schema.MONEDA_FIELD,null)
            modelo.valor = cursor.getFloat(Monedas_Schema.VALOR_FIELD,null)
            modelo.tipo = cursor.getString(Monedas_Schema.TIPO_FIELD,null)
            return modelo
        }
    }
}