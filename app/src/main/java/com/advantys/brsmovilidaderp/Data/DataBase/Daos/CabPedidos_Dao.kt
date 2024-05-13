package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.CabPedidos_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil
import com.advantys.brsmovilidaderp.Utils.TipoVenta
import javax.inject.Inject


class CabPedidos_Dao @Inject constructor(private val databaseManager: BDUtil) {

  //Comprueba si el pedido existe realmente.

    fun pedidoValido(serie: String, pedido:Int): Boolean {
        val sql = "SELECT ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.SERIE_FIELD}, ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.PEDIDO_FIELD} FROM ${CabPedidos_Schema.TABLE_NAME} WHERE ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.SERIE_FIELD} = '$serie' AND ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.PEDIDO_FIELD} = $pedido "
        return databaseManager.getSelectScalarBoolean(sql)
    }

    fun borrarCabecera(serie:String, pedido:Int): Boolean {
        val sql = " DELETE FROM ${CabPedidos_Schema.TABLE_NAME} WHERE ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.SERIE_FIELD} = '$serie' AND ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.PEDIDO_FIELD} = '$pedido"
        return databaseManager.getSelectScalarBoolean(sql)
    }


//    fun ActualizarPendientes(pedido: CABPEDIDOS, nCobrado: Double) {
//        /*
//    	 * 21/09/15: Actualizamos la variable global.
//    	 */
//        VarGlobales.nCuantiaPendiente = 0
//        /* */
//        var Consulta: String
//        Consulta = "UPDATE CLIENTES SET NPENDIENTE = NPENDIENTE - " + java.lang.String.valueOf(
//            VarGlobales.Redondeo(
//                pedido.getNTOTALPEDIDO() - nCobrado,
//                2
//            )
//        ) + ", DDEUDA = " +
//                "(SELECT min(DFECHA) FROM PENDIENTESCOBRO WHERE NPENDIENTE != 0 AND NCLIENTE = " + java.lang.String.valueOf(
//            pedido.getNCLIENTE()
//        ) +
//                " AND NDELEGACION = " + java.lang.String.valueOf(pedido.getNDELEGACION()) + ") WHERE " +
//                " NCLIENTE = " + java.lang.String.valueOf(pedido.getNCLIENTE()) + " AND NDELEGACION = " + java.lang.String.valueOf(
//            pedido.getNDELEGACION()
//        )
//        var Consulta2: String
//        Consulta2 = "UPDATE PENDIENTESCOBRO SET NPENDIENTE = 0 WHERE"
//        if (pedido.getNALBARAN() !== 0 && pedido.getNALBARAN() !== -1 && pedido.getCSERIEALBARAN() != null) {
//            Consulta2 += (" CSERIEALBARAN = '" + pedido.getCSERIEALBARAN()).toString() + "' AND NALBARAN = " + java.lang.String.valueOf(
//                pedido.getNALBARAN()
//            ) + " AND NEJERALBARAN = " + pedido.getEjerAlbaran()
//        } else if (pedido.getNFACTURA() !== 0 && pedido.getNFACTURA() !== -1 && pedido.getCSERIEFACTURA() != null) {
//            Consulta2 += (" CSERIEFACTURA = '" + pedido.getCSERIEFACTURA()).toString() + "' AND NFACTURA = " + java.lang.String.valueOf(
//                pedido.getNFACTURA()
//            ) + " AND NEJERFACTURA = " + pedido.getEjerFactura()
//        }
//        try {
//            db = ManBD.openDataBase()
//            db.execSQL(Consulta)
//            db.execSQL(Consulta2)
//            var fecha: Date? = Calendar.getInstance().getTime()
//            val hoy: Date = Calendar.getInstance().getTime()
//            var dias: Long = 0
//            Consulta =
//                "SELECT min(DFECHA) FROM PENDIENTESCOBRO WHERE NPENDIENTE != 0 AND NCLIENTE = " + java.lang.String.valueOf(
//                    pedido.getNCLIENTE()
//                ) +
//                        " AND NDELEGACION = " + java.lang.String.valueOf(pedido.getNDELEGACION())
//            var c: Cursor? = null
//            try {
//                c = db.rawQuery(Consulta, null)
//                val curFormater = SimpleDateFormat("yyyy-MM-dd")
//                if (c != null) if (c.moveToFirst()) fecha = curFormater.parse(c.getString(0))
//            } catch (ex: Exception) {
//                fecha = null
//            } finally {
//                if (c != null) c.close()
//            }
//            if (fecha != null) try {
//                dias = getDifference(fecha, hoy, TimeUnit.SECONDS)
//            } catch (e: Exception) {
//                dias = 0
//            }
//            var lActualizadoGlobal = false
//            var i = 0
//            while (i < VarGlobales.ListaClientesR.size() && !lActualizadoGlobal) {
//                if (VarGlobales.ListaClientesR.get(i).getNCliente() === pedido.getNCLIENTE()) {
//                    VarGlobales.ListaClientesR.get(i).setDeuda(
//                        VarGlobales.ListaClientesR.get(i).getDeuda() - VarGlobales.Redondeo(
//                            pedido.getNTOTALPEDIDO() - nCobrado,
//                            2
//                        )
//                    )
//                    VarGlobales.ListaClientesR.get(i).setDiaDeu(dias.toInt().toString())
//                    lActualizadoGlobal = true
//                }
//                i++
//            }
//        } catch (ex: Exception) {
//            ErrorListener.OnErrorBD(ex.toString(), "ActualizarPendienteCobro")
//        } finally {
//            if (db.isOpen()) db.close()
//        }
//    }

//    fun ActualizarCabeceraVenta(Pedido: CABPEDIDOS?, fechapreventa: String?, fechaservicio: String?): Boolean {
//        var Resultado = false
//        val Consulta: String = ConsultaActualizarVenta(Pedido, fechapreventa, fechaservicio)
//        try {
//            db = ManBD.openDataBase()
//            db.execSQL(Consulta)
//            Resultado = true
//            if (VarGlobales.serviciocambiada) VarGlobales.serviciocambiada = false
//            if (VarGlobales.preventacambiada) VarGlobales.preventacambiada = false
//        } catch (ex: Exception) {
//            ErrorListener.OnErrorBD(ex.toString(), "ActualizarCabeceraVenta")
//            Resultado = false
//        } finally {
//            if (db.isOpen()) db.close()
//        }
//        return Resultado
//    }

