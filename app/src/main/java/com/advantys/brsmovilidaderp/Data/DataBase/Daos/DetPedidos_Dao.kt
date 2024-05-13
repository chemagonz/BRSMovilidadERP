package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.DetPedidos_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.DetPedidos_Schema
import com.advantys.brsmovilidaderp.Domain.Models.CabPedido
import com.advantys.brsmovilidaderp.Utils.BDUtil
import javax.inject.Inject


class DetPedidos_Dao @Inject constructor(private val databaseManager: BDUtil) {

    fun ObtenerLineasPedido(pedido: CabPedido): List<DetPedidos_Entity?> {

        val sql = "SELECT * FROM ${DetPedidos_Schema.TABLE_NAME} WHERE ${DetPedidos_Schema.TABLE_NAME}.${DetPedidos_Schema.SERIE_FIELD} = '${pedido.serie}' AND ${DetPedidos_Schema.TABLE_NAME}.${DetPedidos_Schema.PEDIDO_FIELD} AND ${DetPedidos_Schema.TABLE_NAME}.${DetPedidos_Schema.TIPOLINEA_FIELD} <> 'D' ORDER BY ${DetPedidos_Schema.TABLE_NAME}.${DetPedidos_Schema.PEDIDO_FIELD} , ${DetPedidos_Schema.TABLE_NAME}.${DetPedidos_Schema.TIPOLINEA_FIELD} "
        return databaseManager.query(sql) { cursor ->
            DetPedidos_Entity.fromCursor(cursor)
        }
    }

    //Borra el pedido
//    fun borraPedido(serie:String, pedido: Int): Boolean {
//        var Resultado = false
//        /*
//    	 * 05/10/15: Modificamos la consulta para eliminar sólamente las líneas del pedido cuyo artículo no esté en la hoja de carga.
//    	 */
//        /*
//    	 * 05/10/15: Modificamos la consulta para eliminar sólamente las líneas del pedido cuyo artículo no esté en la hoja de carga.
//    	 */
//        val ConsultaPreviaHojaCarga =
//            "DELETE FROM DETPEDIDOS WHERE DETPEDIDOS.CARTICULO||DETPEDIDOS.NFABRICANTE NOT IN (SELECT CARTICULO||NFABRICANTE FROM DETHOJAS) AND CSERIE = '$serie".toString() + "' AND NPEDIDO = " + java.lang.String.valueOf(
//                pedido
//            )
//        val ConsultaPostHojaCarga =
//            "DELETE FROM DETPEDIDOS WHERE CSERIE = '$serie".toString() + "' AND NPEDIDO = " + java.lang.String.valueOf(
//                pedido
//            )
//        var lista: List<DETPEDIDOS?>? = null
//
//        try {
//            db = ManBD.openDataBase()
//            db.execSQL(ConsultaPreviaHojaCarga)
//            lista = ObtenerTodasLineasPedido(serie, pedido)
//            if (lista != null) {
//                for (linea in lista) {
//                    if (VarGlobales.ModoVenta.equals("A") && VarGlobales.nTipoDoc === 0) borrarLotes(
//                        linea
//                    )
//                    if (linea.getCTIPOLINEA().equals("V") || linea.getCTIPOLINEA()
//                            .equals("P") || linea.getCTIPOLINEA().equals("D")
//                    ) ActualizaAbonoVenta(linea, "-") else if (linea.getCTIPOLINEA()
//                            .equals("I")
//                    ) ActualizaRotura(linea, "-") else if (linea.getCTIPOLINEA()
//                            .equals("C")
//                    ) ActualizaCambio(linea, "-")
//                }
//                db = ManBD.openDataBase()
//                db.execSQL(ConsultaPostHojaCarga)
//            }
//            Resultado = true
//        } catch (ex: Exception) {
//            ErrorListener.OnErrorBD(ex.toString(), "BorrarPedido")
//            Resultado = false
//        } finally {
//            if (db.isOpen()) db.close()
//        }
//
//        if (Resultado) BorrarCabecera(serie, pedido)
//
//    }

