package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Articulos_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Articulos_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil
import com.advantys.brsmovilidaderp.Utils.buscarArticulosPor
import javax.inject.Inject

class Articulos_Dao @Inject constructor(private val databaseManager: BDUtil) {

    fun getAll(): List<Articulos_Entity?> {
        var sql= "SELECT * FROM ${Articulos_Schema.TABLE_NAME} ORDER BY ${Articulos_Schema.ARTICULO_FIELD} DESC"
        return databaseManager.query(sql) { cursor ->
            Articulos_Entity.fromCursor(cursor)
        }
    }

    fun getFilter(columna: buscarArticulosPor, tipoConsulta: String?):List<Articulos_Entity?>{
        var tipoconsulta= tipoConsulta
        val columnas= when(columna){
            buscarArticulosPor.descripcion->{
                tipoconsulta= "'%${tipoConsulta}%'"
                Articulos_Schema.NOMBRE_FIELD
            }
            buscarArticulosPor.codigo->{
                tipoconsulta="'${tipoConsulta}%'"
                Articulos_Schema.ARTICULO_FIELD
            }
        }
        var sql= "SELECT * FROM ${Articulos_Schema.TABLE_NAME} WHERE ${columnas} LIKE '%${tipoConsulta}%'"
        return databaseManager.query(sql){cursor ->
            Articulos_Entity.fromCursor(cursor)
        }
    }

//    fun obtenerWhere(unitario:Boolean):String{
//        var where: String= " WHERE "
//        if(unitario) where += " ${Articulos_Schema.TABLE_NAME}.${Articulos_Schema.UNIDADES_CAJA_FIELD} >= 1 AND ${Articulos_Schema.TABLE_NAME}.${Articulos_Schema.TIPOIVA_FIELD} = ${TiposIVA_Schema.TABLE_NAME}.${TiposIVA_Schema.TIPOIVA_FIELD} "
//        else where += " ${Articulos_Schema.TABLE_NAME}.${Articulos_Schema.UNIDADES_CAJA_FIELD} = 0 AND ${Articulos_Schema.TABLE_NAME}.${Articulos_Schema.TIPOIVA_FIELD} = ${TiposIVA_Schema.TABLE_NAME}.${TiposIVA_Schema.TIPOIVA_FIELD} "
//    }

    fun getDetalles(articulo:String?, fabricante: Short?): Articulos_Entity?{
        var sql= "SELECT ${Articulos_Schema.ARTICULO_FIELD},${Articulos_Schema.ARTICULO_RET_FIELD}, ${Articulos_Schema.FABRICANTE_RET_FIELD}, ${Articulos_Schema.UNIDADES_CAJA_FIELD}, ${Articulos_Schema.NOMBRE_FIELD}, ${Articulos_Schema.NOMBRE_CORTO_FIELD},${Articulos_Schema.TIPOIVA_FIELD},${Articulos_Schema.PRECOSTE_FIELD}, ${Articulos_Schema.PREULT_COMPRA_FIELD},${Articulos_Schema.DISPONIBLE1_FIELD}, ${Articulos_Schema.DISPONIBLE2_FIELD},${Articulos_Schema.PUNTO_VERDE_FIELD},${Articulos_Schema.ALCOHOL_FIELD}, ${Articulos_Schema.MANIPULACION_FIELD} FROM ${Articulos_Schema.TABLE_NAME} WHERE ${Articulos_Schema.ARTICULO_FIELD} = '${articulo}' AND ${Articulos_Schema.FABRICANTE_FIELD} = ${fabricante}"
        return  databaseManager.queryDetalles(sql){cursor ->
            Articulos_Entity.fromCursor(cursor)
        }
    }
}