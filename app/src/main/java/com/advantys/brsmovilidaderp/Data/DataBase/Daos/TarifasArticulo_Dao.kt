package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import android.os.Build
import androidx.annotation.RequiresApi
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.TarifasArticulos_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Tarfias_Schema
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.TarifasArticulo_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil
import javax.inject.Inject

class TarifasArticulo_Dao @Inject constructor(private val databaseManager: BDUtil)  {

    @RequiresApi(Build.VERSION_CODES.O)
    fun getTarifa(articulo:String?, fabricante: Short?): List<TarifasArticulos_Entity?>{
        var sql= "SELECT ${TarifasArticulo_Schema.TABLE_NAME}.${TarifasArticulo_Schema.TARIFA_FIELD},${TarifasArticulo_Schema.TABLE_NAME}.${TarifasArticulo_Schema.PVP_FIELD}, ${TarifasArticulo_Schema.TABLE_NAME}.${TarifasArticulo_Schema.FABRICANTE_FIELD}, ${Tarfias_Schema.TABLE_NAME}.${Tarfias_Schema.NOMBRE_FIELD} FROM ${TarifasArticulo_Schema.TABLE_NAME} JOIN ${Tarfias_Schema.TABLE_NAME} ON ${Tarfias_Schema.TARIFA_FIELD} = ${TarifasArticulo_Schema.TARIFA_FIELD} WHERE ${TarifasArticulo_Schema.ARTICULO_FIELD} = '${articulo}' AND ${TarifasArticulo_Schema.FABRICANTE_FIELD} = ${fabricante}"
        return  databaseManager.query(sql){cursor ->
            TarifasArticulos_Entity.fromCursor(cursor)
        }
    }
}