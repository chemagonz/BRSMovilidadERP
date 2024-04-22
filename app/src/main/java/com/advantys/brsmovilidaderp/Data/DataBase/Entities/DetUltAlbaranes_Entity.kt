package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.DetUltAlbaranes_Schema
import com.advantys.brsmovilidaderp.Utils.getBoolean
import com.advantys.brsmovilidaderp.Utils.getFloat
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString

class DetUltAlbaranes_Entity (
    var serie: String? = null,
    var albaran: Int? = null,
    var orden: Int? = null,
    var tipoLinea: String? = null,
    var fabricante: Short? = null,
    var articulo: String? = null,
    var concepto: String? = null,
    var cantidad1: Float? = null,
    var cantidad2: Float? = null,
    var unidadesCaja: Float? = null,
    var pvp: Float? = null,
    var porcIva: Float? = null,
    var porcRec: Float? = null,
    var promocion: String? = null,
    var porcDto1: Float? = null,
    var impDto1Uni: Float? = null,
    var impDto1: Float? = null,
    var tipoDescuento: String? = null,
    var porcDto2: Float? = null,
    var impDto2Uni: Float? = null,
    var impDto2: Float? = null,
    var ordenPromo: Int? = null,
    var ordenEnvase: Int? = null,
    var puntoVerde: Float? = null,
    var alcohol: Float? = null,
    var ordenEscandalo: Int? = null,
    var envase: Boolean? = null,
    var ordenPromoMult: Int? = null,
    var tarifa: Int? = null,
    var ejercicio: Int? = null,
    var manipulacion: Float? = null,
    var azucar: Float? = null,
) {
    companion object {
        fun fromCursor(cursor: Cursor): DetUltAlbaranes_Entity {
            val modelo = DetUltAlbaranes_Entity()
            modelo.serie = cursor.getString(DetUltAlbaranes_Schema.SERIE_FIELD,null)
            modelo.albaran = cursor.getInt(DetUltAlbaranes_Schema.ALBARAN_FIELD,null)
            modelo.orden = cursor.getInt(DetUltAlbaranes_Schema.ORDEN_FIELD,null)
            modelo.tipoLinea = cursor.getString(DetUltAlbaranes_Schema.TIPOLINEA_FIELD,null)
            modelo.fabricante = cursor.getShort(DetUltAlbaranes_Schema.FABRICANTE_FIELD,null)
            modelo.articulo = cursor.getString(DetUltAlbaranes_Schema.ARTICULO_FIELD,null)
            modelo.concepto = cursor.getString(DetUltAlbaranes_Schema.CONCEPTO_FIELD,null)
            modelo.cantidad1 = cursor.getFloat(DetUltAlbaranes_Schema.CANTIDAD1_FIELD,null)
            modelo.cantidad2 = cursor.getFloat(DetUltAlbaranes_Schema.CANTIDAD2_FIELD,null)
            modelo.unidadesCaja = cursor.getFloat(DetUltAlbaranes_Schema.UNIDADESCAJA_FIELD,null)
            modelo.pvp = cursor.getFloat(DetUltAlbaranes_Schema.PVP_FIELD,null)
            modelo.porcIva = cursor.getFloat(DetUltAlbaranes_Schema.PORCIVA_FIELD,null)
            modelo.porcRec = cursor.getFloat(DetUltAlbaranes_Schema.PORCREC_FIELD,null)
            modelo.promocion = cursor.getString(DetUltAlbaranes_Schema.PROMOCION_FIELD,null)
            modelo.porcDto1 = cursor.getFloat(DetUltAlbaranes_Schema.PORCDTO1_FIELD,null)
            modelo.impDto1Uni = cursor.getFloat(DetUltAlbaranes_Schema.IMPDTO1UNI_FIELD,null)
            modelo.impDto1 = cursor.getFloat(DetUltAlbaranes_Schema.IMPDTO1_FIELD,null)
            modelo.tipoDescuento = cursor.getString(DetUltAlbaranes_Schema.TIPODESCUENTO_FIELD,null)
            modelo.porcDto2 = cursor.getFloat(DetUltAlbaranes_Schema.PORCDTO2_FIELD,null)
            modelo.impDto2Uni = cursor.getFloat(DetUltAlbaranes_Schema.IMPDTO2UNI_FIELD,null)
            modelo.impDto2 = cursor.getFloat(DetUltAlbaranes_Schema.IMPDTO2_FIELD,null)
            modelo.ordenPromo = cursor.getInt(DetUltAlbaranes_Schema.ORDENPROMO_FIELD,null)
            modelo.ordenEnvase = cursor.getInt(DetUltAlbaranes_Schema.ORDENENVASE_FIELD,null)
            modelo.puntoVerde = cursor.getFloat(DetUltAlbaranes_Schema.PUNTOVERDE_FIELD,null)
            modelo.alcohol = cursor.getFloat(DetUltAlbaranes_Schema.ALCOHOL_FIELD,null)
            modelo.ordenEscandalo = cursor.getInt(DetUltAlbaranes_Schema.ORDENESCANDALLO_FIELD,null)
            modelo.envase = cursor.getBoolean(DetUltAlbaranes_Schema.ENVASE_FIELD,null)
            modelo.ordenPromoMult = cursor.getInt(DetUltAlbaranes_Schema.ORDENPROMOMULT_FIELD,null)
            modelo.tarifa = cursor.getInt(DetUltAlbaranes_Schema.TARIFA_FIELD,null)
            modelo.ejercicio = cursor.getInt(DetUltAlbaranes_Schema.EJERCICIO_FIELD,null)
            modelo.manipulacion = cursor.getFloat(DetUltAlbaranes_Schema.MANIPULACION_FIELD,null)
            modelo.azucar = cursor.getFloat(DetUltAlbaranes_Schema.AZUCAR_FIELD,null)
            return modelo
        }
    }
}