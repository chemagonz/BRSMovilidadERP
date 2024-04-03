package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Formato_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Formato_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil
import javax.inject.Inject

class Formato_Dao @Inject constructor(private val databaseManager: BDUtil){

    fun getAll(): List<Formato_Entity?> {
        var sql= "SELECT ${Formato_Schema.NOMBRE_FIELD}, ${Formato_Schema.FORMATO_FIELD}  FROM ${Formato_Schema.TABLE_NAME} ORDER BY ${Formato_Schema.FORMATO_FIELD}  ASC"
        return databaseManager.query(sql) { cursor ->
            Formato_Entity.fromCursor(cursor)
        }
    }
}