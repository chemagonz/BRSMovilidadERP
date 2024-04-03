package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Subfamilias_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Subfamilias_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil
import javax.inject.Inject

class Subfamilias_Dao @Inject constructor(private val databaseManager: BDUtil){

    fun getAll(): List<Subfamilias_Entity?> {
        var sql= "SELECT ${Subfamilias_Schema.NOMBRE_FIELD}, ${Subfamilias_Schema.SUBFAMILIA_FIELD}  FROM ${Subfamilias_Schema.TABLE_NAME} ORDER BY ${Subfamilias_Schema.SUBFAMILIA_FIELD}  ASC"
        return databaseManager.query(sql) { cursor ->
            Subfamilias_Entity.fromCursor(cursor)
        }
    }
}