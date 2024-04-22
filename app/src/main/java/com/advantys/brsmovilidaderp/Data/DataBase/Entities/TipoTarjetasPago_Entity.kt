package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.TipoTarjetasPago_Schema
import com.advantys.brsmovilidaderp.Utils.getString

class TipoTarjetasPago_Entity (
    var id: String?=null,
    var descripcion: String?=null,
    var entidad: String?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): TipoTarjetasPago_Entity {
            val modelo =  TipoTarjetasPago_Entity()
            modelo.id = cursor.getString(TipoTarjetasPago_Schema.ID_FIELD,null)
            modelo.descripcion = cursor.getString(TipoTarjetasPago_Schema.DESCRIPCION_FIELD,null)
            modelo.entidad = cursor.getString(TipoTarjetasPago_Schema.ENTIDAD_FIELD,null)
            return modelo
        }
    }
}