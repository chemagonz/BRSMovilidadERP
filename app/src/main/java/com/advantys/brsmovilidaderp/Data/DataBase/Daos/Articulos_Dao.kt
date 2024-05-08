package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Articulos_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Articulos_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil
import com.advantys.brsmovilidaderp.Utils.BuscarArticulosPor
import javax.inject.Inject

class Articulos_Dao @Inject constructor(private val databaseManager: BDUtil) {

    fun getAll(): List<Articulos_Entity?> {
        var sql= "SELECT * FROM ${Articulos_Schema.TABLE_NAME} ORDER BY ${Articulos_Schema.ARTICULO_FIELD} DESC"
        return databaseManager.query(sql) { cursor ->
            Articulos_Entity.fromCursor(cursor)
        }
    }

    fun obtenerWhere(buscar: BuscarArticulosPor, codfamilia:Short?, codsubfamilia:Short?, codformato: Int?, codmarca:String?, codsabor:String?, tipoConsulta: String?): String{
        var where= ""

        var condicion = false
        if (!tipoConsulta.isNullOrEmpty()) {
            when (buscar) {
                BuscarArticulosPor.codigo -> {
                    where += "${Articulos_Schema.TABLE_NAME}.${Articulos_Schema.ARTICULO_FIELD} LIKE '$tipoConsulta%'"
                    condicion = true
                }
                BuscarArticulosPor.descripcion -> {
                    where += "${Articulos_Schema.TABLE_NAME}.${Articulos_Schema.NOMBRE_FIELD} LIKE '%$tipoConsulta%'"
                    condicion = true
                }
            }
        }

//        if(codfamilia?.toInt() != -1)
//            where += " AND ${Articulos_Schema.TABLE_NAME}.${Articulos_Schema.FAMILIA_FIELD} = $codfamilia "
//        if(codsubfamilia?.toInt() != -1)
//            where += " AND ${Articulos_Schema.TABLE_NAME}.${Articulos_Schema.SUBFAMILIA_FIELD} = $codsubfamilia "
//        if(codformato?.toInt() != -1)
//            where += " AND ${Articulos_Schema.TABLE_NAME}.${Articulos_Schema.FORMATO_FIELD} = $codformato "
//        if(!codmarca.equals("") && !codmarca.equals("-1"))
//            where += " AND ${Articulos_Schema.TABLE_NAME}.${Articulos_Schema.MARCA_FIELD} = '${codmarca}' "
//        if(!codsabor.equals("") && !codsabor.equals("-1"))
//            where += " AND ${Articulos_Schema.TABLE_NAME}.${Articulos_Schema.SABOR_FIELD} = '${codsabor}' "

        fun hayCondicion(condition: String) {
            if (condicion) {
                where += " AND $condition"
            } else {
                where += " $condition"
                condicion = true
            }
        }

        codfamilia?.let {
            if (it.toInt() != -1) {
                hayCondicion("${Articulos_Schema.TABLE_NAME}.${Articulos_Schema.FAMILIA_FIELD} = $it")
            }
        }
        codsubfamilia?.let {
            if (it.toInt() != -1) {
                hayCondicion("${Articulos_Schema.TABLE_NAME}.${Articulos_Schema.SUBFAMILIA_FIELD} = $it")
            }
        }
        codformato?.let {
            if (it != -1) {
                hayCondicion("${Articulos_Schema.TABLE_NAME}.${Articulos_Schema.FORMATO_FIELD} = $it")
            }
        }
        codmarca?.let {
            if (it != "-1") {
                hayCondicion("${Articulos_Schema.TABLE_NAME}.${Articulos_Schema.MARCA_FIELD} = '$it'")
            }
        }
        codsabor?.let {
            if (it != "-1") {
                hayCondicion("${Articulos_Schema.TABLE_NAME}.${Articulos_Schema.SABOR_FIELD} = '$it'")
            }
        }




//        if(articulo != null && fabricante != null){
//            where += " AND ${Articulos_Schema.ARTICULO_AUX_FIELD} = '${articulo}' OR ${Articulos_Schema.ARTICULO_FIELD} = '${articulo}'"
//            if(!fabricante.equals("-1"))
//                where += " AND ${Articulos_Schema.TABLE_NAME}.${Articulos_Schema.FABRICANTE_FIELD} = ${fabricante}"
//        }
        return where
    }

    fun obtenerArticulos(buscar: BuscarArticulosPor, codfamilia:Short?, codsubfamilia:Short?, codformato: Int?, codmarca:String?, codsabor:String?, tipoConsulta: String?):List<Articulos_Entity?>{
        var sql= " SELECT ${Articulos_Schema.NOMBRE_FIELD}, ${Articulos_Schema.ARTICULO_FIELD} FROM ${Articulos_Schema.TABLE_NAME} "
        val where= obtenerWhere(buscar, codfamilia, codsubfamilia, codformato, codmarca, codsabor, tipoConsulta)
       if(!where.isNullOrEmpty()){
          sql +=  " WHERE $where"
        }


        return databaseManager.query(sql){ cursor ->
            Articulos_Entity.fromCursor(cursor)
        }
    }

    fun getFilter(columna: BuscarArticulosPor, tipoConsulta: String?):List<Articulos_Entity?>{


        var tipoconsulta= tipoConsulta
        val columnas= when(columna){
            BuscarArticulosPor.descripcion->{
                tipoconsulta= "'%${tipoConsulta}%'"
                Articulos_Schema.NOMBRE_FIELD
            }
            BuscarArticulosPor.codigo->{
                tipoconsulta="'${tipoConsulta}%'"
                Articulos_Schema.ARTICULO_FIELD
            }
        }


        var sql= "SELECT * FROM ${Articulos_Schema.TABLE_NAME} WHERE ${columnas} LIKE '${tipoConsulta}'"
        return databaseManager.query(sql){cursor ->
            Articulos_Entity.fromCursor(cursor)
        }
    }


    fun getDetalles(articulo:String?): Articulos_Entity?{
        val sql= "SELECT ${Articulos_Schema.ARTICULO_FIELD},${Articulos_Schema.ARTICULO_RET_FIELD}, ${Articulos_Schema.FABRICANTE_RET_FIELD}, ${Articulos_Schema.UNIDADES_CAJA_FIELD}, ${Articulos_Schema.NOMBRE_FIELD}, ${Articulos_Schema.NOMBRE_CORTO_FIELD},${Articulos_Schema.TIPOIVA_FIELD},${Articulos_Schema.PRECOSTE_FIELD}, ${Articulos_Schema.PREULT_COMPRA_FIELD},${Articulos_Schema.DISPONIBLE1_FIELD}, ${Articulos_Schema.DISPONIBLE2_FIELD},${Articulos_Schema.PUNTO_VERDE_FIELD},${Articulos_Schema.ALCOHOL_FIELD}, ${Articulos_Schema.MANIPULACION_FIELD} FROM ${Articulos_Schema.TABLE_NAME} WHERE ${Articulos_Schema.TABLE_NAME}.${Articulos_Schema.ARTICULO_FIELD} = '${articulo}' "
        return  databaseManager.queryDetalles(sql){cursor ->
            Articulos_Entity.fromCursor(cursor)
        }
    }


}