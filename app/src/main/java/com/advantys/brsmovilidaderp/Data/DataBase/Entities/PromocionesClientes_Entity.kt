package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.PromocionesClientes_Schema
import com.advantys.brsmovilidaderp.Utils.getBoolean
import com.advantys.brsmovilidaderp.Utils.getDate
import com.advantys.brsmovilidaderp.Utils.getFloat
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString
import java.util.Date

class PromocionesClientes_Entity(
    var cliente: Int?=null,
    var delegacion: Short?=null,
    var promocion: String?=null,
    var fabricante: Short?=null,
    var articulo: String?=null,
    var formato: Int?=null,
    var familia: Short?=null,
    var subfamilia: Short?=null,
    var marca: String?=null,
    var sabor: String?=null,
    var porcDto1: Float?=null,
    var impDto1Uni: Float?=null,
    var tipoDescuento: String?=null,
    var porcDto2: Float?=null,
    var impDto2Uni: Float?=null,
    var pvp: Float?=null,
    var tipoSumatoria: String?=null,
    var base: Short?=null,
    var regalo: Short?=null,
    var arrastre: String?=null,
    var vendidas: Int?=null,
    var regaladas: Int?=null,
    var maxRegaladas: Int?=null,
    var fabricante2: Short?=null,
    var articulo2: String?=null,
    var desde: Date?=null,
    var hasta: Date?=null,
    var enviado: Boolean?=null,
    var tipoProm: String?=null,
    var tarifa: Short?=null,
    var minimo: Float?=null,
    var porcCargo: Float?=null,
    var impCargo: Float?=null,
    var descripcion: String?=null,
    var multiple: String?=null,
    var centro: Short?=null,
    var tipoUnidad: String?=null,
    var obligatoria: Boolean?=null,
    var aplrap: Boolean?=null,
    var estado: String?=null,
    var sugMov: Boolean?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): PromocionesClientes_Entity {
            val modelo=  PromocionesClientes_Entity()
            modelo.cliente = cursor.getInt(PromocionesClientes_Schema.CLIENTE_FIELD,null)
            modelo.delegacion = cursor.getShort(PromocionesClientes_Schema.DELEGACION_FIELD,null)
            modelo.promocion = cursor.getString(PromocionesClientes_Schema.PROMOCION_FIELD,null)
            modelo.fabricante = cursor.getShort(PromocionesClientes_Schema.FABRICANTE_FIELD,null)
            modelo.articulo = cursor.getString(PromocionesClientes_Schema.ARTICULO_FIELD,null)
            modelo.formato = cursor.getInt(PromocionesClientes_Schema.FORMATO_FIELD,null)
            modelo.familia = cursor.getShort(PromocionesClientes_Schema.FAMILIA_FIELD,null)
            modelo.subfamilia = cursor.getShort(PromocionesClientes_Schema.SUBFAMILIA_FIELD,null)
            modelo.marca = cursor.getString(PromocionesClientes_Schema.MARCA_FIELD,null)
            modelo.sabor = cursor.getString(PromocionesClientes_Schema.SABOR_FIELD,null)
            modelo.porcDto1 = cursor.getFloat(PromocionesClientes_Schema.PORCDTO1_FIELD,null)
            modelo.impDto1Uni = cursor.getFloat(PromocionesClientes_Schema.IMPDTO1UNI_FIELD,null)
            modelo.tipoDescuento = cursor.getString(PromocionesClientes_Schema.TIPODESCUENTO_FIELD,null)
            modelo.porcDto2 = cursor.getFloat(PromocionesClientes_Schema.PORCDTO2_FIELD,null)
            modelo.impDto2Uni = cursor.getFloat(PromocionesClientes_Schema.IMPDTO2UNI_FIELD,null)
            modelo.pvp = cursor.getFloat(PromocionesClientes_Schema.PVP_FIELD,null)
            modelo.tipoSumatoria = cursor.getString(PromocionesClientes_Schema.TIPOSUMATORIA_FIELD,null)
            modelo.base = cursor.getShort(PromocionesClientes_Schema.BASE_FIELD,null)
            modelo.regalo = cursor.getShort(PromocionesClientes_Schema.REGALO_FIELD,null)
            modelo.arrastre = cursor.getString(PromocionesClientes_Schema.ARRASTRE_FIELD,null)
            modelo.vendidas = cursor.getInt(PromocionesClientes_Schema.VENDIDAS_FIELD,null)
            modelo.regaladas = cursor.getInt(PromocionesClientes_Schema.REGALADAS_FIELD,null)
            modelo.maxRegaladas = cursor.getInt(PromocionesClientes_Schema.MAXREGALADAS_FIELD,null)
            modelo.fabricante2 = cursor.getShort(PromocionesClientes_Schema.FABRICANTE2_FIELD,null)
            modelo.articulo2 = cursor.getString(PromocionesClientes_Schema.ARTICULO2_FIELD,null)
            modelo.desde = cursor.getDate(PromocionesClientes_Schema.DESDE_FIELD,null)
            modelo.hasta = cursor.getDate(PromocionesClientes_Schema.HASTA_FIELD,null)
            modelo.enviado = cursor.getBoolean(PromocionesClientes_Schema.ENVIADO_FIELD,null)
            modelo.tipoProm = cursor.getString(PromocionesClientes_Schema.TIPOPROM_FIELD,null)
            modelo.tarifa = cursor.getShort(PromocionesClientes_Schema.TARIFA_FIELD,null)
            modelo.minimo = cursor.getFloat(PromocionesClientes_Schema.MINIMO_FIELD,null)
            modelo.porcCargo = cursor.getFloat(PromocionesClientes_Schema.PORCCARGO_FIELD,null)
            modelo.impCargo = cursor.getFloat(PromocionesClientes_Schema.IMPOCARGO_FIELD,null)
            modelo.descripcion = cursor.getString(PromocionesClientes_Schema.DESCRIPCION_FIELD,null)
            modelo.multiple = cursor.getString(PromocionesClientes_Schema.MULTIPLE_FIELD,null)
            modelo.centro = cursor.getShort(PromocionesClientes_Schema.CENTRO_FIELD,null)
            modelo.tipoUnidad = cursor.getString(PromocionesClientes_Schema.TIPOUNIDAD_FIELD,null)
            modelo.obligatoria = cursor.getBoolean(PromocionesClientes_Schema.OBLIGATORIA_FIELD,null)
            modelo.aplrap = cursor.getBoolean(PromocionesClientes_Schema.APLRAP_FIELD,null)
            modelo.estado = cursor.getString(PromocionesClientes_Schema.ESTADO_FIELD,null)
            modelo.sugMov = cursor.getBoolean(PromocionesClientes_Schema.SUGMOV_FIELD,null)
            return modelo
        }
    }
}