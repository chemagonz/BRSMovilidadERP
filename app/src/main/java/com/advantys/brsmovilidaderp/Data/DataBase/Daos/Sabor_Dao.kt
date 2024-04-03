package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Sabor_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Sabor_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil
import javax.inject.Inject

class Sabor_Dao @Inject constructor(private val databaseManager: BDUtil){

    fun getAll(): List<Sabor_Entity?> {
        var sql= "SELECT ${Sabor_Schema.NOMBRE_FIELD}, ${Sabor_Schema.SABOR_FIELD}  FROM ${Sabor_Schema.TABLE_NAME} ORDER BY ${Sabor_Schema.SABOR_FIELD}  ASC"
        return databaseManager.query(sql) { cursor ->
            Sabor_Entity.fromCursor(cursor)
        }
    }
}