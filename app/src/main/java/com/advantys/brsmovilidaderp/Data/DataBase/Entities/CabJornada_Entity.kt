package com.advantys.brsmovilidaderp.Data.DataBase.Entities
import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.CabJornada_Schema
import com.advantys.brsmovilidaderp.Utils.getBoolean
import com.advantys.brsmovilidaderp.Utils.getDate
import com.advantys.brsmovilidaderp.Utils.getFloat
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getString
import java.util.Date

class CabJornada_Entity (
    var jornada: Int? = null,
    var inicio: Date? = null,
    var fin: Date? = null,
    var totalGasto: Float? = null,
    var totalRecuento: Float? = null,
    var vehiculo: String? = null,
    var kilometros: Int? = null,
    var enviado: Boolean? = null,
    var totalIngreso: Float? = null,
    var liquidacion: String? = null,
    var saldoFinal: Float? = null,
    var saldoApertura: Float? = null
) {
    companion object {
        fun fromCursor(cursor: Cursor): CabJornada_Entity {
            val modelo = CabJornada_Entity()
            modelo.jornada = cursor.getInt(CabJornada_Schema.JORNADA_FIELD,null)
            modelo.inicio = cursor.getDate(CabJornada_Schema.INICIO_FIELD,null)
            modelo.fin = cursor.getDate(CabJornada_Schema.FIN_FIELD,null)
            modelo.totalGasto = cursor.getFloat(CabJornada_Schema.TOTALGASTO_FIELD,null)
            modelo.totalRecuento = cursor.getFloat(CabJornada_Schema.TOTALRECUENTO_FIELD,null)
            modelo.vehiculo = cursor.getString(CabJornada_Schema.VEHICULO_FIELD,null)
            modelo.kilometros = cursor.getInt(CabJornada_Schema.KILOMETROS_FIELD,null)
            modelo.enviado = cursor.getBoolean(CabJornada_Schema.ENVIADO_FIELD,null)
            modelo.totalIngreso = cursor.getFloat(CabJornada_Schema.TOTALINGRESO_FIELD,null)
            modelo.liquidacion = cursor.getString(CabJornada_Schema.LIQUIDACION_FIELD,null)
            modelo.saldoFinal = cursor.getFloat(CabJornada_Schema.SALDOFINAL_FIELD,null)
            modelo.saldoApertura = cursor.getFloat(CabJornada_Schema.SALDOAPERTURA_FIELD,null)
            return modelo
        }
    }
}
