package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.CabPedidos_Schema
import com.advantys.brsmovilidaderp.Utils.getBoolean
import com.advantys.brsmovilidaderp.Utils.getDate
import com.advantys.brsmovilidaderp.Utils.getFloat
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString
import java.util.Date

data class CabPedidos_Entity (
    var serie: String? = null,
    var pedido: Int? = null,
    var centro: Short? = null,
    var cliente: Int? = null,
    var delegacion: Short? = null,
    var clientefiscal: Int? = null,
    var delegacionfiscal: Short? = null,
    var formapago: Short? = null,
    var cpreventa: Date? = null,
    var reparto: Date? = null,
    var rutaventa: String? = null,
    var secuventa: String? = null,
    var rutareparto: String? = null,
    var secureparto: String? = null,
    var porcdto: Float? = null,
    var facturable: String? = null,
    var tipooperacion: Short? = null,
    var seriehoja: String? = null,
    var hoja: Int? = null,
    var sureferencia: String? = null,
    var totalpedido:Float?= null,
    var enviado: Boolean? = false,
    var tiporeparto: String? = null,
    var apldtopor: Boolean? = null,
    var fabricante: Short? = null,
    var npreventa: Int? = null,
    var seriealbaran: String? = null,
    var albaran: Int? = null,
    var fechaalbaran: Date? = null,
    var seriefactura: String? = null,
    var factura: Int? = null,
    var fechafactura: Date? = null,
    var notas: String? = null,
    var carga: Short? = null,
    var impreso: Boolean? = null,
    var pendientevalidar: String? = null,
    var importado: Boolean? = null,
    var tarifa: Int? = null,
    var ejercicio: Int? = null,
    var ejerhoja: Int? = null,
    var ejeralbaran: Int? = null,
    var ejerfactura: Int? = null,
    var situacion: Int? = 0,
    var repetir: Date? = null,
    var dnidestinatario: String? = null,
    var nombredestinatario: String? = null
){
    companion object {
        fun fromCursor(cursor: Cursor): CabPedidos_Entity {
            val modelo = CabPedidos_Entity()
            modelo.serie = cursor.getString(CabPedidos_Schema.SERIE_FIELD,null)
            modelo.pedido = cursor.getInt(CabPedidos_Schema.PEDIDO_FIELD, null)
            modelo.centro = cursor.getShort(CabPedidos_Schema.CENTRO_FIELD, null)
            modelo.cliente = cursor.getInt(CabPedidos_Schema.CLIENTE_FIELD,null)
            modelo.delegacion = cursor.getShort(CabPedidos_Schema.DELEGACION_FIELD,null)
            modelo.clientefiscal = cursor.getInt(CabPedidos_Schema.CLIENTEFISCAL_FIELD,null)
            modelo.delegacionfiscal = cursor.getShort(CabPedidos_Schema.DELEGACIONFISCAL_FIELD,null)
            modelo.formapago = cursor.getShort(CabPedidos_Schema.FORMAPAGO_FIELD,null)
            modelo.cpreventa = cursor.getDate(CabPedidos_Schema.DPREVENTA_FIELD,null)
            modelo.reparto = cursor.getDate(CabPedidos_Schema.REPARTO_FIELD,null)
            modelo.rutaventa = cursor.getString(CabPedidos_Schema.RUTAVENTA_FIELD,null)
            modelo.secuventa = cursor.getString(CabPedidos_Schema.SECUVENTA_FIELD,null)
            modelo.rutareparto = cursor.getString(CabPedidos_Schema.RUTAREPARTO_FIELD,null)
            modelo.secureparto = cursor.getString(CabPedidos_Schema.SECUREPARTO_FIELD,null)
            modelo.porcdto = cursor.getFloat(CabPedidos_Schema.PORCDTO_FIELD,null)
            modelo.facturable = cursor.getString(CabPedidos_Schema.FACTURABLE_FIELD,null)
            modelo.tipooperacion = cursor.getShort(CabPedidos_Schema.TIPOOPERACION_FIELD,null)
            modelo.seriehoja = cursor.getString(CabPedidos_Schema.SERIEHOJA_FIELD,null)
            modelo.hoja = cursor.getInt(CabPedidos_Schema.HOJA_FIELD,null)
            modelo.sureferencia = cursor.getString(CabPedidos_Schema.SUREFERENCIA_FIELD,null)
            modelo.totalpedido = cursor.getFloat(CabPedidos_Schema.TOTALPEDIDO_FIELD,null)
            modelo.enviado = cursor.getBoolean(CabPedidos_Schema.ENVIADO_FIELD,null)
            modelo.tiporeparto = cursor.getString(CabPedidos_Schema.TIPOREPARTO_FIELD,null)
            modelo.apldtopor = cursor.getBoolean(CabPedidos_Schema.APLDTOPOR_FIELD,null)
            modelo.fabricante = cursor.getShort(CabPedidos_Schema.FABRICANTE_FIELD,null)
            modelo.npreventa = cursor.getInt(CabPedidos_Schema.NPREVENTA_FIELD,null)
            modelo.seriealbaran = cursor.getString(CabPedidos_Schema.SERIEALBARAN_FIELD,null)
            modelo.albaran = cursor.getInt(CabPedidos_Schema.ALBARAN_FIELD,null)
            modelo.fechaalbaran = cursor.getDate(CabPedidos_Schema.FECHAALBARAN_FIELD,null)
            modelo.seriefactura = cursor.getString(CabPedidos_Schema.SERIEFACTURA_FIELD,null)
            modelo.factura = cursor.getInt(CabPedidos_Schema.FACTURA_FIELD,null)
            modelo.fechafactura = cursor.getDate(CabPedidos_Schema.FECHAFACTURA_FIELD,null)
            modelo.notas = cursor.getString(CabPedidos_Schema.NOTAS_FIELD,null)
            modelo.carga = cursor.getShort(CabPedidos_Schema.CARGA_FIELD,null)
            modelo.impreso = cursor.getBoolean(CabPedidos_Schema.IMPRESO_FIELD,null)
            modelo.pendientevalidar = cursor.getString(CabPedidos_Schema.PENDIENTEVALIDAR_FIELD,null)
            modelo.importado = cursor.getBoolean(CabPedidos_Schema.IMPORTADO_FIELD,null)
            modelo.tarifa = cursor.getInt(CabPedidos_Schema.TARIFA_FIELD,null)
            modelo.ejercicio = cursor.getInt(CabPedidos_Schema.EJERCICIO_FIELD,null)
            modelo.ejerhoja = cursor.getInt(CabPedidos_Schema.EJERHOJA_FIELD,null)
            modelo.ejeralbaran = cursor.getInt(CabPedidos_Schema.EJERALBARAN_FIELD,null)
            modelo.ejerfactura = cursor.getInt(CabPedidos_Schema.EJERFACTURA_FIELD,null)
            modelo.situacion = cursor.getInt(CabPedidos_Schema.SITUACION_FIELD,null)
            modelo.repetir = cursor.getDate(CabPedidos_Schema.REPETIR_FIELD,null)
            modelo.dnidestinatario = cursor.getString(CabPedidos_Schema.DNIDESTINATARIO_FIELD,null)
            modelo.nombredestinatario = cursor.getString(CabPedidos_Schema.NOMBREDESTINATARIO_FIELD,null)
            return modelo
        }
    }
}

