package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.DetHojas_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.DetHojas_Schema
import com.advantys.brsmovilidaderp.Domain.Models.DetPedido
import com.advantys.brsmovilidaderp.Utils.BDUtil
import javax.inject.Inject

class DetHojas_Dao @Inject constructor(private val databaseManager: BDUtil) {

    fun insertaHojaCarga(linea: DetPedido): Boolean {
        var ok = false
        val detHojas = DetHojas_Entity()
        try {
            databaseManager.insert(DetHojas_Schema.TABLE_NAME, detHojas.toContentValues(linea))
            ok = true
        }catch(e: Exception){
            e.printStackTrace()
        }
        return ok
    }

    private fun actualizaAbonoVenta(linea: DetPedido, cOperacion: String) {
        var sql = " UPDATE ${DetHojas_Schema.TABLE_NAME} SET"

        if (linea.unidadcaja!! > 1) {
            val nCantidad = linea.cantidad1!! * linea.unidadcaja!! + linea.cantidad2!!

            if (linea.cantidad1!! >= 0 && linea.cantidad2!! >= 0) {
                sql += "${DetHojas_Schema.TABLE_NAME}.${DetHojas_Schema.VENTAS_FIELD} = ${DetHojas_Schema.TABLE_NAME}.${DetHojas_Schema.VENTAS_FIELD} " + cOperacion + nCantidad
                sql += ", ${DetHojas_Schema.TABLE_NAME}.${DetHojas_Schema.CARGA1_FIELD} = ${DetHojas_Schema.TABLE_NAME}.${DetHojas_Schema.CARGA1_FIELD}" + cOperacion + " " + nCantidad * -1
            } else {

                if (linea.cantidad1!! <= 0 && linea.cantidad2!! <= 0)
                    sql += "${DetHojas_Schema.TABLE_NAME}.${DetHojas_Schema.ABONOS_FIELD} = ${DetHojas_Schema.TABLE_NAME}.${DetHojas_Schema.ABONOS_FIELD} " + cOperacion + " " + nCantidad * -1
            }

        } else {

            if (linea.cantidad1!! >= 0) {
                sql += "${DetHojas_Schema.TABLE_NAME}.${DetHojas_Schema.VENTAS_FIELD} = ${DetHojas_Schema.TABLE_NAME}.${DetHojas_Schema.VENTAS_FIELD} " + cOperacion + " " + linea.cantidad1!!
                sql += ", ${DetHojas_Schema.TABLE_NAME}.${DetHojas_Schema.CARGA1_FIELD} = ${DetHojas_Schema.TABLE_NAME}.${DetHojas_Schema.CARGA1_FIELD} " + cOperacion + " " + linea.cantidad1!! * -1
            } else
                sql += "${DetHojas_Schema.TABLE_NAME}.${DetHojas_Schema.ABONOS_FIELD} = ${DetHojas_Schema.TABLE_NAME}.${DetHojas_Schema.ABONOS_FIELD} " + cOperacion + " " + linea.cantidad1!! * -1
        }

        sql += " WHERE ${DetHojas_Schema.TABLE_NAME}.${DetHojas_Schema.FABRICANTE_FIELD}  = " + linea.fabricante + " AND ${DetHojas_Schema.TABLE_NAME}.${DetHojas_Schema.ARTICULO_FIELD} =  '" + linea.articulo + "'"
    }

    fun lEstaEnHojaCarga(articulo: String, fabricante: Int): Boolean {
        val sql = " SELECT * FROM ${DetHojas_Schema.TABLE_NAME} WHERE ${DetHojas_Schema.TABLE_NAME}.${DetHojas_Schema.ARTICULO_FIELD} = '$articulo' AND ${DetHojas_Schema.TABLE_NAME}.${DetHojas_Schema.FABRICANTE_FIELD} = $fabricante"
        return databaseManager.getSelectScalarBoolean(sql)
    }


}