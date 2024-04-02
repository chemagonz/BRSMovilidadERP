package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.TiposIVA_Schema
import com.advantys.brsmovilidaderp.Utils.getFloat
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString

data class TiposIVA_Entity(
    var tipoIVA: Short?= null,
    var nombre: String?=null,
    var porcIVA: Float?=null,
    var porCREC: Float?= null
)
 {
     companion object {
         fun fromCursor(cursor: Cursor): TiposIVA_Entity{
             val modelo= TiposIVA_Entity()
             modelo.tipoIVA= cursor.getShort(TiposIVA_Schema.TIPOIVA_FIELD,null)
             modelo.nombre=cursor.getString(TiposIVA_Schema.NOMBRE_FIELD,null)
             modelo.porcIVA= cursor.getFloat(TiposIVA_Schema.PORCIVA_FIELD,null)
             modelo.porCREC= cursor.getFloat(TiposIVA_Schema.PORCREC,null)
             return modelo
         }
     }
}