package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.content.ContentValues
import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.DetHojas_Schema
import com.advantys.brsmovilidaderp.Domain.Models.DetPedido
import com.advantys.brsmovilidaderp.Utils.getFloat
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString
import kotlin.math.abs


data class DetHojas_Entity (
    var fabricante: Short? = null,
    var articulo: String? = null,
    var unidadcaja: Float? = null,
    var carga1: Float? = null,
    var carga2: Float? = null,
    var carga3: Float? = null,
    var ventas: Float? = null,
    var abonos: Float? = null,
    var cambios: Float? = null,
    var roturas: Float? = null,
    var caducadas: Float? = null,
    var caducadasdisp: Float? = null
)
{
    companion object {
        fun fromCursor(cursor: Cursor): DetHojas_Entity {
            val modelo = DetHojas_Entity()
            modelo.fabricante = cursor.getShort(DetHojas_Schema.FABRICANTE_FIELD,null)
            modelo.articulo = cursor.getString(DetHojas_Schema.ARTICULO_FIELD,null)
            modelo.unidadcaja = cursor.getFloat(DetHojas_Schema.UNIDADESCAJA_FIELD,null)
            modelo.carga1 = cursor.getFloat(DetHojas_Schema.CARGA1_FIELD,null)
            modelo.carga2 = cursor.getFloat(DetHojas_Schema.CARGA2_FIELD,null)
            modelo.carga3 = cursor.getFloat(DetHojas_Schema.CARGA3_FIELD,null)
            modelo.ventas = cursor.getFloat(DetHojas_Schema.VENTAS_FIELD,null)
            modelo.abonos = cursor.getFloat(DetHojas_Schema.ABONOS_FIELD,null)
            modelo.cambios = cursor.getFloat(DetHojas_Schema.CAMBIOS_FIELD,null)
            modelo.roturas = cursor.getFloat(DetHojas_Schema.ROTURAS_FIELD,null)
            modelo.caducadas = cursor.getFloat(DetHojas_Schema.CADUCADAS_FIELD,null)
            modelo.caducadasdisp = cursor.getFloat(DetHojas_Schema.CADUCADASDISP_FIELD,null)
            return modelo
        }
    }

    fun toContentValues(linea: DetPedido): ContentValues {
        val values= ContentValues()

        values.put(DetHojas_Schema.FABRICANTE_FIELD, linea.fabricante)
        values.put(DetHojas_Schema.ARTICULO_FIELD, linea.articulo)
        values.put(DetHojas_Schema.UNIDADESCAJA_FIELD, linea.unidadcaja)
        values.put(DetHojas_Schema.CARGA1_FIELD, 0)
        values.put(DetHojas_Schema.CARGA2_FIELD, 0)
        values.put(DetHojas_Schema.CARGA3_FIELD, 0)
        values.put(DetHojas_Schema.VENTAS_FIELD, 0)
        values.put(DetHojas_Schema.ABONOS_FIELD, 0)
        values.put(DetHojas_Schema.ROTURAS_FIELD, 0)
        values.put(DetHojas_Schema.CAMBIOS_FIELD, 0)
        values.put(DetHojas_Schema.CADUCADAS_FIELD, 0)
        values.put(DetHojas_Schema.CADUCADASDISP_FIELD, 0)

        if (linea.cantidad1!! > 1) {
            val nCantidad = linea.cantidad1!! * linea.unidadcaja!! + linea.cantidad2!!

            if (linea.tipolinea.equals("I"))
                values.put("NROTURAS", abs(nCantidad))
            else {

                if (nCantidad > 0)
                    values.put("NVENTAS", nCantidad)
                else
                    values.put("NABONOS", nCantidad * -1)
            }

        } else {
            if (linea.tipolinea.equals("I"))
                values.put("NROTURAS", Math.abs(linea.cantidad1!!)
            ) else {

                if (linea.cantidad1!! > 0)
                    values.put("NVENTAS", linea.cantidad1)
                else
                    values.put("NABONOS", linea.cantidad1!! * -1)
            }
        }

        return values
    }
}