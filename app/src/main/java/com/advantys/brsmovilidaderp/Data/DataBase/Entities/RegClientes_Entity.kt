package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.RegClientes_Schema
import com.advantys.brsmovilidaderp.Utils.getDate
import com.advantys.brsmovilidaderp.Utils.getDouble
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString
import java.util.Date

class RegClientes_Entity (
    var cliente: Int?=null,
    var cliente2: Int?=null,
    var delegacion: Short?=null,
    var fabricante: Short?=null,
    var articulo: String?=null,
    var maximo: Double?=null,
    var desde: Date?=null,
    var hasta: Date?=null,
    var regalos: Double?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): RegClientes_Entity {
            val modelo= RegClientes_Entity()
            modelo.cliente = cursor.getInt(RegClientes_Schema.CLIENTE_FIELD,null)
            modelo.cliente2 = cursor.getInt(RegClientes_Schema.CLIENTE2_FIELD,null)
            modelo.delegacion = cursor.getShort(RegClientes_Schema.DELEGACION_FIELD,null)
            modelo.fabricante = cursor.getShort(RegClientes_Schema.FABRICANTE_FIELD,null)
            modelo.articulo = cursor.getString(RegClientes_Schema.ARTICULO_FIELD,null)
            modelo.maximo = cursor.getDouble(RegClientes_Schema.MAXIMO_FIELD,null)
            modelo.desde = cursor.getDate(RegClientes_Schema.DESDE_FIELD,null)
            modelo.hasta = cursor.getDate(RegClientes_Schema.HASTA_FIELD,null)
            modelo.regalos = cursor.getDouble(RegClientes_Schema.REGALOS_FIELD,null)
            return modelo
        }
    }
}