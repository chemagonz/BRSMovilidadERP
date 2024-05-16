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

    fun comprobarExistencia(tipo: TipoVenta, serie:String, siguiente: Int): Int? {
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

//    fun obtenerValores(cab: CabPedido, fechaPreventa: String, fechaReparto: String): String {
//        var aplicaDto = 0
//
//        if(cab.apldtopor != null && cab.apldtopor!!)
//            aplicaDto = 1
//        else
//            aplicaDto = 0
//
//        var fabricante = " NULL"
//
//        if(cab.fabricante?.toInt() != -1) {
//            fabricante = cab.fabricante.toString()
//        } else {
//            fabricante = null.toString()
//        }
//
//        if(fechaPreventa.contains(" 24: ")) {
//            fechaPreventa = fechaPreventa.replace(" 24:", " 00:")
//        }
//
//        if(fechaReparto.contains(" 24: ")) {
//            fechaReparto = fechaReparto.replace(" 24:", " 00:")
//        }
//
//        var sql = " INSERT INTO ${CabPedidos_Schema.TABLE_NAME} (${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.SERIE_FIELD}, ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.PEDIDO_FIELD}, " +
//                "${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.CENTRO_FIELD}, ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.CLIENTE_FIELD}, ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.DELEGACION_FIELD}, ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.CLIENTEFISCAL_FIELD}, " +
//                "${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.DELEGACIONFISCAL_FIELD}, ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.FORMAPAGO_FIELD}, ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.DPREVENTA_FIELD}, ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.REPARTO_FIELD}, " +
//                "${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.RUTAVENTA_FIELD}, ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.SECUVENTA_FIELD}, ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.RUTAREPARTO_FIELD}, ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.SECUREPARTO_FIELD}, ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.PORCDTO_FIELD}, " +
//                "${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.FACTURABLE_FIELD}, ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.TIPOOPERACION_FIELD}, ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.SERIEHOJA_FIELD}, ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.HOJA_FIELD}, ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.SUREFERENCIA_FIELD},  " +
//                "${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.NOTAS_FIELD}, ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.CARGA_FIELD}, ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.APLDTOPOR_FIELD}, ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.FABRICANTE_FIELD} , ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.TOTALPEDIDO_FIELD}, ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.TIPOREPARTO_FIELD}, " +
//                "${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.SERIEALBARAN_FIELD}, ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.ALBARAN_FIELD}, ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.FECHAALBARAN_FIELD}, ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.SERIEFACTURA_FIELD}, ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.FACTURA_FIELD}, ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.FECHAFACTURA_FIELD}, ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.TARIFA_FIELD}, ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.EJERCICIO_FIELD}, ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.EJERALBARAN_FIELD}, ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.EJERFACTURA_FIELD}"
//
//        if(riesgoSuperadoContinuar) {
//            sql += " ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.PENDIENTEVALIDAR_FIELD} )"
//        } else {
//            sql += " )"
//        }
//
//        sql += " VALUES (${cab.serie} , ${cab.pedido}, ${cab.centro}, ${cab.cliente}, ${cab.delegacion}, ${cab.clientefiscal}, ${cab.delegacionfiscal}, ${cab.formapago}, $fechaPreventa, $fechaReparto, ${cab.rutaventa}, ${cab.secuventa}, ${cab.rutareparto}, ${cab.secureparto}, ${cab.porcdto}, ${cab.facturable}, ${cab.tipooperacion} , "
//
//        if(cab.seriehoja != null) {
//            sql += " ${cab.seriehoja}"
//        } else {
//            sql += " null,"
//        }
//
//        if(cab.ejerhoja!! > 0) {
//            sql += " ${cab.ejerhoja} , "
//        } else {
//            sql += "null "
//        }
//
//        if(cab.sureferencia != null) {
//
//            if(cab.sureferencia!!.contains("\'")) {
//                cab.sureferencia!!.replace("\'", "?")
//            }
//
//            sql += "${cab.sureferencia} , "
//        } else {
//            sql += "null, "
//        }
//
//        if(cab.notas != null) {
//
//            if(cab.notas!!.contains("\'")) {
//                cab.notas!!.replace("\'", "?")
//            }
//
//            sql += "${cab.notas} , "
//        } else {
//            sql += "null, "
//        }
//
//        if(cab.carga?.toInt() != -1)
//        {
//            sql += " ${cab.carga} , ";
//        }
//        else
//        {
//            sql += "1, ";
//        }
//
//        if(fabricante == null) {
//            sql +=	 " $aplicaDto , null , ${cab.totalpedido}"
//        }  else {
//            sql +=
//        }
//        {
//            Consulta += String.valueOf(aplicaDto) + ", " +
//                    fabricante + ", " +
//                    String.valueOf(cab.getNTOTALPEDIDO());
//        }
//        Consulta += ", '" + cab.getCTIPOREPARTO()+"', ";
//
//        if(cab.getCSERIEALBARAN() != null)
//        {
//            Consulta += "'"+ cab.getCSERIEALBARAN() +"', ";
//        }
//        else
//        {
//            Consulta += " null , ";
//        }
//        if(cab.getNALBARAN() != -1)
//        {
//            Consulta += cab.getNALBARAN() + ", ";
//        }
//        else
//        {
//            Consulta += " null , ";
//        }
//        if(cab.getDFECHAALBARAN() != null)
//        {
//            //Consulta += "'" + VarGlobales.formatofechaconhora2(Date.parse(cab.getDFECHAALBARAN()),true) + "', ";
//            //String fechaalb = cab.getDFECHAALBARAN().getYear() + "-" + cab.getDFECHAALBARAN().getMonth() + "-" + cab.getDFECHAALBARAN().getDate() + " " + cab.getDFECHAALBARAN().getHours() + ":" + cab.getDFECHAALBARAN().getMinutes();
//            Consulta += "'" + VarGlobales.formatofechaconhora2(cab.getDFECHAALBARAN(), false) + "', ";
//        }
//        else
//        {
//            Consulta += " null , ";
//        }
//        if(cab.getCSERIEFACTURA() != null)
//        {
//            Consulta += "'" + cab.getCSERIEFACTURA() +"', ";
//        }
//        else
//        {
//            Consulta += " null , ";
//        }
//        if(cab.getNFACTURA() != -1)
//        {
//            Consulta += cab.getNFACTURA() +", ";
//        }
//        else
//        {
//            Consulta += ", null , ";
//        }
//        if(cab.getDFECHAFACTURA() != null)
//        {
//            Consulta += " '"+ VarGlobales.formatofechaconhora2(cab.getDFECHAFACTURA(), false)+"' ";
//        }
//        else
//        {
//            Consulta += " null ";
//        }
//
//        if(cab.getTarifa() != -1)
//        {
//            Consulta += ", "+cab.getTarifa();
//        }
//        else
//        {
//            Consulta += ", null";
//        }
//
//        if(cab.getEjercicio() != -1)
//        {
//            Consulta += ", "+cab.getEjercicio();
//        }
//        else
//        {
//            Consulta += ", null";
//        }
//
//        if(cab.getEjerAlbaran() != -1)
//        {
//            Consulta += ", "+cab.getEjerAlbaran();
//        }
//        else
//        {
//            Consulta += ", null";
//        }
//
//        if(cab.getEjerFactura() != -1)
//        {
//            Consulta += ", "+cab.getEjerFactura();
//        }
//        else
//        {
//            Consulta += ", null";
//        }
//
//        if(VarGlobales.RiesgoSuperadoContinuar)
//        {
//            Consulta += ", 'S' )";
//        }
//        else
//        {
//            Consulta += " )";
//        }
//        VarGlobales.RiesgoSuperadoContinuar = false;
//    }
}