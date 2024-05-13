package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.TipoOperacion_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.TipoOperacion_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil
import javax.inject.Inject


class TipoOperacion_Dao @Inject constructor(private val databaseManager:BDUtil){
     fun getAll():List<TipoOperacion_Entity?>{
        var sql= "SELECT * FROM ${TipoOperacion_Schema.TABLE_NAME} ORDER BY ${TipoOperacion_Schema.TIPOOPERACION_FIELD} ASC"
         return databaseManager.query(sql) { cursor ->
             TipoOperacion_Entity.fromCursor(cursor)
         }
     }

    fun obtenerTiposOperacion():List<TipoOperacion_Entity?>{
        var sql = "SELECT DISTINCT ${TipoOperacion_Schema.TABLE_NAME}.${TipoOperacion_Schema.TIPOOPERACION_FIELD},${TipoOperacion_Schema.TABLE_NAME}.${TipoOperacion_Schema.NOMBRE_FIELD}  FROM ${TipoOperacion_Schema.TABLE_NAME} "
        return databaseManager.query(sql) { cursor ->
            TipoOperacion_Entity.fromCursor(cursor)
        }
    }

    fun obtenerTiposOperacionDatos(tipoOperacion: Int): TipoOperacion_Entity? {
        val sql = "SELECT * FROM ${TipoOperacion_Schema.TABLE_NAME} WHERE ${TipoOperacion_Schema.TABLE_NAME}.${TipoOperacion_Schema.TIPOOPERACION_FIELD} = ? "
        return databaseManager.queryDetalles(sql) { cursor ->
            TipoOperacion_Entity.fromCursor(cursor)
        }
    }


    fun getNombreOperacion(operacion:Short?): TipoOperacion_Entity?{
        var sql = "SELECT ${TipoOperacion_Schema.NOMBRE_FIELD},${TipoOperacion_Schema.TIPOOPERACION_FIELD} FROM ${TipoOperacion_Schema.TABLE_NAME} WHERE ${TipoOperacion_Schema.TIPOOPERACION_FIELD} = $operacion"
        return databaseManager.queryDetalles(sql){ cursor ->
           TipoOperacion_Entity.fromCursor(cursor)
        }
    }
}

















