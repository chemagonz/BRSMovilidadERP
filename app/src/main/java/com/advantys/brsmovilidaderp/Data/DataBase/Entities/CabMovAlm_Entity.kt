package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.CabMovAlm_Schema
import com.advantys.brsmovilidaderp.Utils.getBoolean
import com.advantys.brsmovilidaderp.Utils.getDate
import com.advantys.brsmovilidaderp.Utils.getInt
import java.util.Date

class CabMovAlm_Entity (
    var movimiento: Int? = null,
    var fecha: Date? = null,
    var enviado: Boolean? = null,
    var cerrado: Boolean? = null
) {
    companion object {
        fun fromCursor(cursor: Cursor): CabMovAlm_Entity {
            val modelo = CabMovAlm_Entity()
            modelo.movimiento = cursor.getInt(CabMovAlm_Schema.MOVIMIENTO_FIELD,null)
            modelo.fecha = cursor.getDate(CabMovAlm_Schema.MOVIMIENTO_FIELD,null)
            modelo.enviado = cursor.getBoolean(CabMovAlm_Schema.MOVIMIENTO_FIELD,null)
            modelo.cerrado = cursor.getBoolean(CabMovAlm_Schema.MOVIMIENTO_FIELD,null)
            return modelo
        }
    }
}