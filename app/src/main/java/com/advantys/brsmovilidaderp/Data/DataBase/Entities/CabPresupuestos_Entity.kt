package com.advantys.brsmovilidaderp.Data.DataBase.Entities
import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.CabPresupuestos_Schema
import com.advantys.brsmovilidaderp.Utils.getBoolean
import com.advantys.brsmovilidaderp.Utils.getDate
import com.advantys.brsmovilidaderp.Utils.getFloat
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString
import java.util.Date

class CabPresupuestos_Entity(
    var serie: String? = null,
    var presupuesto: Int? = null,
    var centro: Short? = null,
    var cliente: Int? = null,
    var delegacion: Short? = null,
    var clienteFiscal: Int? = null,
    var delegacionFiscal: Short? = null,
    var formaPago: Short? = null,
    var fecha: Date? = null,
    var rutaVenta: String? = null,
    var secuVenta: String? = null,
    var rutaReparto: String? = null,
    var secuReparto: String? = null,
    var porcDto: Float? = null,
    var facturable: String? = null,
    var tipoOperacion: Short? = null,
    var totalPresupuesto: Float? = null,
    var enviado: Boolean? = null,
    var tipoReparto: String? = null,
    var aplDtoPor: Boolean? = null,
    var fabricante: Short? = null,
    var preventa: Int? = null,
    var seriePedido: String? = null,
    var pedido: Int? = null,
    var fechaPedido: Date? = null,
    var notas: String? = null,
    var impreso: Boolean? = null,
    var importado: Boolean? = null,
    var ejercicio: Int? = null,
    var ejerPedido: Int? = null
) {
    companion object {
        fun fromCursor(cursor: Cursor): CabPresupuestos_Entity {
            val modelo = CabPresupuestos_Entity()
            modelo.serie = cursor.getString(CabPresupuestos_Schema.SERIE_FIELD,null)
            modelo.presupuesto = cursor.getInt(CabPresupuestos_Schema.PRESUPUESTO_FIELD,null)
            modelo.centro = cursor.getShort(CabPresupuestos_Schema.CENTRO_FIELD,null)
            modelo.cliente = cursor.getInt(CabPresupuestos_Schema.CLIENTE_FIELD,null)
            modelo.delegacion = cursor.getShort(CabPresupuestos_Schema.DELEGACION_FIELD,null)
            modelo.clienteFiscal = cursor.getInt(CabPresupuestos_Schema.CLIENTEFISCAL_FIELD,null)
            modelo.delegacionFiscal = cursor.getShort(CabPresupuestos_Schema.DELEGACIONFISCAL_FIELD,null)
            modelo.formaPago = cursor.getShort(CabPresupuestos_Schema.FORMAPAGO_FIELD,null)
            modelo.fecha = cursor.getDate(CabPresupuestos_Schema.FECHA_FIELD,null)
            modelo.rutaVenta = cursor.getString(CabPresupuestos_Schema.RUTAVENTA_FIELD,null)
            modelo.secuVenta = cursor.getString(CabPresupuestos_Schema.SECUVENTA_FIELD,null)
            modelo.rutaReparto = cursor.getString(CabPresupuestos_Schema.RUTAREPARTO_FIELD,null)
            modelo.secuReparto = cursor.getString(CabPresupuestos_Schema.SECUREPARTO_FIELD,null)
            modelo.porcDto = cursor.getFloat(CabPresupuestos_Schema.PORCDTO_FIELD,null)
            modelo.facturable = cursor.getString(CabPresupuestos_Schema.FACTURABLE_FIELD,null)
            modelo.tipoOperacion = cursor.getShort(CabPresupuestos_Schema.TIPOOPERACION_FIELD,null)
            modelo.totalPresupuesto = cursor.getFloat(CabPresupuestos_Schema.TOTALPRESUPUESTO_FIELD,null)
            modelo.enviado = cursor.getBoolean(CabPresupuestos_Schema.ENVIADO_FIELD,null)
            modelo.tipoReparto = cursor.getString(CabPresupuestos_Schema.TIPOREPARTO_FIELD,null)
            modelo.aplDtoPor = cursor.getBoolean(CabPresupuestos_Schema.APLDTOPOR_FIELD,null)
            modelo.fabricante = cursor.getShort(CabPresupuestos_Schema.FABRICANTE_FIELD,null)
            modelo.preventa = cursor.getInt(CabPresupuestos_Schema.PREVENTA_FIELD,null)
            modelo.seriePedido = cursor.getString(CabPresupuestos_Schema.SERIEPEDIDO_FIELD,null)
            modelo.pedido = cursor.getInt(CabPresupuestos_Schema.PEDIDO_FIELD,null)
            modelo.fechaPedido = cursor.getDate(CabPresupuestos_Schema.FECHAPEDIDO_FIELD,null)
            modelo.notas = cursor.getString(CabPresupuestos_Schema.NOTAS_FIELD,null)
            modelo.impreso = cursor.getBoolean(CabPresupuestos_Schema.IMPRESO_FIELD,null)
            modelo.importado = cursor.getBoolean(CabPresupuestos_Schema.IMPORTADO_FIELD,null)
            modelo.ejercicio = cursor.getInt(CabPresupuestos_Schema.EJERCICIO_FIELD,null)
            modelo.ejerPedido = cursor.getInt(CabPresupuestos_Schema.EJERPEDIDO_FIELD,null)
            return modelo
        }
    }
}