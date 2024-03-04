package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import android.content.Context
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.TipoOperacion_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.TipoOperacion_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil


class TipoOperacion_Dao(context: Context){

    //Instancia de la clase BDUtil
    val databaseManager: BDUtil = BDUtil(context)
     fun getAll():List<TipoOperacion_Entity?>{
        var sql= "SELECT * FROM ${TipoOperacion_Schema.TABLE_NAME} ORDER BY ${TipoOperacion_Schema.TIPOOPERACION_FIELD} ASC"
         return databaseManager.query(sql) { cursor ->
             TipoOperacion_Entity.fromCursorA(cursor)
         }
     }
 }

















