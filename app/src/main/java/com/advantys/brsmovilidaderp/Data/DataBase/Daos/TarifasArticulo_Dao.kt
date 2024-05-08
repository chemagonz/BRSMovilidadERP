package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import android.os.Build
import androidx.annotation.RequiresApi
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.TarifasArticulos_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.TarifasArticulo_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil
import javax.inject.Inject

class TarifasArticulo_Dao @Inject constructor(private val databaseManager: BDUtil)  {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getTarifa(articulo:String?): List<TarifasArticulos_Entity?>{
        var sql= "SELECT ${TarifasArticulo_Schema.TARIFA_FIELD},${TarifasArticulo_Schema.PVP_FIELD}  FROM ${TarifasArticulo_Schema.TABLE_NAME} WHERE ${TarifasArticulo_Schema.ARTICULO_FIELD} = '${articulo}' "
        return  databaseManager.query(sql){cursor ->
            TarifasArticulos_Entity.fromCursor(cursor)
        }
    }
}