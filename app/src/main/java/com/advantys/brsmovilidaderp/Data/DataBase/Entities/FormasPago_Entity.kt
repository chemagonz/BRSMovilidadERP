package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.FormasPago_Schema
import com.advantys.brsmovilidaderp.Utils.getBoolean
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString

class FormasPago_Entity (
    var formaPago: Short?=null,
    var nombre: String?=null,
    var emiteRecibo: String?=null,
    var tipoPago: String?=null,
    var dtoVtaCaj: Boolean?=null,
    var medioPago: Short?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): FormasPago_Entity {
            val modelo = FormasPago_Entity()
            modelo.formaPago = cursor.getShort(FormasPago_Schema.FORMAPAGO_FIELD,null)
            modelo.nombre = cursor.getString(FormasPago_Schema.NOMBRE_FIELD,null)
            modelo.emiteRecibo = cursor.getString(FormasPago_Schema.EMITERECIBO_FIELD,null)
            modelo.tipoPago = cursor.getString(FormasPago_Schema.TIPOPAGO_FIELD,null)
            modelo.dtoVtaCaj = cursor.getBoolean(FormasPago_Schema.DTOVTACAJ_FIELD,null)
            modelo.medioPago = cursor.getShort(FormasPago_Schema.MEDIOPAGO_FIELD,null)
            return modelo
        }
    }
}