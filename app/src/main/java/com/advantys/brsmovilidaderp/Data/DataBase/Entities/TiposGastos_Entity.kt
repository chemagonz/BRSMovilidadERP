package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.TiposGastos_Schema
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getString

class TiposGastos_Entity(
    var tipoGasto: String?=null,
    var descripcion: String?=null,
    var reqIdentar: Int?=null,
    var reqIntrCant: Int?=null,
    var reqDatFact: Int?=null,
    var proveedor: Int?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): TiposGastos_Entity {
            val modelo=  TiposGastos_Entity()
            modelo.tipoGasto = cursor.getString(TiposGastos_Schema.TIPOGASTO_FIELD,null)
            modelo.descripcion = cursor.getString(TiposGastos_Schema.DESCRIPCION_FIELD,null)
            modelo.reqIdentar = cursor.getInt(TiposGastos_Schema.REQIDENTAR_FIELD,null)
            modelo.reqIntrCant = cursor.getInt(TiposGastos_Schema.REQINTRCANT_FIELD,null)
            modelo.reqDatFact = cursor.getInt(TiposGastos_Schema.REQDATFACT_FIELD,null)
            modelo.proveedor = cursor.getInt(TiposGastos_Schema.PROVEEDOR_FIELD,null)
            return modelo
        }
    }
}