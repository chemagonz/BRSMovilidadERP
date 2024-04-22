package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.PendientesCobro_Schema
import com.advantys.brsmovilidaderp.Utils.getBoolean
import com.advantys.brsmovilidaderp.Utils.getDate
import com.advantys.brsmovilidaderp.Utils.getFloat
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString
import java.util.Date

class PendientesCobro_Entity (
    var cliente: Int?=null,
    var delegacion: Short?=null,
    var fecha: Date?=null,
    var serieAlbaran: String?=null,
    var albaran: Int?=null,
    var serieFactura: String?=null,
    var factura: Int?=null,
    var nEfecto: Int?=null,
    var cEfecto: String?=null,
    var estado: String?=null,
    var gasto: Int?=null,
    var pendiente: Float?=null,
    var tipoEfecto: Short?=null,
    var vencimiento: Date?=null,
    var lMarcado: Boolean?=null,
    var cMarcado: String?=null,
    var ejerAlbaran: Int?=null,
    var ejerFactura: Int?=null,
    var recibo: Int?=null,
    var totalDocumento: Float?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): PendientesCobro_Entity {
            val modelo= PendientesCobro_Entity()
            modelo.cliente = cursor.getInt(PendientesCobro_Schema.CLIENTE_FIELD,null)
            modelo.delegacion = cursor.getShort(PendientesCobro_Schema.DELEGACION_FIELD,null)
            modelo.fecha = cursor.getDate(PendientesCobro_Schema.FECHA_FIELD,null)
            modelo.serieAlbaran = cursor.getString(PendientesCobro_Schema.SERIEALBARAN_FIELD,null)
            modelo.albaran = cursor.getInt(PendientesCobro_Schema.ALBARAN_FIELD,null)
            modelo.serieFactura = cursor.getString(PendientesCobro_Schema.SERIEFACTURA_FIELD,null)
            modelo.factura = cursor.getInt(PendientesCobro_Schema.FACTURA_FIELD,null)
            modelo.nEfecto = cursor.getInt(PendientesCobro_Schema.NEFECTO_FIELD,null)
            modelo.cEfecto = cursor.getString(PendientesCobro_Schema.CEFECTO_FIELD,null)
            modelo.estado = cursor.getString(PendientesCobro_Schema.ESTADO_FIELD,null)
            modelo.gasto = cursor.getInt(PendientesCobro_Schema.GASTO_FIELD,null)
            modelo.pendiente = cursor.getFloat(PendientesCobro_Schema.PENDIENTE_FIELD,null)
            modelo.tipoEfecto = cursor.getShort(PendientesCobro_Schema.TIPOEFECTO_FIELD,null)
            modelo.vencimiento = cursor.getDate(PendientesCobro_Schema.VENCIMIENTO_FIELD,null)
            modelo.lMarcado = cursor.getBoolean(PendientesCobro_Schema.LMARCADO_FIELD,null)
            modelo.cMarcado = cursor.getString(PendientesCobro_Schema.CMARCADO_FIELD,null)
            modelo.ejerAlbaran = cursor.getInt(PendientesCobro_Schema.EJERALBARAN_FIELD,null)
            modelo.ejerFactura = cursor.getInt(PendientesCobro_Schema.EJERFACTURA_FIELD,null)
            modelo.recibo = cursor.getInt(PendientesCobro_Schema.RECIBO_FIELD,null)
            modelo.totalDocumento = cursor.getFloat(PendientesCobro_Schema.TOTALDOCUMENTO_FIELD,null)
            return modelo
        }
    }
}