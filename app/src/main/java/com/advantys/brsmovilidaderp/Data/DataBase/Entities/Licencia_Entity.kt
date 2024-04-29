package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.content.ContentValues
import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Licencia_Schema
import com.advantys.brsmovilidaderp.Domain.Models.Licencia
import com.advantys.brsmovilidaderp.Utils.getString

data class Licencia_Entity(
    var idenDisp: String?= null,
    var idenProg: String?= null,
    var licencia: String?=null,
    var cliente: String?=null,
    var numLicencia: String?=null
){
    companion object {
        fun fromCursor(cursor: Cursor): Licencia_Entity{
            val modelo= Licencia_Entity()
            modelo.idenDisp= cursor.getString(Licencia_Schema.IDENDISP_FIELD,null)
            modelo.idenProg=cursor.getString(Licencia_Schema.IDENPROG_FIELD,null)
            modelo.licencia=cursor.getString(Licencia_Schema.LICENCIA_FIELD,null)
            modelo.cliente=cursor.getString(Licencia_Schema.CLIENTE_FIELD,null)
            modelo.numLicencia=cursor.getString(Licencia_Schema.NUMLICENCIA_FIELD,null)
            return modelo
        }

    }

    fun toContentValues(): ContentValues {
        val values= ContentValues()

        values.put(Licencia_Schema.IDENDISP_FIELD, idenDisp)
        values.put(Licencia_Schema.IDENPROG_FIELD, idenProg)
        values.put(Licencia_Schema.LICENCIA_FIELD, licencia)
        values.put(Licencia_Schema.CLIENTE_FIELD, cliente)
        values.put(Licencia_Schema.NUMLICENCIA_FIELD, numLicencia)

        return values
    }
}
    fun Licencia.toEntity() = Licencia_Entity(idenDisp, idenProg, licencia, cliente, numLicencia)





