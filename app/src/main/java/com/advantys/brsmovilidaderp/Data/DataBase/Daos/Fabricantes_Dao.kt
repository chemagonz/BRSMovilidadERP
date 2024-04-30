package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Fabricante_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Fabricante_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil
import javax.inject.Inject

class Fabricantes_Dao @Inject constructor(private val databaseManager: BDUtil) {
    fun getNombreFabricante(fabricante: Short?):Fabricante_Entity?{
        val sql = " SELECT ${Fabricante_Schema.TABLE_NAME}.${Fabricante_Schema.NOMBRE_FIELD} FROM ${Fabricante_Schema.TABLE_NAME} WHERE ${Fabricante_Schema.FABRICANTE_FIELD} = $fabricante"
        return databaseManager.queryDetalles(sql){ cursor ->
            Fabricante_Entity.fromCursor(cursor)
        }
    }
}