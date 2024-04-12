package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Cobros_Schema
import com.advantys.brsmovilidaderp.Utils.getBoolean
import com.advantys.brsmovilidaderp.Utils.getDate
import com.advantys.brsmovilidaderp.Utils.getFloat
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString
import java.util.Date

data class Cobros_Entity (
    var cobro: Int? = null,
    var cliente: Int? = null,
    var delegacion: Short? = null,
    var fecha: Date? = null,
    var seriealbaran: String? = null,
    var albaran: Int? = null,
    var seriefactura: String? = null,
    var factura: Int? = null,
    var gasto: Int? = null,
    var cobrado: Float? = null,
    var tipopago: String? = null,
    var tipoefecto: Short? = null,
    var efecto: String? = null,
    var libramiento: Date? = null,
    var vencimiento: Date? = null,
    var asiento: Int? = null,
    var acuenta: Int? = null,
    var enviado: Boolean? = false,
    var ejeralbaran: Int? = null,
    var ejerfactura: Int? = null,
    var recibo: Int? = null,
    var datafono: String? = null
)
{
    companion object {
        fun fromCursor(cursor: Cursor): Cobros_Entity {
            val modelo = Cobros_Entity()
            modelo.cobro = cursor.getInt(Cobros_Schema.COBRO_FIELD,null)
            modelo.cliente = cursor.getInt(Cobros_Schema.CLIENTE_FIELD,null)
            modelo.delegacion = cursor.getShort(Cobros_Schema.DELEGACION_FIELD,null)
            modelo.fecha = cursor.getDate(Cobros_Schema.FECHA_FIELD,null)
            modelo.seriealbaran = cursor.getString(Cobros_Schema.SERIEALBARAN_FIELD,null)
            modelo.albaran = cursor.getInt(Cobros_Schema.ALBARAN_FIELD,null)
            modelo.seriefactura = cursor.getString(Cobros_Schema.SERIEFACTURA_FIELD,null)
            modelo.factura = cursor.getInt(Cobros_Schema.FACTURA_FIELD,null)
            modelo.gasto = cursor.getInt(Cobros_Schema.GASTO_FIELD,null)
            modelo.cobrado = cursor.getFloat(Cobros_Schema.COBRADO_FIELD,null)
            modelo.tipopago = cursor.getString(Cobros_Schema.TIPOPAGO_FIELD,null)
            modelo.tipoefecto = cursor.getShort(Cobros_Schema.TIPOEFECTO_FIELD,null)
            modelo.efecto = cursor.getString(Cobros_Schema.EFECTO_FIELD,null)
            modelo.libramiento = cursor.getDate(Cobros_Schema.LIBRAMIENTO_FIELD,null)
            modelo.vencimiento = cursor.getDate(Cobros_Schema.VENCIMIENTO_FIELD,null)
            modelo.asiento = cursor.getInt(Cobros_Schema.ASIENTO_FIELD,null)
            modelo.acuenta = cursor.getInt(Cobros_Schema.ACUENTA_FIELD,null)
            modelo.enviado = cursor.getBoolean(Cobros_Schema.ENVIADO_FIELD,null)
            return modelo
        }
    }
}
