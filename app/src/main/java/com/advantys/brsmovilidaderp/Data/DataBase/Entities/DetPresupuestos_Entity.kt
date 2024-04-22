package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.DetPresupuestos_Schema
import com.advantys.brsmovilidaderp.Utils.getBoolean
import com.advantys.brsmovilidaderp.Utils.getFloat
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString

class DetPresupuestos_Entity(
    var serie: String? = null,
    var presupuesto: Int? = null,
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
    var ordenPromoMult: Short? = null,
    var ejercicio: Int? = null
) {
    companion object {
        fun fromCursor(cursor: Cursor): DetPresupuestos_Entity {
            val modelo = DetPresupuestos_Entity()
            modelo.serie = cursor.getString(DetPresupuestos_Schema.SERIE_FIELD,null)
            modelo.presupuesto = cursor.getInt(DetPresupuestos_Schema.PRESUPUESTO_FIELD,null)
            modelo.orden = cursor.getInt(DetPresupuestos_Schema.ORDEN_FIELD,null)
            modelo.tipoLinea = cursor.getString(DetPresupuestos_Schema.TIPOLINEA_FIELD,null)
            modelo.fabricante = cursor.getShort(DetPresupuestos_Schema.FABRICANTE_FIELD,null)
            modelo.articulo = cursor.getString(DetPresupuestos_Schema.ARTICULO_FIELD,null)
            modelo.concepto = cursor.getString(DetPresupuestos_Schema.CONCEPTO_FIELD,null)
            modelo.cantidad1 = cursor.getFloat(DetPresupuestos_Schema.CANTIDAD1_FIELD,null)
            modelo.cantidad2 = cursor.getFloat(DetPresupuestos_Schema.CANTIDAD2_FIELD,null)
            modelo.unidadesCaja = cursor.getFloat(DetPresupuestos_Schema.UNIDADESCAJA_FIELD,null)
            modelo.pvp = cursor.getFloat(DetPresupuestos_Schema.PVP_FIELD,null)
            modelo.porcIva = cursor.getFloat(DetPresupuestos_Schema.PORCIVA_FIELD,null)
            modelo.porcRec = cursor.getFloat(DetPresupuestos_Schema.PORCREC_FIELD,null)
            modelo.promocion = cursor.getString(DetPresupuestos_Schema.PROMOCION_FIELD,null)
            modelo.porcDto1 = cursor.getFloat(DetPresupuestos_Schema.PORCDTO1_FIELD,null)
            modelo.impDto1Uni = cursor.getFloat(DetPresupuestos_Schema.IMPDTO1UNI_FIELD,null)
            modelo.impDto1 = cursor.getFloat(DetPresupuestos_Schema.IMPDTO1_FIELD,null)
            modelo.tipoDescuento = cursor.getString(DetPresupuestos_Schema.TIPODESCUENTO_FIELD,null)
            modelo.porcDto2 = cursor.getFloat(DetPresupuestos_Schema.PORCDTO2_FIELD,null)
            modelo.impDto2Uni = cursor.getFloat(DetPresupuestos_Schema.IMPDTO2UNI_FIELD,null)
            modelo.impDto2 = cursor.getFloat(DetPresupuestos_Schema.IMPDTO2_FIELD,null)
            modelo.ordenPromo = cursor.getInt(DetPresupuestos_Schema.ORDENPROMO_FIELD,null)
            modelo.ordenEnvase = cursor.getInt(DetPresupuestos_Schema.ORDENENVASE_FIELD,null)
            modelo.puntoVerde = cursor.getFloat(DetPresupuestos_Schema.PUNTOVERDE_FIELD,null)
            modelo.alcohol = cursor.getFloat(DetPresupuestos_Schema.ALCOHOL_FIELD,null)
            modelo.ordenEscandalo = cursor.getInt(DetPresupuestos_Schema.ORDENESCANDALLO_FIELD,null)
            modelo.envase = cursor.getBoolean(DetPresupuestos_Schema.ENVASE_FIELD,null)
            modelo.ordenPromoMult = cursor.getShort(DetPresupuestos_Schema.ORDENPROMOMULT_FIELD,null)
            modelo.ejercicio = cursor.getInt(DetPresupuestos_Schema.EJERCICIO_FIELD,null)
            return modelo
        }
    }
}









