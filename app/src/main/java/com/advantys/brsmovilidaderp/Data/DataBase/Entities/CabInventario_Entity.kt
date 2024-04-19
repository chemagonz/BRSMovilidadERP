package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.CabInventario_Schema
import com.advantys.brsmovilidaderp.Utils.getBoolean
import com.advantys.brsmovilidaderp.Utils.getDate
import com.advantys.brsmovilidaderp.Utils.getInt
import java.util.Date

class CabInventario_Entity (
    var inventario: Int? = null,
    var inicio: Date? = null,
    var fin: Date? = null,
    var enviado: Boolean? = null
) {
    companion object {
        fun fromCursor(cursor: Cursor): CabInventario_Entity {
            val modelo = CabInventario_Entity()
            modelo.inventario = cursor.getInt(CabInventario_Schema.INVENTARIO_FIELD,null)
            modelo.inicio = cursor.getDate(CabInventario_Schema.INICIO_FIELD,null)
            modelo.fin = cursor.getDate(CabInventario_Schema.FIN_FIELD,null)
            modelo.enviado = cursor.getBoolean(CabInventario_Schema.ENVIADO_FIELD,null)
            return modelo
        }
    }
}