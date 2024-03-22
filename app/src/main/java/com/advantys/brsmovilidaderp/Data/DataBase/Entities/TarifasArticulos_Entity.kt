package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import android.os.Build
import androidx.annotation.RequiresApi
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.TarifasArticulo_Schema
import com.advantys.brsmovilidaderp.Utils.getFloat
import com.advantys.brsmovilidaderp.Utils.getLocalDateTime
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString
import java.time.LocalDateTime

data class TarifasArticulos_Entity(
    var numTarifa: Short?= null,
    var numFabricante: Short?=null,
    var articulo: String?=null,
    var pvp: Float?= null,
    var desde: LocalDateTime?= null,
    var hasta: LocalDateTime?=null,
    var porcdto1: Float?=null,
    var impodto1: Float?= null,
    var porcdto2: Float?= null,
    var impodto2: Float?=null

) {
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun fromCursor(cursor: Cursor): TarifasArticulos_Entity{
            val modelo= TarifasArticulos_Entity()
            modelo.numTarifa= cursor.getShort(TarifasArticulo_Schema.TARIFA_FIELD,null)
            modelo.numFabricante= cursor.getShort(TarifasArticulo_Schema.FABRICANTE_FIELD,null)
            modelo.articulo= cursor.getString(TarifasArticulo_Schema.ARTICULO_FIELD,null)
            modelo.pvp= cursor.getFloat(TarifasArticulo_Schema.PVP_FIELD,null)
            modelo.desde= cursor.getLocalDateTime(TarifasArticulo_Schema.DESDE_FIELD,null)
            modelo.hasta= cursor.getLocalDateTime(TarifasArticulo_Schema.HASTA_FIELD,null)
            modelo.porcdto1= cursor.getFloat(TarifasArticulo_Schema.PORCDTO1_FIELD,null)
            modelo.impodto1= cursor.getFloat(TarifasArticulo_Schema.IMPODTO1_FIELD,null)
            modelo.porcdto2= cursor.getFloat(TarifasArticulo_Schema.PORCDTO2_FIELD,null)
            modelo.impodto2= cursor.getFloat(TarifasArticulo_Schema.IMPODTO2_FIELD, null)
            return modelo
        }
    }
}