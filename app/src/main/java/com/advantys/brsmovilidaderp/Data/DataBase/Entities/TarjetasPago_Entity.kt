package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.TarjetasPago_Schema
import com.advantys.brsmovilidaderp.Utils.getString

class TarjetasPago_Entity (
    var tarjetaPago: String?=null,
    var descripcion: String?=null,
    var tipoTarjeta: String?=null,
    var numero: String?=null,
    var tipoPago: String?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): TarjetasPago_Entity {
            val modelo=  TarjetasPago_Entity()
            modelo.tarjetaPago = cursor.getString(TarjetasPago_Schema.TARJETAPAGO_FIELD,null)
            modelo.descripcion = cursor.getString(TarjetasPago_Schema.DESCRIPCION_FIELD,null)
            modelo.tipoTarjeta = cursor.getString(TarjetasPago_Schema.TIPOTARJETA_FIELD,null)
            modelo.numero = cursor.getString(TarjetasPago_Schema.NUMERO_FIELD,null)
            modelo.tipoPago = cursor.getString(TarjetasPago_Schema.TIPOPAGO_FIELD,null)
            return modelo
        }
    }
}