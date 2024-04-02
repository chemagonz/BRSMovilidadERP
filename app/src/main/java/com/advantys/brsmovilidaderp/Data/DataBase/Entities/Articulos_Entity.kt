package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Articulos_Schema
import com.advantys.brsmovilidaderp.Utils.getBoolean
import com.advantys.brsmovilidaderp.Utils.getDouble
import com.advantys.brsmovilidaderp.Utils.getFloat
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString

data class Articulos_Entity(
    var fabricante: Short? = null,
    var articulo: String? = null,
    var articuloAux: String? = null,
    var nombre: String? = null,
    var nombreCorto: String? = null,
    var tipoIVA: Short? = null,
    var precoste: Float? = null,
    var familia: Short? = null,
    var subfamilia: Short? = null,
    var formato: Int? = null,
    var marca: String? = null,
    var sabor: String? = null,
    var fabricanteRet: Short? = null,
    var articuloRet: String? = null,
    var unidadesCaja: Double? = null,
    var puntoVerde: Float? = null,
    var manipulacion: Float? = null,
    var alcohol: Float? = null,
    var cajasBulto: Int? = null,
    var preUltCompra: Float? = null,
    var peso: Double? = null,
    var volumen: Double? = null,
    var suReferencia: String? = null,
    var tipoCarga: String? = null,
    var puntosCarga: Float? = null,
    var porLotes: Boolean? = null,
    var disponible1: Float? = null,
    var disponible2: Float? = null,
    var porLotesSMP: Boolean? = null,
    var udsCaja: Float? = null,
    var desdobleDoc: Short? = null,
    var ventaExCajas: String? = null,
    var tipoArticulo: String? = null,
    var centroArt: String? = null,
    var articuloEst: String? = null,
    var udsFracERP: Int? = null,
    var azucar: Float? = null,
    var pesoAprox: Float? = null,
    var linea: String? = null
) {

    companion object {
        fun fromCursor(cursor: Cursor): Articulos_Entity {
            val modelo = Articulos_Entity()
            modelo.fabricante = cursor.getShort(Articulos_Schema.FABRICANTE_FIELD,null)
            modelo.articulo = cursor.getString(Articulos_Schema.ARTICULO_FIELD,null)
            modelo.articuloAux = cursor.getString(Articulos_Schema.ARTICULO_AUX_FIELD,null)
            modelo.nombre = cursor.getString(Articulos_Schema.NOMBRE_FIELD,null)
            modelo.nombreCorto = cursor.getString(Articulos_Schema.NOMBRE_CORTO_FIELD,null)
            modelo.tipoIVA = cursor.getShort(Articulos_Schema.TIPOIVA_FIELD,null)
            modelo.precoste = cursor.getFloat(Articulos_Schema.PRECOSTE_FIELD,null)
            modelo.familia = cursor.getShort(Articulos_Schema.FAMILIA_FIELD)
            modelo.subfamilia = cursor.getShort(Articulos_Schema.SUBFAMILIA_FIELD)
            modelo.formato = cursor.getInt(Articulos_Schema.FORMATO_FIELD,null)
            modelo.marca = cursor.getString(Articulos_Schema.MARCA_FIELD,null)
            modelo.sabor = cursor.getString(Articulos_Schema.SABOR_FIELD,null)
            modelo.fabricanteRet = cursor.getShort(Articulos_Schema.FABRICANTE_RET_FIELD,null)
            modelo.articuloRet = cursor.getString(Articulos_Schema.ARTICULO_RET_FIELD,null)
            modelo.unidadesCaja = cursor.getDouble(Articulos_Schema.UNIDADES_CAJA_FIELD,null)
            modelo.puntoVerde = cursor.getFloat(Articulos_Schema.PUNTO_VERDE_FIELD,null)
            modelo.manipulacion = cursor.getFloat(Articulos_Schema.MANIPULACION_FIELD,null)
            modelo.alcohol = cursor.getFloat(Articulos_Schema.ALCOHOL_FIELD,null)
            modelo.cajasBulto = cursor.getInt(Articulos_Schema.CAJAS_BULTO_FIELD,null)
            modelo.preUltCompra = cursor.getFloat(Articulos_Schema.PREULT_COMPRA_FIELD,null)
            modelo.peso = cursor.getDouble(Articulos_Schema.PESO_FIELD,null)
            modelo.volumen = cursor.getDouble(Articulos_Schema.VOLUMEN_FIELD,null)
            modelo.suReferencia = cursor.getString(Articulos_Schema.SU_REFERENCIA_FIELD,null)
            modelo.tipoCarga = cursor.getString(Articulos_Schema.TIPO_CARGA_FIELD,null)
            modelo.puntosCarga = cursor.getFloat(Articulos_Schema.PUNTOS_CARGA_FIELD,null)
            modelo.porLotes = cursor.getBoolean(Articulos_Schema.POR_LOTES_FIELD,null)
            modelo.disponible1 = cursor.getFloat(Articulos_Schema.DISPONIBLE1_FIELD,null)
            modelo.disponible2 = cursor.getFloat(Articulos_Schema.DISPONIBLE2_FIELD,null)
            modelo.porLotesSMP = cursor.getBoolean(Articulos_Schema.POR_LOTES_SMP_FIELD,null)
            modelo.udsCaja = cursor.getFloat(Articulos_Schema.UNIDADES_CAJA_FIELD,null)
            modelo.desdobleDoc = cursor.getShort(Articulos_Schema.DESDOBLE_DOC_FIELD,null)
            modelo.ventaExCajas = cursor.getString(Articulos_Schema.VENTA_EX_CAJAS_FIELD,null)
            modelo.tipoArticulo = cursor.getString(Articulos_Schema.TIPO_ARTICULO_FIELD,null)
            modelo.centroArt = cursor.getString(Articulos_Schema.CENTRO_ART_FIELD,null)
            modelo.articuloEst = cursor.getString(Articulos_Schema.ARTICULO_EST_FIELD,null)
            modelo.udsFracERP = cursor.getInt(Articulos_Schema.UDS_FRAC_ERP_FIELD,null)
            modelo.azucar = cursor.getFloat(Articulos_Schema.AZUCAR_FIELD,null)
            modelo.pesoAprox = cursor.getFloat(Articulos_Schema.PESO_APROX_FIELD,null)
            modelo.linea = cursor.getString(Articulos_Schema.LINEA_FIELD,null)
            return modelo
        }
    }
}


