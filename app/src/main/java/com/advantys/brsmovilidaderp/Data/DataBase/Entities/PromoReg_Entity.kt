package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.PromoReg_Schema
import com.advantys.brsmovilidaderp.Utils.getFloat
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString

class PromoReg_Entity (
    var promo: String?=null,
    var cliente: Short?=null,
    var articulo: String?=null,
    var fabricante: Short?=null,
    var tipoLinea: String?=null,
    var cantidad1: Float?=null,
    var cantidad2: Float?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): PromoReg_Entity {
            val modelo= PromoReg_Entity()
            modelo.promo = cursor.getString(PromoReg_Schema.PROMO_FIELD,null)
            modelo.cliente = cursor.getShort(PromoReg_Schema.CLIENTE_FIELD,null)
            modelo.articulo = cursor.getString(PromoReg_Schema.ARTICULO_FIELD,null)
            modelo.fabricante = cursor.getShort(PromoReg_Schema.FABRICANTE_FIELD,null)
            modelo.tipoLinea = cursor.getString(PromoReg_Schema.TIPOLINEA_FIELD,null)
            modelo.cantidad1 = cursor.getFloat(PromoReg_Schema.CANTIDAD1_FIELD,null)
            modelo.cantidad2 = cursor.getFloat(PromoReg_Schema.CANTIDAD2_FIELD,null)
            return modelo
        }
    }
}