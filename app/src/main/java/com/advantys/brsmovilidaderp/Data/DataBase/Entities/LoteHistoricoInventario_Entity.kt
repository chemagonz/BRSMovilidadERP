package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.LoteHistoricoInventario_Schema
import com.advantys.brsmovilidaderp.Utils.getFloat
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString

class LoteHistoricoInventario_Entity  (
    var fabricante: Short?=null,
    var articulo: String?=null,
    var lote: String?=null,
    var orden: Short?=null,
    var cantidad1: Float?=null,
    var cantidad2: Float?=null,
    var numInventario: Int?=null,
    var ordenArticulo: Int?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): LoteHistoricoInventario_Entity {
            val modelo= LoteHistoricoInventario_Entity()
            modelo.fabricante = cursor.getShort(LoteHistoricoInventario_Schema.FABRICANTE_FIELD,null)
            modelo.articulo = cursor.getString(LoteHistoricoInventario_Schema.ARTICULO_FIELD,null)
            modelo.lote = cursor.getString(LoteHistoricoInventario_Schema.LOTE_FIELD,null)
            modelo.orden = cursor.getShort(LoteHistoricoInventario_Schema.ORDEN_FIELD,null)
            modelo.cantidad1 = cursor.getFloat(LoteHistoricoInventario_Schema.CANTIDAD1_FIELD,null)
            modelo.cantidad2 = cursor.getFloat(LoteHistoricoInventario_Schema.CANTIDAD2_FIELD,null)
            modelo.numInventario = cursor.getInt(LoteHistoricoInventario_Schema.NUMINVENTARIO_FIELD,null)
            modelo.ordenArticulo = cursor.getInt(LoteHistoricoInventario_Schema.ORDENARTICULO_FIELD,null)
            return modelo
        }
    }
}