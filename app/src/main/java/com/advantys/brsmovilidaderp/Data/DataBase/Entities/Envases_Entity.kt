package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Envases_Schema
import com.advantys.brsmovilidaderp.Utils.getFloat
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString

class Envases_Entity (
    var cliente: Int?=null,
    var fabricante: Short?=null,
    var articulo: String?=null,
    var unVenFab: Float?=null,
    var unVenDis: Float?=null,
    var unDepFab: Float?=null,
    var unDepDis: Float?=null,
    var unRegFab: Float?=null,
    var unRegDis: Float?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): Envases_Entity {
            val modelo = Envases_Entity()
            modelo.cliente = cursor.getInt(Envases_Schema.CLIENTE_FIELD,null)
            modelo.fabricante = cursor.getShort(Envases_Schema.FABRICANTE_FIELD,null)
            modelo.articulo = cursor.getString(Envases_Schema.ARTICULO_FIELD,null)
            modelo.unVenFab = cursor.getFloat(Envases_Schema.NUNVENFAB_FIELD,null)
            modelo.unVenDis = cursor.getFloat(Envases_Schema.NUNVENDIS_FIELD,null)
            modelo.unDepFab = cursor.getFloat(Envases_Schema.NUNDEPFAB_FIELD,null)
            modelo.unDepDis = cursor.getFloat(Envases_Schema.NUNDEPDIS_FIELD,null)
            modelo.unRegFab = cursor.getFloat(Envases_Schema.NUNREGFAB_FIELD,null)
            modelo.unRegDis = cursor.getFloat(Envases_Schema.NUNREGDIS_FIELD,null)
            return modelo
        }
    }
}