package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Centros_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Centros_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil
import javax.inject.Inject

class Centros_Dao @Inject constructor(private val databaseManager:BDUtil) {
    fun getCentros(): List<Centros_Entity?> {
        val sql = "SELECT * FROM ${Centros_Schema.TABLE_NAME} ORDER BY ${Centros_Schema.CENTRO_FIELD} DESC"
        return databaseManager.query(sql) {
            cursor -> Centros_Entity.fromCursor(cursor)
        }
    }

    fun obtenerCentros(): List<Centros_Entity?> {
        val sql = "SELECT DISTINCT ${Centros_Schema.TABLE_NAME}.${Centros_Schema.CENTRO_FIELD}, ${Centros_Schema.TABLE_NAME}.${Centros_Schema.NOMBRE_FIELD} FROM ${Centros_Schema.TABLE_NAME} "
        return databaseManager.query(sql) {
                cursor -> Centros_Entity.fromCursor(cursor)
        }
    }

    fun getCentro(centro: Int?): Centros_Entity?{
        val sql = "SELECT * FROM ${Centros_Schema.TABLE_NAME} WHERE ${Centros_Schema.CENTRO_FIELD} = ${centro}"
        return databaseManager.queryDetalles(sql){
            cursor -> Centros_Entity.fromCursor(cursor)
        }
    }

    fun centroValido(centro: Int): Boolean {
        val sql = "SELECT ${Centros_Schema.TABLE_NAME}.${Centros_Schema.CENTRO_FIELD} FROM ${Centros_Schema.TABLE_NAME} WHERE ${Centros_Schema.CENTRO_FIELD} = $centro"
        return databaseManager.getSelectScalarBoolean(sql)
    }

    fun obtenerSerieDeCentro(centro: Int): String{
        val sql = " SELECT ${Centros_Schema.TABLE_NAME}.${Centros_Schema.SERIE_FIELD} FROM ${Centros_Schema.TABLE_NAME} WHERE ${Centros_Schema.TABLE_NAME}.${Centros_Schema.SERIE_FIELD} = $centro AND ${Centros_Schema.TABLE_NAME}.${Centros_Schema.SERIE_FIELD} IS NOT NULL"
        return databaseManager.getSelectScalarString(sql)
    }

    fun primeraSerieCentros(): String {
        val sql = " SELECT ${Centros_Schema.TABLE_NAME}.${Centros_Schema.SERIE_FIELD} FROM ${Centros_Schema.TABLE_NAME} ORDER BY ${Centros_Schema.TABLE_NAME}.${Centros_Schema.CENTRO_FIELD} "
        return databaseManager.getSelectScalarString(sql)
    }
}