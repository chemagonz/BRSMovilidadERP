package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.TiposIVA_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.TiposIVA_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil
import javax.inject.Inject

class TiposIVA_Dao @Inject constructor(private val databaseManager: BDUtil){
    fun getIVA(IVA:Short?): TiposIVA_Entity?{
        var sql= "SELECT ${TiposIVA_Schema.PORCIVA_FIELD}, ${TiposIVA_Schema.PORCREC} FROM ${TiposIVA_Schema.TABLE_NAME} WHERE ${TiposIVA_Schema.TIPOIVA_FIELD} = ${IVA}"
        return  databaseManager.queryDetalles(sql){cursor ->
            TiposIVA_Entity.fromCursor(cursor)
        }
    }
}