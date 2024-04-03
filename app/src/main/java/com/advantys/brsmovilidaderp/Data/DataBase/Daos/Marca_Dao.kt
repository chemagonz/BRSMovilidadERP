package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Marca_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Marca_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil
import javax.inject.Inject

class Marca_Dao @Inject constructor(private val databaseManager: BDUtil){

    fun getAll(): List<Marca_Entity?> {
        var sql= "SELECT ${Marca_Schema.NOMBRE_FIELD}, ${Marca_Schema.MARCA_FIELD}  FROM ${Marca_Schema.TABLE_NAME} ORDER BY ${Marca_Schema.MARCA_FIELD}  ASC"
        return databaseManager.query(sql) { cursor ->
            Marca_Entity.fromCursor(cursor)
        }
    }
}