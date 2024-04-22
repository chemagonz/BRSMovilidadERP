package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Promociones_Schema
import com.advantys.brsmovilidaderp.Utils.getBoolean
import com.advantys.brsmovilidaderp.Utils.getDate
import com.advantys.brsmovilidaderp.Utils.getFloat
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString
import java.util.Date

class Promociones_Entity  (
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
    var fabricante2: Short?=null,
    var articulo2: String?=null,
    var desde: Date?=null,
    var hasta: Date?=null,
    var tipoProm: String?=null,
    var sector: Short?=null,
    var actividad: Short?=null,
    var tarifa: Short?=null,
    var minimo: Float?=null,
    var descripcion: String?=null,
    var multiple: String?=null,
    var centro: Short?=null,
    var grupo: String?=null,
    var tipoUnidad: String?=null,
    var obligatoria: Boolean?=null,
    var aplrap: Boolean?=null,
    var estado: String?=null,
    var sugMov: Boolean?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): Promociones_Entity {
            val modelo=  Promociones_Entity()
            modelo.promocion = cursor.getString(Promociones_Schema.PROMOCION_FIELD,null)
            modelo.fabricante = cursor.getShort(Promociones_Schema.FABRICANTE_FIELD,null)
            modelo.articulo = cursor.getString(Promociones_Schema.ARTICULO_FIELD,null)
            modelo.formato = cursor.getInt(Promociones_Schema.FORMATO_FIELD,null)
            modelo.familia = cursor.getShort(Promociones_Schema.FAMILIA_FIELD,null)
            modelo.subfamilia = cursor.getShort(Promociones_Schema.SUBFAMILIA_FIELD,null)
            modelo.marca = cursor.getString(Promociones_Schema.MARCA_FIELD,null)
            modelo.sabor = cursor.getString(Promociones_Schema.SABOR_FIELD,null)
            modelo.porcDto1 = cursor.getFloat(Promociones_Schema.PORCDTO1_FIELD,null)
            modelo.impDto1Uni = cursor.getFloat(Promociones_Schema.IMPDTO1UNI_FIELD,null)
            modelo.tipoDescuento = cursor.getString(Promociones_Schema.TIPODESCUENTO_FIELD,null)
            modelo.porcDto2 = cursor.getFloat(Promociones_Schema.PORCDTO2_FIELD,null)
            modelo.impDto2Uni = cursor.getFloat(Promociones_Schema.IMPDTO2UNI_FIELD,null)
            modelo.pvp = cursor.getFloat(Promociones_Schema.PVP_FIELD,null)
            modelo.tipoSumatoria = cursor.getString(Promociones_Schema.TIPOSUMATORIA_FIELD,null)
            modelo.base = cursor.getShort(Promociones_Schema.BASE_FIELD,null)
            modelo.regalo = cursor.getShort(Promociones_Schema.REGALO_FIELD,null)
            modelo.fabricante2 = cursor.getShort(Promociones_Schema.FABRICANTE2_FIELD,null)
            modelo.articulo2 = cursor.getString(Promociones_Schema.ARTICULO2_FIELD,null)
            modelo.desde = cursor.getDate(Promociones_Schema.DESDE_FIELD,null)
            modelo.hasta = cursor.getDate(Promociones_Schema.HASTA_FIELD,null)
            modelo.tipoProm = cursor.getString(Promociones_Schema.TIPOPROM_FIELD,null)
            modelo.sector = cursor.getShort(Promociones_Schema.SECTOR_FIELD,null)
            modelo.actividad = cursor.getShort(Promociones_Schema.ACTIVIDAD_FIELD,null)
            modelo.tarifa = cursor.getShort(Promociones_Schema.TARIFA_FIELD,null)
            modelo.minimo = cursor.getFloat(Promociones_Schema.MINIMO_FIELD,null)
            modelo.descripcion = cursor.getString(Promociones_Schema.DESCRIPCION_FIELD,null)
            modelo.multiple = cursor.getString(Promociones_Schema.MULTIPLE_FIELD,null)
            modelo.centro = cursor.getShort(Promociones_Schema.CENTRO_FIELD,null)
            modelo.grupo = cursor.getString(Promociones_Schema.GRUPO_FIELD,null)
            modelo.tipoUnidad = cursor.getString(Promociones_Schema.TIPOUNIDAD_FIELD,null)
            modelo.obligatoria = cursor.getBoolean(Promociones_Schema.OBLIGATORIA_FIELD,null)
            modelo.aplrap = cursor.getBoolean(Promociones_Schema.APLRAP_FIELD,null)
            modelo.estado = cursor.getString(Promociones_Schema.ESTADO_FIELD,null)
            modelo.sugMov = cursor.getBoolean(Promociones_Schema.SUGMOV_FIELD,null)
            return modelo

        }
    }
}