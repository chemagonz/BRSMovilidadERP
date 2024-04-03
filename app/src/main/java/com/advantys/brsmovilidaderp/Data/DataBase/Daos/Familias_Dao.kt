package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Familias_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Familias_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil
import javax.inject.Inject

class Familias_Dao @Inject constructor(private val databaseManager: BDUtil) {

    fun getAll(): List<Familias_Entity?> {
        var sql= "SELECT ${Familias_Schema.NOMBRE_FIELD}, ${Familias_Schema.FAMILIA_FIELD}  FROM ${Familias_Schema.TABLE_NAME} ORDER BY ${Familias_Schema.FAMILIA_FIELD}  ASC"
        return databaseManager.query(sql) { cursor ->
            Familias_Entity.fromCursor(cursor)
        }
    }
}