    fun comprobarExistencia(tipo: TipoVenta, serie:String, siguiente: Int): Int {
        var campo1: String? = null
        var campo2: String? = null
        val existe = false


        when (tipo) {
            TipoVenta.pedido -> {
                campo1 = " ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.SERIE_FIELD}"
                campo2 = " ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.PEDIDO_FIELD}"
            }

            TipoVenta.albaran -> {
                campo1 = " ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.SERIEALBARAN_FIELD}"
                campo2 = " ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.ALBARAN_FIELD}"
            }

            TipoVenta.albaran -> {
                campo1 = " ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.SERIEFACTURA_FIELD}"
                campo2 = " ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.FACTURA_FIELD}"
            }

            else -> ""
        }

        var sql =
            "SELECT * FROM ${CabPedidos_Schema.TABLE_NAME} WHERE  $campo1 = '$serie' AND $campo2 = $siguiente"

        if (existe) {
            sql =
                " SELECT MAX ($campo2) FROM ${CabPedidos_Schema.TABLE_NAME} WHERE $campo1 = '$serie'"
        }

        return databaseManager.getSelectScalarInt(sql)
    }

//    fun insertarCabeceraVenta(cab: CabPedido, fechaPreventa: String, fechaReparto: String): Boolean {
//        val sql = obtenerValores(cab, fechaPreventa, fechaReparto)
//        return databaseManager.getSelectScalarBoolean(sql)
//    }
}