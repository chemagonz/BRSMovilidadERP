package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Articulos_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Articulos_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil
import javax.inject.Inject

class Articulos_Dao @Inject constructor(private val databaseManager: BDUtil) {

    fun getAll(): List<Articulos_Entity?> {
        var sql= "SELECT * FROM ${Articulos_Schema.TABLE_NAME} ORDER BY ${Articulos_Schema.ARTICULO_FIELD} DESC"
        return databaseManager.query(sql) { cursor ->
            Articulos_Entity.fromCursor(cursor)
        }
    }

    fun getFilter(tipoConsulta: String?):List<Articulos_Entity?>{
        var sql= "SELECT * FROM ${Articulos_Schema.TABLE_NAME} WHERE ${Articulos_Schema.NOMBRE_FIELD} LIKE '${tipoConsulta}%'"
        return databaseManager.query(sql){cursor ->
            Articulos_Entity.fromCursor(cursor)
        }
    }

    fun getDetalles(articulo:String?): Articulos_Entity?{
        var sql= "SELECT ${Articulos_Schema.ARTICULO_FIELD}, ${Articulos_Schema.UDS_CAJA_FIELD}, ${Articulos_Schema.NOMBRE_FIELD}, ${Articulos_Schema.NOMBRE_CORTO_FIELD}, ${Articulos_Schema.TIPOIVA_FIELD}, ${Articulos_Schema.PRECOSTE_FIELD}, ${Articulos_Schema.PREULT_COMPRA_FIELD},  ${Articulos_Schema.PUNTO_VERDE_FIELD},${Articulos_Schema.ALCOHOL_FIELD}, ${Articulos_Schema.MANIPULACION_FIELD}  FROM ${Articulos_Schema.TABLE_NAME} WHERE ${Articulos_Schema.ARTICULO_FIELD} = '${articulo}'"
        return  databaseManager.queryDetalles(sql){cursor ->
            Articulos_Entity.fromCursor(cursor)
        }
    }
}