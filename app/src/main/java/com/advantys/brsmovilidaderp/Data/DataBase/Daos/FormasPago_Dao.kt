package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.FormasPago_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.FormasPago_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil
import javax.inject.Inject

class FormasPago_Dao @Inject constructor(private val databaseManager: BDUtil) {

    fun obtenerFormasPago(): List<FormasPago_Entity> {
        val sql = " SELECT DISTINCT ${FormasPago_Schema.TABLE_NAME}.${FormasPago_Schema.FORMAPAGO_FIELD}, ${FormasPago_Schema.TABLE_NAME}.${FormasPago_Schema.NOMBRE_FIELD} FROM ${FormasPago_Schema.TABLE_NAME}"
        return databaseManager.query(sql) { cursor ->
            FormasPago_Entity.fromCursor(cursor)
        }
    }
}