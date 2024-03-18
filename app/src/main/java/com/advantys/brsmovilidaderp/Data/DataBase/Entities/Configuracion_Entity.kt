package com.advantys.brsmovilidaderp.Data.DataBase.Entities


import android.database.Cursor
import android.os.Build
import androidx.annotation.RequiresApi
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Configuracion_Schema
import com.advantys.brsmovilidaderp.Utils.getBoolean
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getLocalDateTime
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString


import java.time.LocalDateTime

data class Configuracion_Entity(
    var nTerminal: Short? = null,
    var cNombre: String? = null,
    var cModo: String? = null,
    var cRutaCarga: String? = null,
    var cRutaDescarga: String? = null,
    var lModificarFormaPago: Boolean? = null,
    var nModificarPvpDtos: Short ?= null,
    var dPreventa: LocalDateTime? = null,
    var dServicio: LocalDateTime? = null,
    var lDesDoble: Int ?= null,
    var lMinimo: Int ?= null,
    var nImpMinimo: Int ?= null,
    var cServidorFtp: String? = null,
    var cUsuarioFtp: String? = null,
    var cPasswordFtp: String? = null,
    var cCarpetaFtp: String? = null,
    var lModificarConcepto: Int ?= null,
    var cRsgblqVta: String? = null,
    var lBusArt: Int ?= null,
    var lConsum: Int ?= null,
    var nCambiarDto1: Short ?= null,
    var nCambiarDto2: Short? = null,
    var nTipLinPro: Short? = null,
    var nTipLinReg: Short? = null,
    var lGenFicInc: Int? = null,
    var lVtasInstk: Int ?= null,
    var lAlbaranAr: Int? = null,
    var lFacturar: Int? = null,
    var cNombrePuerto: String ?= null,
    var nNumeroPuerto: Short? = null,
    var nLongPagina: Short ?= null,
    var nAjustePag: Short? = null,
    var nMargenSup: Short ?= null,
    var nMargenIzda: Short? = null,
    var nMargenInf: Short? = null,
    var lPromocion: Boolean ?= null,
    var lVerCoste: Boolean ?= null,
    var lBorrarCobro: Boolean? = null,
    var lImpCentro: Boolean? = null,
    var nMaximoRegistro: Short? = null,
    var cPatronBusqueda: String? = null,
    var lExportPte: Boolean? = null,
    var lExportCob: Boolean? = null,
    var nRetardoImp: Short ?= null,
    var lReqCfgImp: Boolean? = null,
    var cSalidaLotes: String? = null,
    var lVentaPrevia: Boolean? = null,
    var lCantidad: Boolean ?= null,
    var cWebService: String? = null,
    var dUltimaAct: LocalDateTime? = null,
    var cCarpetaCopia: String? = null,
    var nModeloVenta: Short ?= null,
    var nAplicacion: Short ?= null,
    var nBorrarFactura: Short? = null,
    var cVentaNegEnv: String? = null,
    var lResumenEnv: Boolean? = null,
    var cBloquearFab: String? = null,
    var cPermDeposito: String ?= null,
    var cPermIncidencias: String? = null,
    var cPermCambios: String ?= null,
    var cPermFacturable: String ?= null,
    var cServidorIntFtp: String? = null,
    var cVerBeneficio: String? = null,
    var cForzarBas: String? = null,
    var nBorrarAlbaran: Boolean ?= null,
    var lLiqImpresa: Boolean? = null,
    var lMostrarEnvase: Boolean? = null,
    var cNotaVendedor: String? = null,
    var nHojaCarga: Int? = null,
    var cSerieHoja: String? = null,
    var lModRSocial: Boolean ?= null,
    var cAplicIva: String? = null,
    var cVerSyncAnd: String? = null,
    var cAltaCli: String ?= null,
    var cAltaPro: String? = null,
    var dInicioCierre: LocalDateTime? = null,
    var dFinCierre: LocalDateTime? = null,
    var nAgente: Int? = null,
    var cNombreAgente: String? = null,
    var cPin: String? = null,
    var cVehiculo: String? = null,
    var nPuertoFtp: Int? = null,
    var cBlkDocImp: String ?= null,
    var cSmtp: String? = null,
    var cPuertoCorreo: String? = null,
    var cCuenta: String? = null,
    var cSsl: String? = null,
    var cContra: String? = null,
    var nEjercicio: Int ?= null,
    var nHistConsultivo: Int? = null,
    var nModoAutoVenta: Boolean? = null,
    var lCobrarSoloEfectivo: Boolean ?= null,
    var lNoVenderSinExistencias: Boolean? = null,
    var lBloquearLineasManuales: Boolean? = null,
    var lNoDecimales: Boolean? = null,
    var lMostrarDireccionRutero: Boolean ?= null,
    var lMostrarUvuc: Boolean ?= null,
    var lMostrarExportar: Boolean ?= null,
    var lMostrarJornada: Boolean? = null,
    var lVenderUnTipoUnidad: Boolean? = null,
    var lNoGrabarVisitaSalirVenta: Boolean? = null,
    var nModeloInforme: Int? = null,
    var nRecogerEnvases: Int? = null,
    var nDiasBorradoAut: Int? = null,
    var nAnadirSoloEnvases: Int? = null,
    var nMostrarPendientesCobroAntesVenta: Int? = null
) {
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun fromCursor(cursor: Cursor): Configuracion_Entity {
            val modelo = Configuracion_Entity()
            modelo.nTerminal = cursor.getShort(Configuracion_Schema.TERMINAL_FIELD,null)
            modelo.cNombre = cursor.getString(Configuracion_Schema.NOMBRE_FIELD,null)
            modelo.cModo = cursor.getString(Configuracion_Schema.MODO_FIELD,null)
            modelo.cRutaCarga = cursor.getString(Configuracion_Schema.RUTA_CARGA_FIELD,null)
            modelo.cRutaDescarga = cursor.getString(Configuracion_Schema.RUTA_DESCARGA_FIELD,null)
            modelo.lModificarFormaPago = cursor.getBoolean(Configuracion_Schema.MODIFICAR_FORMA_PAGO_FIELD, null)
            modelo.nModificarPvpDtos = cursor.getShort(Configuracion_Schema.MODIFICAR_PVP_DESCUENTOS_FIELD,null)
            modelo.dPreventa = cursor.getLocalDateTime(Configuracion_Schema.PREVENTA_FIELD, null)
            modelo.dServicio = cursor.getLocalDateTime(Configuracion_Schema.SERVICIO_FIELD, null)
            modelo.lDesDoble = cursor.getInt(Configuracion_Schema.DESDOBLES_FIELD,null)
            modelo.lMinimo = cursor.getInt(Configuracion_Schema.MINIMO_FIELD,null)
            modelo.nImpMinimo = cursor.getInt(Configuracion_Schema.IMP_MINIMO_FIELD,null)
            modelo.cServidorFtp = cursor.getString(Configuracion_Schema.SERVIDOR_FTP_FIELD,null)
            modelo.cUsuarioFtp = cursor.getString(Configuracion_Schema.USUARIO_FTP_FIELD,null)
            modelo.cPasswordFtp = cursor.getString(Configuracion_Schema.CONTRASENA_FTP_FIELD,null)
            modelo.cCarpetaFtp = cursor.getString(Configuracion_Schema.CARPETA_FTP_FIELD,null)
            modelo.lModificarConcepto = cursor.getInt(Configuracion_Schema.MODIFICAR_CONCEPTO_FIELD,null)
            modelo.cRsgblqVta = cursor.getString(Configuracion_Schema.RSG_BLQ_VTA_FIELD,null)
            modelo.lBusArt = cursor.getInt(Configuracion_Schema.BUS_ART_FIELD, null)
            modelo.lConsum = cursor.getInt(Configuracion_Schema.CONSUM_FIELD,null)
            modelo.nCambiarDto1 = cursor.getShort(Configuracion_Schema.CAMBIAR_DTO1_FIELD)
            modelo.nCambiarDto2 = cursor.getShort(Configuracion_Schema.CAMBIAR_DTO2_FIELD,null)
            modelo.nTipLinPro = cursor.getShort(Configuracion_Schema.TIPO_LIN_PRO_FIELD,null)
            modelo.nTipLinReg = cursor.getShort(Configuracion_Schema.TIPO_LIN_REG_FIELD,null)
            modelo.lGenFicInc = cursor.getInt(Configuracion_Schema.GEN_FIC_INC_FIELD,null)
            modelo.lVtasInstk = cursor.getInt(Configuracion_Schema.VTAS_INSTK_FIELD, null)
            modelo.lAlbaranAr = cursor.getInt(Configuracion_Schema.ALBARAN_AR_FIELD,null)
            modelo.lFacturar = cursor.getInt(Configuracion_Schema.FACTURAR_FIELD,null)
            modelo.cNombrePuerto = cursor.getString(Configuracion_Schema.NOMBRE_PUERTO_FIELD,null)
            modelo.nNumeroPuerto = cursor.getShort(Configuracion_Schema.NUMERO_PUERTO_FIELD,null)
            modelo.nLongPagina = cursor.getShort(Configuracion_Schema.LONG_PAGINA_FIELD,null)
            modelo.nAjustePag = cursor.getShort(Configuracion_Schema.AJUSTE_PAG_FIELD,null)
            modelo.nMargenSup = cursor.getShort(Configuracion_Schema.MARGEN_SUP_FIELD,null)
            modelo.nMargenIzda =cursor.getShort(Configuracion_Schema.MARGEN_IZQ_FIELD,null)
            modelo.nMargenInf = cursor.getShort(Configuracion_Schema.MARGEN_INF_FIELD,null)
            modelo.lPromocion = cursor.getBoolean(Configuracion_Schema.PROMOCION_FIELD, null)
            modelo.lVerCoste = cursor.getBoolean(Configuracion_Schema.VER_COSTE_FIELD, null)
            modelo.lBorrarCobro = cursor.getBoolean(Configuracion_Schema.BORRAR_COBRO_FIELD, null)
            modelo.lImpCentro = cursor.getBoolean(Configuracion_Schema.LIMPIAR_CENTRO_FIELD, null)
            modelo.nMaximoRegistro = cursor.getShort(Configuracion_Schema.MAX_REGISTRO_FIELD, null)
            modelo.cPatronBusqueda = cursor.getString(Configuracion_Schema.PATRON_BUSQUEDA_FIELD)
            modelo.lExportPte = cursor.getBoolean(Configuracion_Schema.EXPORT_PTE_FIELD, null)
            modelo.lExportCob =cursor.getBoolean(Configuracion_Schema.EXPORT_COB_FIELD, null)
            modelo.nRetardoImp = cursor.getShort(Configuracion_Schema.RETARDO_IMP_FIELD, null)
            modelo.lReqCfgImp = cursor.getBoolean(Configuracion_Schema.REQ_CFG_IMP_FIELD, null)
            modelo.cSalidaLotes =cursor.getString(Configuracion_Schema.SALIDA_LOTES_FIELD, null)
            modelo.lVentaPrevia = cursor.getBoolean(Configuracion_Schema.VENTA_PREVIA_FIELD, null)
            modelo.lCantidad = cursor.getBoolean(Configuracion_Schema.CANTIDAD_FIELD, null)
            modelo.cWebService = cursor.getString(Configuracion_Schema.WEB_SERVICE_FIELD, null)
            modelo.dUltimaAct = cursor.getLocalDateTime(Configuracion_Schema.ULTIMA_ACT_FIELD, null)
            modelo.cCarpetaCopia =cursor.getString(Configuracion_Schema.CARPETA_COPIA_FIELD, null)
            modelo.nModeloVenta = cursor.getShort(Configuracion_Schema.MODELO_VENTA_FIELD, null)
            modelo.nAplicacion =cursor.getShort(Configuracion_Schema.APLICACION_FIELD, null)
            modelo.nBorrarFactura = cursor.getShort(Configuracion_Schema.BORRAR_FACTURA_FIELD, null)
            modelo.cVentaNegEnv = cursor.getString(Configuracion_Schema.VENTA_NEG_ENV_FIELD, null)
            modelo.lResumenEnv = cursor.getBoolean(Configuracion_Schema.RESUMEN_ENV_FIELD, null)
            modelo.cBloquearFab = cursor.getString(Configuracion_Schema.BLOQ_FAB_FIELD, null)
            modelo.cPermDeposito = cursor.getString(Configuracion_Schema.PERM_DEPOSITO_FIELD, null)
            modelo.cPermIncidencias = cursor.getString(Configuracion_Schema.PERM_INCIDENCIAS_FIELD, null)
            modelo.cPermCambios = cursor.getString(Configuracion_Schema.PERM_CAMBIOS_FIELD, null)
            modelo.cPermFacturable = cursor.getString(Configuracion_Schema.PERM_FACTURABLE_FIELD, null)
            modelo.cServidorIntFtp = cursor.getString(Configuracion_Schema.SERVIDOR_INT_FTP_FIELD, null)
            modelo.cVerBeneficio = cursor.getString(Configuracion_Schema.VER_BENEFICIO_FIELD, null)
            modelo.cForzarBas = cursor.getString(Configuracion_Schema.FORZAR_BAS_FIELD, null)
            modelo.nBorrarAlbaran = cursor.getBoolean(Configuracion_Schema.BORRAR_ALBARAN_FIELD, null)
            modelo.lLiqImpresa = cursor.getBoolean(Configuracion_Schema.LIQ_IMPRESA_FIELD, null)
            modelo.lMostrarEnvase = cursor.getBoolean(Configuracion_Schema.MOSTRAR_ENVASE_FIELD, null)
            modelo.cNotaVendedor = cursor.getString(Configuracion_Schema.NOTA_VENDEDOR_FIELD, null)
            modelo.nHojaCarga =cursor.getInt(Configuracion_Schema.HOJA_CARGA_FIELD, null)
            modelo.cSerieHoja = cursor.getString(Configuracion_Schema.SERIE_HOJA_FIELD, null)
            modelo.lModRSocial = cursor.getBoolean(Configuracion_Schema.MOD_RAZON_SOCIAL_FIELD ,null)
            modelo.cAplicIva = cursor.getString(Configuracion_Schema.APL_IVA_FIELD, null)
            modelo.cVerSyncAnd = cursor.getString(Configuracion_Schema.VER_SYNC_AND_FIELD, null)
            modelo.cAltaCli = cursor.getString(Configuracion_Schema.ALT_CLI_FIELD, null)
            modelo.cAltaPro = cursor.getString(Configuracion_Schema.ALT_PRO_FIELD, null)
            modelo.dInicioCierre = cursor.getLocalDateTime(Configuracion_Schema.INICIO_CIERRE_FIELD, null)
            modelo.dFinCierre = cursor.getLocalDateTime(Configuracion_Schema.FIN_CIERRE_FIELD, null)
            modelo.nAgente = cursor.getInt(Configuracion_Schema.AGENTE_FIELD, null)
            modelo.cNombreAgente = cursor.getString(Configuracion_Schema.NOMBRE_AGENTE_FIELD, null)
            modelo.cPin = cursor.getString(Configuracion_Schema.PIN_FIELD, null)
            modelo.cVehiculo = cursor.getString(Configuracion_Schema.VEHICULO_FIELD, null)
            modelo.nPuertoFtp = cursor.getInt(Configuracion_Schema.PUERTO_FTP_FIELD, null)
            modelo.cBlkDocImp = cursor.getString(Configuracion_Schema.BLK_DOC_IMP_FIELD, null)
            modelo.cSmtp = cursor.getString(Configuracion_Schema.SMTP_FIELD, null)
            modelo.cPuertoCorreo = cursor.getString(Configuracion_Schema.PUERTO_CORREO_FIELD, null)
            modelo.cCuenta = cursor.getString(Configuracion_Schema.CUENTA_FIELD, null)
            modelo.cSsl = cursor.getString(Configuracion_Schema.SSL_FIELD, null)
            modelo.cContra = cursor.getString(Configuracion_Schema.CONTRASENA_CORREO_FIELD, null)
            modelo.nEjercicio = cursor.getInt(Configuracion_Schema.EJERCICIO_FIELD, null)
            modelo.nHistConsultivo = cursor.getInt(Configuracion_Schema.HIST_CONSULTIVO_FIELD, null)
            modelo.nModoAutoVenta = cursor.getBoolean(Configuracion_Schema.MODO_AUTOVENTA_FIELD, null)
            modelo.lCobrarSoloEfectivo = cursor.getBoolean(Configuracion_Schema.COBRAR_SOLO_EFECTIVO_FIELD, null)
            modelo.lNoVenderSinExistencias = cursor.getBoolean(Configuracion_Schema.NO_VENDER_SIN_EXISTENCIAS_FIELD, null)
            modelo.lBloquearLineasManuales = cursor.getBoolean(Configuracion_Schema.BLOQUEAR_LINEAS_MANUALES_FIELD, null)
            modelo.lNoDecimales = cursor.getBoolean(Configuracion_Schema.NO_DECIMALES_FIELD, null)
            modelo.lMostrarDireccionRutero = cursor.getBoolean(Configuracion_Schema.MOSTRAR_DIRECCION_RUTERO_FIELD, null)
            modelo.lMostrarUvuc = cursor.getBoolean(Configuracion_Schema.MOSTRAR_UVUC_FIELD, null)
            modelo.lMostrarExportar = cursor.getBoolean(Configuracion_Schema.MOSTRAR_EXPORTAR_FIELD, null)
            modelo.lMostrarJornada = cursor.getBoolean(Configuracion_Schema.MOSTRAR_JORNADA_FIELD, null)
            modelo.lVenderUnTipoUnidad = cursor.getBoolean(Configuracion_Schema.VENDER_UNIDAD_UNICA_FIELD, null)
            modelo.lNoGrabarVisitaSalirVenta = cursor.getBoolean(Configuracion_Schema.NO_GRABAR_VISITA_SALIR_VENTA_FIELD, null)
            modelo.nModeloInforme = cursor.getInt(Configuracion_Schema.MODELO_INFORME_FIELD, null)
            modelo.nRecogerEnvases =  cursor.getInt(Configuracion_Schema.RECOGER_ENVASES_FIELD, null)
            modelo.nDiasBorradoAut =  cursor.getInt(Configuracion_Schema.DIAS_BORRADO_AUT_FIELD, null)
            modelo.nAnadirSoloEnvases = cursor.getInt(Configuracion_Schema.ANADIR_SOLO_ENVASES_FIELD, null)
            modelo.nMostrarPendientesCobroAntesVenta =  cursor.getInt(Configuracion_Schema.MOSTRAR_PENDIENTES_COBRO_ANTES_VENTA_FIELD, null)
            return modelo
        }
    }
}