    //Borrar lotes

//    private fun borrarLotes(Linea: DETPEDIDOS) {
//        var flag = false
//        if (!Linea.getCARTICULO().equals("") && Linea.getNFABRICANTE() !== -1) {
//            if (ManejadorArticulos(Contexto).ObtenerArticulo(
//                    Linea.getNFABRICANTE(),
//                    Linea.getCARTICULO()
//                ).getLPORLOTES()
//            ) {
//                val pedido: CABPEDIDOS = obtenerPedido(Linea.getCSERIE(), Linea.getNPEDIDO())
//                var pedirLotes = false
//                val tipoOp: TIPOSOPERACION = ObtenerTiposOperacion(pedido.getNTIPOOPERACION())
//                if (Linea.getCTIPOLINEA().equals("V") && tipoOp.getCACTSTKVEN()
//                        .equals("S")
//                ) pedirLotes = true
//                if (Linea.getCTIPOLINEA().equals("P") && tipoOp.getCACTSTKPRO()
//                        .equals("S")
//                ) pedirLotes = true
//                if (Linea.getCTIPOLINEA().equals("D") && tipoOp.getCACTSTKDEP()
//                        .equals("S")
//                ) pedirLotes = true
//                if (Linea.getCTIPOLINEA().equals("R") && tipoOp.getCACTSTKREG()
//                        .equals("S")
//                ) pedirLotes = true
//                //				if (Linea.getCTIPOLINEA().equals("I") && tipoOp.getCACTSTKINC().equals("S"))
////					pedirLotes = true;
//                if (pedirLotes) {
//                    val manLotes = ManLotes(Contexto)
//                    val historicos: ArrayList<LoteHistorico> = manLotes.obtenerHistoricoLotesAlb(
//                        Linea.getCARTICULO(),
//                        Linea.getNFABRICANTE(),
//                        pedido.getCSERIEALBARAN(),
//                        pedido.getNALBARAN(),
//                        Linea.getNORDEN()
//                    )
//                    if (historicos != null) {
//                        if (historicos.size > 0) {
//                            for (h in historicos) {
//                                val lote = LoteAlmacen(
//                                    h.getArticulo(),
//                                    h.getFabricante(),
//                                    h.getLote(),
//                                    VarGlobales.tipoLote(h.getTipo(), true) * h.getCantidad1(),
//                                    VarGlobales.tipoLote(h.getTipo(), true) * h.getCantidad2(),
//                                    null
//                                )
//                                flag = manLotes.actualizarLote(lote)
//                                if (flag) flag = manLotes.eliminarHistoricoLoteAlb(h)
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }

    //Actualiza la hoja de carga

//    fun actualizaHojaCarga(serie:String, pedido: Int): Boolean {
//
//        var Resultado = false
//
//        var lista: List<DETPEDIDOS?>? = null
//
//        try {
//            db = ManBD.openDataBase()
//
//            //lista = ObtenerTodasLineasPedidoHojaCarga(serie, pedido);
//            lista = ObtenerTodasLineasPedido(serie, pedido)
//            if (lista != null) {
//                for (linea in lista) {
//                    if (linea.getCTIPOLINEA().equals("V") || linea.getCTIPOLINEA()
//                            .equals("P") || linea.getCTIPOLINEA().equals("D")
//                    ) {
//                        if (lEstaEnHojaCarga(
//                                linea.getCARTICULO(),
//                                linea.getNFABRICANTE()
//                            )
//                        ) ActualizaAbonoVenta(linea, "+") else InsertaHojaCarga(linea)
//                    } else if (linea.getCTIPOLINEA().equals("I")) {
//                        if (lEstaEnHojaCarga(
//                                linea.getCARTICULO(),
//                                linea.getNFABRICANTE()
//                            )
//                        ) ActualizaRotura(linea, "+") else InsertaHojaCarga(linea)
//                    } else if (linea.getCTIPOLINEA().equals("C")) {
//                        if (lEstaEnHojaCarga(
//                                linea.getCARTICULO(),
//                                linea.getNFABRICANTE()
//                            )
//                        ) ActualizaCambio(linea, "+") else InsertaHojaCarga(linea)
//                    }
//                }
//                db = ManBD.openDataBase()
//            }
//            Resultado = true
//        } catch (ex: Exception) {
//            ErrorListener.OnErrorBD(ex.toString(), "ActualizaHojaCarga")
//            Resultado = false
//        } finally {
//            if (db.isOpen()) db.close()
//        }
//
//    }

    fun obtenerTodasLineasPedidos(serie:String, pedido:Int): List<DetPedidos_Entity?> {
        val sql = "SELECT * FROM ${DetPedidos_Schema.TABLE_NAME} FROM ${DetPedidos_Schema.TABLE_NAME}.${DetPedidos_Schema.SERIE_FIELD} = '$serie' AND ${DetPedidos_Schema.TABLE_NAME}.${DetPedidos_Schema.PEDIDO_FIELD} = $pedido"
        return databaseManager.query(sql) { cursor ->
            DetPedidos_Entity.fromCursor(cursor)
        }
    }




}