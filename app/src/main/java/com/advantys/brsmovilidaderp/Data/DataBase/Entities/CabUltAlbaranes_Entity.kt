package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.CabUltAlbaranes_Schema
import com.advantys.brsmovilidaderp.Utils.getBoolean
import com.advantys.brsmovilidaderp.Utils.getDate
import com.advantys.brsmovilidaderp.Utils.getFloat
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString
import java.util.Date

class CabUltAlbaranes_Entity(
    var serie: String? = null,
    var albaran: Int? = null,
    var centro: Short? = null,
    var cliente: Int? = null,
    var delegacion: Short? = null,
    var clienteFiscal: Int? = null,
    var delegacionFiscal: Short? = null,
    var formaPago: Short? = null,
    var preventa: Date? = null,
    var reparto: Date? = null,
    var rutaVenta: String? = null,
    var secuVenta: String? = null,
    var rutaReparto: String? = null,
    var secuReparto: String? = null,
    var porcDto: Float? = null,
    var facturable: String? = null,
    var tipoOperacion: Short? = null,
    var serieHoja: String? = null,
    var hoja: Int? = null,
    var suReferencia: String? = null,
    var totalAlbaran: Float? = null,
    var tipoReparto: String? = null,
    var aplDtoPor: Boolean? = null,
    var fabricante: Short? = null,
    var tarifa: Int? = null,
    var ejercicio: Int? = null,
    var ejerHoja: Int? = null
) {
    companion object {
        fun fromCursor(cursor: Cursor): CabUltAlbaranes_Entity {
            val modelo = CabUltAlbaranes_Entity()
            modelo.serie = cursor.getString(CabUltAlbaranes_Schema.SERIE_FIELD,null)
            modelo.albaran = cursor.getInt(CabUltAlbaranes_Schema.ALBARAN_FIELD,null)
            modelo.centro = cursor.getShort(CabUltAlbaranes_Schema.CENTRO_FIELD,null)
            modelo.cliente = cursor.getInt(CabUltAlbaranes_Schema.CLIENTE_FIELD,null)
            modelo.delegacion = cursor.getShort(CabUltAlbaranes_Schema.DELEGACION_FIELD,null)
            modelo.clienteFiscal = cursor.getInt(CabUltAlbaranes_Schema.CLIENTEFISCAL_FIELD,null)
            modelo.delegacionFiscal = cursor.getShort(CabUltAlbaranes_Schema.DELEGACIONFISCAL_FIELD,null)
            modelo.formaPago = cursor.getShort(CabUltAlbaranes_Schema.FORMAPAGO_FIELD,null)
            modelo.preventa = cursor.getDate(CabUltAlbaranes_Schema.PREVENTA_FIELD,null)
            modelo.reparto = cursor.getDate(CabUltAlbaranes_Schema.REPARTO_FIELD,null)
            modelo.rutaVenta = cursor.getString(CabUltAlbaranes_Schema.RUTAVENTA_FIELD,null)
            modelo.secuVenta = cursor.getString(CabUltAlbaranes_Schema.SECUVENTA_FIELD,null)
            modelo.rutaReparto = cursor.getString(CabUltAlbaranes_Schema.RUTAREPARTO_FIELD,null)
            modelo.secuReparto = cursor.getString(CabUltAlbaranes_Schema.SECUREPARTO_FIELD,null)
            modelo.porcDto = cursor.getFloat(CabUltAlbaranes_Schema.PORCDTO_FIELD,null)
            modelo.facturable = cursor.getString(CabUltAlbaranes_Schema.FACTURABLE_FIELD,null)
            modelo.tipoOperacion = cursor.getShort(CabUltAlbaranes_Schema.TIPOOPERACION_FIELD,null)
            modelo.serieHoja = cursor.getString(CabUltAlbaranes_Schema.SERIEHOJA_FIELD,null)
            modelo.hoja = cursor.getInt(CabUltAlbaranes_Schema.HOJA_FIELD,null)
            modelo.suReferencia = cursor.getString(CabUltAlbaranes_Schema.SUREFERENCIA_FIELD,null)
            modelo.totalAlbaran = cursor.getFloat(CabUltAlbaranes_Schema.TOTALALBARAN_FIELD,null)
            modelo.tipoReparto = cursor.getString(CabUltAlbaranes_Schema.TIPOREPARTO_FIELD,null)
            modelo.aplDtoPor = cursor.getBoolean(CabUltAlbaranes_Schema.APLDTOPOR_FIELD,null)
            modelo.fabricante = cursor.getShort(CabUltAlbaranes_Schema.FABRICANTE_FIELD,null)
            modelo.tarifa = cursor.getInt(CabUltAlbaranes_Schema.TARIFA_FIELD,null)
            modelo.ejercicio = cursor.getInt(CabUltAlbaranes_Schema.EJERCICIO_FIELD,null)
            modelo.ejerHoja = cursor.getInt(CabUltAlbaranes_Schema.EJERHOJA_FIELD,null)
            return modelo
        }
    }
}