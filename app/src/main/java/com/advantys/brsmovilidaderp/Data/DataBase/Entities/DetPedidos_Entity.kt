package com.advantys.brsmovilidaderp.Data.DataBase.Entities


import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.DetPedidos_Schema
import com.advantys.brsmovilidaderp.Utils.getBoolean
import com.advantys.brsmovilidaderp.Utils.getDate
import com.advantys.brsmovilidaderp.Utils.getFloat
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString
import java.util.Date

data class DetPedidos_Entity (
    var serie: String? = null,
    var pedido: Int? = null,
    var orden: Int? = null,
    var tipolinea: String? = null,
    var fabricante: Short? = null,
    var articulo: String? = null,
    var concepto: String? = null,
    var cantidad1: Float? = null,
    var cantidad2: Float? = null,
    var unidadcaja: Float? = null,
    var pvp: Float? = null,
    var porciva: Float? = null,
    var porcrec: Float? = null,
    var promocion: String? = null,
    var porcdto1: Float? = null,
    var impdto1uni:Float? = null,
    var impdto1: Float? = null,
    var tipodescuento: String? = null,
    var porcdto2: Float? = null,
    var impdto2uni: Float? = null,
    var impdto2: Float? = null,
    var ordenpromo: Int? = null,
    var ordenenvase: Int? = null,
    var puntoverde: Float? = null,
    var alcohol: Float? = null,
    var ordenescandallo: Int? = null,
    var envase: Boolean? = null,
    var desdobledoc: Short? = null,
    var lote: String? = null,
    var caducidad: Date? = null,
    var bloqueo: Boolean? = null,
    var ordenpromomult: Short? = null,
    var observacion: String? = null,
    var motivo: Int? = null,
    var ejercicio: Int? = null,
    var manipulacion: Float? = null,
    var azucar: Float? = null,
    var cantidad1original: Float? = null,
    var cantidad2original: Float? = null
)

{
    companion object {
        fun fromCursor(cursor: Cursor): DetPedidos_Entity {
            val modelo = DetPedidos_Entity()
            modelo.serie = cursor.getString(DetPedidos_Schema.SERIE_FIELD,null)
            modelo.pedido = cursor.getInt(DetPedidos_Schema.PEDIDO_FIELD,null)
            modelo.orden = cursor.getInt(DetPedidos_Schema.ORDEN_FIELD,null)
            modelo.tipolinea = cursor.getString(DetPedidos_Schema.TIPOLINEA_FIELD,null)
            modelo.fabricante = cursor.getShort(DetPedidos_Schema.FABRICANTE_FIELD,null)
            modelo.articulo = cursor.getString(DetPedidos_Schema.ARTICULO_FIELD,null)
            modelo.concepto = cursor.getString(DetPedidos_Schema.CONCEPTO_FIELD,null)
            modelo.cantidad1 = cursor.getFloat(DetPedidos_Schema.CANTIDAD1_FIELD,null)
            modelo.cantidad2 = cursor.getFloat(DetPedidos_Schema.CANTIDAD2_FIELD,null)
            modelo.unidadcaja = cursor.getFloat(DetPedidos_Schema.UNIDADESCAJA_FIELD,null)
            modelo.pvp = cursor.getFloat(DetPedidos_Schema.PVP_FIELD,null)
            modelo.porciva = cursor.getFloat(DetPedidos_Schema.PORCIVA_FIELD,null)
            modelo.porcrec = cursor.getFloat(DetPedidos_Schema.PORCREC_FIELD,null)
            modelo.promocion = cursor.getString(DetPedidos_Schema.PROMOCION_FIELD,null)
            modelo.porcdto1 = cursor.getFloat(DetPedidos_Schema.PORCDTO1_FIELD,null)
            modelo.impdto1uni = cursor.getFloat(DetPedidos_Schema.IMPDTO1UNI_FIELD,null)
            modelo.impdto1 = cursor.getFloat(DetPedidos_Schema.IMPDTO1_FIELD,null)
            modelo.tipodescuento = cursor.getString(DetPedidos_Schema.TIPODESCUENTO_FIELD,null)
            modelo.porcdto2 = cursor.getFloat(DetPedidos_Schema.PORCDTO2_FIELD,null)
            modelo.impdto2uni = cursor.getFloat(DetPedidos_Schema.IMPDTO2UNI_FIELD,null)
            modelo.impdto2 = cursor.getFloat(DetPedidos_Schema.IMPDTO2_FIELD,null)
            modelo.ordenpromo = cursor.getInt(DetPedidos_Schema.ORDENPROMO_FIELD,null)
            modelo.ordenenvase = cursor.getInt(DetPedidos_Schema.ORDENENVASE_FIELD,null)
            modelo.puntoverde = cursor.getFloat(DetPedidos_Schema.PUNTOVERDE_FIELD,null)
            modelo.alcohol = cursor.getFloat(DetPedidos_Schema.ALCOHOL_FIELD,null)
            modelo.ordenescandallo = cursor.getInt(DetPedidos_Schema.ORDENESCANDALLO_FIELD,null)
            modelo.envase = cursor.getBoolean(DetPedidos_Schema.ENVASE_FIELD,null)
            modelo.desdobledoc= cursor.getShort(DetPedidos_Schema.DESDOBLEDOC_FIELD,null)
            modelo.lote = cursor.getString(DetPedidos_Schema.LOTE_FIELD,null)
            modelo.caducidad = cursor.getDate(DetPedidos_Schema.CADUCIDAD_FIELD,null)
            modelo.bloqueo = cursor.getBoolean(DetPedidos_Schema.BLOQUEO_FIELD,null)
            modelo.ordenpromomult = cursor.getShort(DetPedidos_Schema.ORDENPROMOMULT_FIELD,null)
            modelo.observacion = cursor.getString(DetPedidos_Schema.OBSERVACION_FIELD,null)
            modelo.motivo = cursor.getInt(DetPedidos_Schema.MOTIVO_FIELD,null)
            modelo.ejercicio = cursor.getInt(DetPedidos_Schema.EJERCICIO_FIELD,null)
            modelo.manipulacion = cursor.getFloat(DetPedidos_Schema.MANIPULACION_FIELD,null)
            modelo.azucar = cursor.getFloat(DetPedidos_Schema.AZUCAR_FIELD,null)
            modelo.cantidad1original = cursor.getFloat(DetPedidos_Schema.CANTIDAD1ORIGINAL_FIELD,null)
            modelo.cantidad2original = cursor.getFloat(DetPedidos_Schema.CANTIDAD2ORIGINAL_FIELD,null)
            return modelo
        }
    }
}