package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.GastosPagos_Schema
import com.advantys.brsmovilidaderp.Utils.getBoolean
import com.advantys.brsmovilidaderp.Utils.getDate
import com.advantys.brsmovilidaderp.Utils.getFloat
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString
import java.util.Date

class GastosPagos_Entity(
    var id: Int?=null,
    var fecha: Date?=null,
    var tipoGasto: String?=null,
    var medioPago: Short??=null,
    var tarjetaPago: String??=null,
    var serie: String?=null,
    var factura: String?=null,
    var proveedor: Int?=null,
    var nifProveedor: String?=null,
    var nombreProveedor: String?=null,
    var base1: Float?=null,
    var porcIva1: Float?=null,
    var porcRec1: Float?=null,
    var cuoIva1: Float?=null,
    var cuoRec1: Float?=null,
    var base2: Float?=null,
    var porcIva2: Float?=null,
    var porcRec2: Float?=null,
    var cuoIva2: Float?=null,
    var cuoRec2: Float?=null,
    var base3: Float?=null,
    var porcIva3: Float?=null,
    var porcRec3: Float?=null,
    var cuoIva3: Float?=null,
    var cuoRec3: Float?=null,
    var base4: Float?=null,
    var porcIva4: Float?=null,
    var porcRec4: Float?=null,
    var cuoIva4: Float?=null,
    var cuoRec4: Float?=null,
    var importe: Float?=null,
    var enviado: Boolean?=null,
    var jornada: Int?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): GastosPagos_Entity {
            val modelo = GastosPagos_Entity()
            modelo.id = cursor.getInt(GastosPagos_Schema.ID_FIELD,null)
            modelo.fecha = cursor.getDate(GastosPagos_Schema.FECHA_FIELD,null)
            modelo.tipoGasto = cursor.getString(GastosPagos_Schema.TIPOGASTO_FIELD,null)
            modelo.medioPago = cursor.getShort(GastosPagos_Schema.MEDIOPAGO_FIELD,null)
            modelo.tarjetaPago = cursor.getString(GastosPagos_Schema.TARJETAPAGO_FIELD,null)
            modelo.serie = cursor.getString(GastosPagos_Schema.SERIE_FIELD,null)
            modelo.factura = cursor.getString(GastosPagos_Schema.FACTURA_FIELD,null)
            modelo.proveedor = cursor.getInt(GastosPagos_Schema.PROVEEDOR_FIELD,null)
            modelo.nifProveedor = cursor.getString(GastosPagos_Schema.NIFPROVEEDOR_FIELD,null)
            modelo.nombreProveedor = cursor.getString(GastosPagos_Schema.NOMBREPROVEEDOR_FIELD,null)
            modelo.base1 = cursor.getFloat(GastosPagos_Schema.BASE1_FIELD,null)
            modelo.porcIva1 = cursor.getFloat(GastosPagos_Schema.PORCIVA1_FIELD,null)
            modelo.porcRec1 = cursor.getFloat(GastosPagos_Schema.PORCREC1_FIELD,null)
            modelo.cuoIva1 = cursor.getFloat(GastosPagos_Schema.CUOIVA1_FIELD,null)
            modelo.cuoRec1 = cursor.getFloat(GastosPagos_Schema.CUOREC1_FIELD,null)
            modelo.base2 = cursor.getFloat(GastosPagos_Schema.BASE2_FIELD,null)
            modelo.porcIva2 = cursor.getFloat(GastosPagos_Schema.PORCIVA2_FIELD,null)
            modelo.porcRec2 = cursor.getFloat(GastosPagos_Schema.PORCREC2_FIELD,null)
            modelo.cuoIva2 = cursor.getFloat(GastosPagos_Schema.CUOIVA2_FIELD,null)
            modelo.cuoRec2 = cursor.getFloat(GastosPagos_Schema.CUOREC2_FIELD,null)
            modelo.base3 = cursor.getFloat(GastosPagos_Schema.BASE3_FIELD,null)
            modelo.porcIva3 = cursor.getFloat(GastosPagos_Schema.PORCIVA3_FIELD,null)
            modelo.porcRec3 = cursor.getFloat(GastosPagos_Schema.PORCREC3_FIELD,null)
            modelo.cuoIva3 = cursor.getFloat(GastosPagos_Schema.CUOIVA3_FIELD,null)
            modelo.cuoRec3 = cursor.getFloat(GastosPagos_Schema.CUOREC3_FIELD,null)
            modelo.base4 = cursor.getFloat(GastosPagos_Schema.BASE4_FIELD,null)
            modelo.porcIva4 = cursor.getFloat(GastosPagos_Schema.PORCIVA4_FIELD,null)
            modelo.porcRec4 = cursor.getFloat(GastosPagos_Schema.PORCREC4_FIELD,null)
            modelo.cuoIva4 = cursor.getFloat(GastosPagos_Schema.CUOIVA4_FIELD,null)
            modelo.cuoRec4 = cursor.getFloat(GastosPagos_Schema.CUOREC4_FIELD,null)
            modelo.importe = cursor.getFloat(GastosPagos_Schema.IMPORTE_FIELD,null)
            modelo.enviado = cursor.getBoolean(GastosPagos_Schema.ENVIADO_FIELD,null)
            modelo.jornada = cursor.getInt(GastosPagos_Schema.JORNADA_FIELD,null)
            return modelo
        }
    }
}