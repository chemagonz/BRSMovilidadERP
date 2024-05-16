package com.advantys.brsmovilidaderp.UI.Views.CabVenta

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.CabPedidos_ViewModel
import com.advantys.brsmovilidaderp.UI.ViewModels.Centro_ViewModel
import com.advantys.brsmovilidaderp.UI.ViewModels.Cliente_ViewModel
import com.advantys.brsmovilidaderp.UI.ViewModels.FormasPago_ViewModel
import com.advantys.brsmovilidaderp.UI.ViewModels.MultiClientes_ViewModel
import com.advantys.brsmovilidaderp.UI.ViewModels.RazonSocial_ViewModel
import com.advantys.brsmovilidaderp.UI.ViewModels.Serie_ViewModel
import com.advantys.brsmovilidaderp.UI.Views.Series.Series_Activity
import com.advantys.brsmovilidaderp.Utils.actionBar
import com.advantys.brsmovilidaderp.Utils.autorizadoModFacturable
import com.advantys.brsmovilidaderp.Utils.hemosEntradoEnMulti
import com.advantys.brsmovilidaderp.Utils.modoVenta
import com.advantys.brsmovilidaderp.Utils.nTipoDoc
import com.advantys.brsmovilidaderp.Utils.obligadoContado
import com.advantys.brsmovilidaderp.Utils.serieConfiguracion
import com.advantys.brsmovilidaderp.databinding.ActivityCabVentaBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale


@AndroidEntryPoint
class CabVenta_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityCabVentaBinding
    val clienteViewModel: Cliente_ViewModel by viewModels()
    val serieViewmodel: Serie_ViewModel by viewModels()
    val razonSocialViewModel: RazonSocial_ViewModel by viewModels()
    val centroViewmodel: Centro_ViewModel by viewModels()
    val formaPagoViewmodel: FormasPago_ViewModel by viewModels()
    val multiClienteViewmodel: MultiClientes_ViewModel by viewModels()
    val cabpedidosViewmodel: CabPedidos_ViewModel by viewModels()


    var pantallaPrincipal = true

    private var mostrandose = true
    private var mostrandosec = false
    private var mostrandosef = false
    private var nFabricante = -1
    private var nMetodoAContinuar = 0


    private val responseLauncher= registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ activityResult->

        if(activityResult.resultCode== RESULT_OK){
            val serie = activityResult.data?.getStringExtra("Serie")

            serie?.let {
                binding.edNumSerie.setText(it)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCabVentaBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        actionBar("PEDIDOS")
        //inicializarComponentesPedidos()
        cabeceraAutoventa()
        inicializarEventosPedidos()
        rellenoClienteModoMostrar()

    }

    //Para la comprobación de modo venta, modo autoventa... hay que añadir lógica al apartado Configuración.

    private fun inicializarComponentesPedidos() {
        binding.edSecuVenta.isEnabled = false
        binding.edSecuReparto.isEnabled = false

        inicializarEventosPedidos()


    }
    private fun inicializarComponentesCabeceraAutoventa() {
        vaciarYbloquarCamposPedido(true)
        binding.edNumFactura.isEnabled = false
        binding.edNumFactura.isFocusable = false

    }
    private fun cabeceraAutoventa() {
        binding.checkPedido.setOnClickListener {

            if (binding.checkPedido.isChecked) {
                binding.checkFacturar.isChecked = false
                ocultarCamposAlbaran()
                ocultarCamposFactura()
                vaciarYbloquarCamposPedido(false)
                //Fechas
            } else {
                mostrarCamposAlbaran()
                //IniciarCabeceraAutoventa (pedido)
                cabeceraAutoventa()

                //Insertar datos de base de datos mediante el viewModel de Cliente
                //if(ManCabVenta.getCliente().getCFACTURAINMEDIATA().equals("S") && !ManCabVenta.getEsMultiCliente())
                //{
                //	Toast.makeText(contexto, "El cliente tiene activa la facturación inmediata.", Toast.LENGTH_LONG).show();
                //	layoutfactura.setVisibility(View.VISIBLE);
                //	check_facturar.setVisibility(View.GONE);
                //	CargarCamposFactura();
                //  chkfacturable.setChecked(true);
                //	chkfacturable.setEnabled(false);
                //}
            }
        }

        binding.checkFacturar.setOnClickListener {

            if (binding.checkFacturar.isChecked) {
                mostrarCamposFactura()
                cargarCamposFactura()
            } else {
                ocultarCamposFactura()
                binding.edNumSerieFactura.setText("")
                binding.edNumFactura.setText("")
            }
        }

        binding.checkFacturable.setOnClickListener {

            if (!binding.checkFacturable.isChecked) {

                if (binding.checkFacturar.isChecked) {

                    binding.checkFacturar.performClick()
                }

                binding.checkFacturar.isEnabled = false
            } else {
                binding.checkFacturar.isEnabled = true
                binding.checkFacturar.isVisible = true

            }
        }

        binding.edNumPedido.isEnabled = false
        binding.edNumPedido.isFocusable = false
        vaciarYbloquarCamposPedido(true)

        //Este código depende de la pantalla Configuración
        //Aún así, hay que realizar, consulta sql de Series (siguientePedidoOalbaran)
        //comprobarExistenciaAlbaran
        //ObtenerNombreFabricante
        //obtenerMulticliente

//        if(!VarGlobales.SerieAlbaranConfiguracion.equals(""))
//            seriealbaran.setText(VarGlobales.SerieAlbaranConfiguracion);
//        else
//            seriealbaran.setText(ManCabVenta.getSerie());
//        numalbaran.setText(ManCabVenta.SiguientePedidoOAlbaranSerie(seriealbaran.getText().toString(), true));
//        numalbaran.setText(ManCabVenta.ComprobarExistenciaAlbaran(seriealbaran.getText().toString(), numalbaran.getText().toString()));
//        fabricante.setText(ManCabVenta.ObtenerNombreFabricante(nFabricante));
//
//        if(ManCabVenta.getCliente().getCFACTURAINMEDIATA().equals("S") && !ManCabVenta.getEsMultiCliente())
//        {
//            Toast.makeText(contexto, "El cliente tiene activa la facturación inmediata.", Toast.LENGTH_LONG).show();
//            check_facturar.setVisibility(View.GONE);
//            if (ManCabVenta.getTipo() != TipoVenta.ALBARAN)
//            {
//                CargarCamposFactura();
//                layoutfactura.setVisibility(View.VISIBLE);
//            }
//            else
//            {
//                layoutfactura.setVisibility(View.GONE);
//            }
//            chkfacturable.setChecked(true);
//            chkfacturable.setEnabled(false);
//        }

    }

    private fun vaciarYbloquarCamposPedido(bloquear: Boolean) {
        if (bloquear) {
            binding.edNumPedido.isEnabled = false
            binding.edNumPedido.isFocusable = false
            binding.edNumSerie.isEnabled = false
            binding.edNumSerie.isFocusable = false
            binding.botonPedido.isFocusable = false
            binding.botonPedido.isEnabled = false
        } else {
            binding.edNumPedido.isEnabled = true
            binding.edNumPedido.isFocusable = true
            binding.edNumSerie.isEnabled = true
            binding.edNumSerie.isFocusable = true
            binding.botonPedido.isFocusable = true
            binding.botonPedido.isEnabled = true

            //Aqui hay que llamar a viewModel serie para obtener las series
            //numSerie.text =
        }
    }

//    fun mostrarSerie(serie: Serie?) {
//        binding.edNumSerie.setText(serie?.cSeries)
//    }

    private fun cargarCamposFactura() {
        //Aqui se hace una consulta sql de Serie para obtener la factura indicada. Se ataca al viewmodel de cabVenta, mediante el caso de uso de Serie.
        binding.edNumSerieFactura.text = binding.edNumSerieAlbaran.text

        //if(ManCabVenta.getOpcion() == Opcion.AGREGAR || operacion.equals("Facturar") || ManCabVenta.getTipo() == TipoVenta.PEDIDOIMPORTADO)
        //{
        //  numfactura.setText(ManCabVenta.SiguienteFacturaSerie(seriefactura.getText().toString()));
        //  numfactura.setText(ManCabVenta.ComprobarExistenciaFactura(seriefactura.getText().toString(), numfactura.getText().toString()));
        //}
    }

    private fun mostrarCamposFactura() {
        if (binding.layoutFactura != null) {
            binding.layoutFactura.isVisible = true
        }
    }

    private fun ocultarCamposFactura() {
        if (binding.layoutFactura != null) {
            binding.layoutFactura.isVisible = false
        }
    }

    private fun mostrarCamposAlbaran() {
        if (binding.layoutAlbaran != null) {
            binding.layoutAlbaran.isVisible = true
        }
    }

    private fun ocultarCamposAlbaran() {
        binding.edNumPedido.isEnabled = true
        binding.edNumPedido.isFocusable = true
        binding.edNumPedido.setText("")

        if (binding.layoutAlbaran != null) {
            binding.layoutAlbaran.isVisible = false
        }
    }

    private fun ocultarAutoventa() {
        if (binding.layoutAlbaran != null) {
            binding.layoutAlbaran.isVisible = false
        }

        if (binding.layoutFactura != null) {
            binding.layoutFactura.isVisible = false
        }
    }

//    private fun inicializarComponentesPedidos() {
//        inicializarEventosPedidos()
//
//    }

    //Código dependiente de la pantalla Configuración
//    private fun mostrarOcultarLayoutFechas() {
//
//        if (!mostrandose)
//        {
//            layoutfechas.setVisibility(View.VISIBLE);
//            mostrandose = true;
//
//            if(!VarGlobales.ModoVenta.equals("P")){
//                binding.layoutFecha.isVisible = false
//                diafechapreventa.setVisibility(View.GONE);
//                tvfechaprev.setVisibility(View.GONE);
//            }
//        }
//        else
//        {
//            layoutfechas.setVisibility(View.GONE);
//            mostrandose = false;
//        }
//
//    }

    private fun inicializarEventosComunes() {


    }

    private fun inicializarEventosPedidos() {
        binding.btnBuscarPedido.setOnClickListener {

            if (binding.btnBuscarPedido != null) {
                //Cuando se busca pedido, va a la pantalla series, cuando se selecciona un pedido de series y se da a aceptar, muestra en pedido, el cliente y el centro.
                hemosEntradoEnMulti = true
                val intent = Intent(this, Series_Activity::class.java)
                responseLauncher.launch(intent)
//               serieViewmodel.buscarSerie()
            }
        }


//        binding.botonTotales.setOnClickListener {
//
//            pantallaPrincipal = false
//
//
//            //Se tiene que obtener la el pedido, para extraer la informacion de la vista Totales
////            if (ManCabVenta.getOpcion() != Opcion.BORRAR && ManCabVenta.getOpcion() != Opcion.MODIFICAR)
////                ObtienePedido(true);
////            else
////                ModificarPedidoModoModificar();
////
////            ManCabVenta.setRutaReparto(rutareparto.getText().toString());
////            ManCabVenta.setSecuenciaReparto(secuventa.getText().toString());
//            setContentView(R.layout.totales_ventas);
//            inicializarComponentesTotales();
////            ManCabVenta.CalcularTotales();
////            RellenarFormularioTotales();
////            tipor = tiporeparto.getText().toString();
////        }
        //  }
    }

//    private fun inicializarComponentesTotales() {
//        inicializarEventosTotales();
//    }

    //    private fun inicializarEventosTotales() {
//        val botonPedidoTotal = findViewById<Button>(R.id.botonPedidototal)
//
//        botonPedidoTotal.setOnClickListener {
//            pantallaPrincipal = true
//
////            if (ManCabVenta.getOpcion() != Opcion.BORRAR)
////            {
////                CONSULTAPEDIDOS ped = ManCabVenta.getPedido();
////                ped.setNTOTALPEDIDO(Float.parseFloat(total.getText().toString().replace(",", "")));
////                ManCabVenta.setPedido(ped);
////            }
//            setContentView(R.layout.activity_cab_venta);
//            inicializarComponentesPedidos()
////            RellenarFormularioCliente();
////            tiporeparto.setText(tipor);
////            DeshabilitarControles(ManCabVenta.getOpcion());
////
////            if(!VarGlobales.ModoVenta.equals("P")){
////                binding.layoutFecha.isVisible = false
////                diafechapreventa.setVisibility(View.GONE);
////                tvfechaprev.setVisibility(View.GONE);
////            }
//        }
//    }

    fun rellenoFormularioCliente() {
        comprobarFacturable()


    }

    @SuppressLint("SuspiciousIndentation")
    fun rellenoClienteModoMostrar() {
        comprobarFacturable()

        val numCliente = intent.getIntExtra("cliente", 0)
        val delegacion = intent.getIntExtra("numdelegacion", 0)
        val nombre = intent.getStringExtra("nombre")
        val centro = intent.getIntExtra("centro", 0)
        val razonSocial = intent.getStringExtra("razonSocial")
        val clienteFiscal = intent.getIntExtra("clienteFiscal", 0)
        val tarifa = intent.getIntExtra("tarifa", 0)
        val tipoOperacion = intent.getIntExtra("operacion", 0)
        val formaPago = intent.getIntExtra("formapago", 0)
        val porCto = intent.getFloatExtra("porCto", 0F)
        val serieAlbaran = intent.getStringExtra("serieAlbaran")
        val facturable = intent.getStringExtra("facturable")
        val avisos = intent.getStringExtra("avisos")

        binding.edNumPedido.setText("")

        //Buscar para obtener el objeto cliente, y así ahorrar este código

        //SERIES
        if(serieAlbaran.equals("")) {

            if(!serieConfiguracion.equals("")) {
                binding.edNumSerie.setText(serieConfiguracion)
            } else {
                //implementar serie de pedidos (cabpedidos)
            }
        } else {
            //implementar serie de pedidos (cabpedidos)
        }
         //OBSERVACIONES
//        if(cabpedidosViewmodel.getOpcion() == Opcion.mostrar) {
//            binding.edObservacionesPedido.isEnabled = false
//        }

        //CLIENTES
        binding.edNumCliente.setText(numCliente.toString())
        binding.edNumDelegacion.setText(delegacion.toString())

        //FORMA DE PAGO
        formaPagoViewmodel.obtenerFormaPago(formaPago)
        formaPagoViewmodel.formaPagomodel.observe(this, Observer { formapago ->
            val nombreFormapago = formapago?.nombre
            binding.edFormaPago.setText(nombreFormapago)
        })

        //CENTRO
        binding.edCentroPedidos.setText(centro.toString())

        if(binding.edCentroPedidos.text.equals("0")) {
            binding.edCentroPedidos.setText(centroViewmodel.obtenerCodCentro())
        }

        if(binding.edCentroPedidos.text.equals("0")) {
            binding.edCentroPedidos.setText("1")
        }

        //RUTA VENTAS, SECUENCIA, RUTA REPARTO, SECUENCIA REPARTO
        clienteViewModel.obtenerRutaClientes(numCliente)
        clienteViewModel.rutaClienteModel.observe(this, Observer { ruta ->
            val rutaventa = ruta?.ruta
            val rutaReparto = ruta?.rr
            var secuencia = ruta?.secuencia
            val secuRutaVenta = ruta?.sr
            val tipoReparto = ruta?.tipoReparto

            binding.edRutaVenta.setText(rutaventa)
            binding.edTipoReparto.setText(tipoReparto)

            //APARTADO PEDIDOS

//            if (!ped.getCRUTAVENTA().equals(""))
//                rutaventa.setText(ped.getCRUTAVENTA());
//            else
//                rutaventa.setText("000");

            if (secuencia == null) {
                secuencia = "0000"
            }

            if(!secuencia.equals("")) {
                binding.edSecuVenta.setText(secuencia)
            } else {
                binding.edSecuVenta.setText("0000")
            }

            if(!rutaReparto.equals("")) {
                binding.edRutaReparto.setText(rutaReparto)
            } else {
                binding.edRutaReparto.setText("000")
            }

            if(!secuRutaVenta.equals("")) {
                binding.edSecuReparto.setText(secuRutaVenta)
            } else {
                binding.edSecuReparto.setText("0000")
            }
        })

        //CLIENTE FISCAL
        binding.edClienteFiscal.setText(clienteFiscal.toString())

        //TIPO OPERACION, FALTA OBTENER MULTICLIENTE
        binding.edTipoOperacion.setText(tipoOperacion.toString())
//
//        if (ManCabVenta.getMultiCliente() != null && ManCabVenta.getMultiCliente().getNTIPOOPERACION() != -1)
//            tipooperacion.setText(Integer.toString(ManCabVenta.getMultiCliente().getNTIPOOPERACION()));
//        else
//            tipooperacion.setText(Integer.toString(Cliente.getNTIPOOPERACION()));

        //S/R SU REFERENCIA
        binding.edSuReferencia.setText("")

        //NOMBRE CLIENTE
        binding.tvNombreCliente.text = nombre

        //AVISOS
        binding.edAvisoPedidos.setText(avisos)

        //RAZON SOCIAL
        binding.tvNombreFiscal.text = razonSocial.toString()

        //CARGA IMPLEMENTAR PEDIDO
        binding.edCargaPedido.setText("1")

        //PORCDTO
        binding.edPorctoPP.setText(String.format(Locale.ENGLISH, "%.1f", porCto as Double))

//        centroViewmodel.obtenerSerieDeCentros(centro)
//        centroViewmodel.centroStringModel.observe(this, Observer { centro ->
//            val serie = centro
//            binding.edNumSerie.setText(serie)
//        })

        //TARIFA
        binding.edTarifa.setText(tarifa.toString())

        if(!modoVenta.equals("P") && nTipoDoc == 0) {

            if(facturable.equals("S") && nFabricante == -1) {
                binding.checkFacturable.isChecked = true
                binding.checkFacturar.isEnabled = true
                binding.checkFacturar.isVisible = true
            } else {
                binding.checkFacturable.isChecked = false
                binding.checkFacturar.isEnabled = false
            }
        }

//        if((!modoVenta.equals("P") && nTipoDoc == 0 || cabpedidosViewmodel.getTipo() == TipoVenta.ultalbaran)) {
//
////            INSERTAR PEDIDOS AQUI
////            if(ped.getNALBARAN() != -1)
////            {
////                MostrarCamposAlbaran();
////                numalbaran.setText(ped.getNALBARAN()+"");
////                if(ped.getCSERIEALBARAN() != null)
////                    seriealbaran.setText(ped.getCSERIEALBARAN());
////                else
////                    seriealbaran.setText(ped.getCSERIE());
////            }
////            if(ped.getNFACTURA() == 0 || ped.getNFACTURA() == -1)
////                OcultarCamposFactura();
////        }
//        }
    }

    fun rellenoClienteModoAgregar() {
       comprobarFacturable()

        val numCliente = intent.getIntExtra("cliente", 0)
        val delegacion = intent.getIntExtra("numdelegacion", 0)
        val nombre = intent.getStringExtra("nombre")
        val centro = intent.getIntExtra("centro", 0)
        val razonSocial = intent.getStringExtra("razonSocial")
        val clienteFiscal = intent.getIntExtra("clienteFiscal", 0)
        val tarifa = intent.getIntExtra("tarifa", 0)
        val tipoOperacion = intent.getIntExtra("operacion", 0)
        val formaPago = intent.getIntExtra("formapago", 0)
        val porCto = intent.getFloatExtra("porCto", 0F)
        val serieAlbaran = intent.getStringExtra("serieAlbaran")
        val facturable = intent.getStringExtra("facturable")
        val avisos = intent.getStringExtra("avisos")

        binding.edNumPedido.setText("")

        if(!serieConfiguracion.equals("")) {
            binding.edNumSerie.setText(serieConfiguracion)
        } else {
            //serie.setText(ManCabVenta.getSerie());
        }

//        if (cabpedidosViewmodel.getOpcion() == Opcion.borrar) {
//            binding.edObservacionesPedido.isEnabled = false
//        }

        //CLIENTES
        binding.edNumCliente.setText(numCliente.toString())
        binding.edNumDelegacion.setText(delegacion.toString())

        //FORMA DE PAGO
        formaPagoViewmodel.obtenerFormaPago(formaPago)
        formaPagoViewmodel.formaPagomodel.observe(this, Observer { formapago ->
            val nombreFormapago = formapago?.nombre
            binding.edFormaPago.setText(nombreFormapago)
        })

        //INSERTAR MULTICLIENTE
//        if (ManCabVenta.getEsMultiCliente() && ManCabVenta.getMultiCliente() != null) {
//            val cent: String = ManCabVenta.ComprobarCentroSerie(serie.getText().toString())
//            if (cent != "") {
//                centro.setText(cent)
//                if (centro.getText().equals("-1")) centro.setText(
//                    ManCabVenta.ObtenerCodCentro().toString()
//                )
//            } else centro.setText(ManCabVenta.ObtenerCodCentro().toString())
//            if (centro.getText().equals("-1")) centro.setText("1")
//        } else {
//            centro.setText(Cliente.getNCENTRO().toString())
//            if (centro.getText().equals("-1")) centro.setText(
//                ManCabVenta.ObtenerCodCentro().toString()
//            )
//            if (centro.getText().equals("-1")) centro.setText("1")
//        }

        //RUTA VENTAS, SECUENCIA, RUTA REPARTO, SECUENCIA REPARTO
        clienteViewModel.obtenerRutaClientes(numCliente)
        clienteViewModel.rutaClienteModel.observe(this, Observer { ruta ->
            val rutaventa = ruta?.ruta
            val rutaReparto = ruta?.rr
            var secuencia = ruta?.secuencia
            val secuRutaVenta = ruta?.sr
            val tipoReparto = ruta?.tipoReparto


            binding.edTipoReparto.setText(tipoReparto)

            if(!rutaventa.equals("")) {
                binding.edRutaVenta.setText(rutaventa)
            } else {
                binding.edRutaVenta.setText("000")
            }

            if(secuencia == null) {
                secuencia = "0000"
            }
            if(!secuencia.equals("")) {
                binding.edSecuVenta.setText(secuencia)
            } else {
                binding.edSecuVenta.setText("0000")
            }

            //AGREGAR OBJETO CLIENTE
//            if (!ManCabVenta.obtenerRutaVenta(Cliente).equals(""))
//                rutareparto.setText(ManCabVenta.obtenerRutaVenta(Cliente))
//            else
//                rutareparto.setText("000")
//
//            if (!ManCabVenta.obtenerSecuenciaRutaVenta(Cliente).equals(""))
//                secureparto.setText(ManCabVenta.obtenerSecuenciaRutaVenta(Cliente))
//            else
//                secureparto.setText("0000")
        })

        //CLIENTE FISCAL
        binding.edClienteFiscal.setText(clienteFiscal.toString())

//            ActualizarFecha()
//            try {
//                diafechaservicio.setText(
//                    CambiarFecha(
//                        ComprobarDiaSemanaPreventa(
//                            SimpleDateFormat("dd/MM/yyyy").parse(
//                                tvfechaserv.getText().toString()
//                            )
//                        )
//                    )
//                )
//            } catch (e: ParseException) {
//                e.printStackTrace()
//            }
//
//            //tipooperacion.setText(Integer.toString(Cliente.getNTIPOOPERACION()));
//
//            //tipooperacion.setText(Integer.toString(Cliente.getNTIPOOPERACION()));
//            if (ManCabVenta.getMultiCliente() != null && ManCabVenta.getMultiCliente()
//                    .getNTIPOOPERACION() !== -1
//            ) tipooperacion.setText(
//                ManCabVenta.getMultiCliente().getNTIPOOPERACION().toString()
//            ) else tipooperacion.setText(Cliente.getNTIPOOPERACION().toString())

        // Si el tipo de cliente no es facturable, deja la casilla facturable vacía y deshabilitada
        if(facturable.equals("N")) {
            binding.checkFacturable.isChecked = false
            binding.checkFacturable.isEnabled = false
        }

        //S/R SU REFERENCIA
        binding.edSuReferencia.setText("")

        //NOMBRE CLIENTE
        binding.tvNombreCliente.text = nombre

        //SQL PARA OBTENER RAZON SOCIAL

        if (clienteFiscal != -1) {
            binding.tvNombreFiscal.text = razonSocial.toString()
        }
//            if (Cliente.getNCLIENTEFISCAL() != -1)
//                tvfiscal.setText(ManCabVenta.getRazonSocial(Cliente.getNCLIENTEFISCAL()).getCNOMBRE())
//            else
//                tvfiscal.setText(tvcliente.getText())

        //AVISOS
        binding.edAvisoPedidos.setText(avisos)

        //PORCDTO
        binding.edPorctoPP.setText(String.format(Locale.ENGLISH, "%.1f", porCto as Double))
//
//            if (ManCabVenta.getEsMultiCliente() && ManCabVenta.getMultiCliente() != null && ManCabVenta.getMultiCliente().getNTARIFA()!=-1)
//                tarifa.setText(ManCabVenta.getMultiCliente().getNTARIFA()+"");
//            else tarifa.setText(Cliente.getNTARIFA()+"");

        if (modoVenta.equals("P") && nTipoDoc ==0) {
            if(facturable.equals("S") && nFabricante == -1) {
                binding.checkFacturable.isChecked = true
                binding.checkFacturar.isEnabled = true
                binding.checkFacturar.isVisible = true
            } else {
                binding.checkFacturable.isChecked = false
                binding.checkFacturar.isEnabled = false
            }
        }

        if(binding.layoutAlbaran != null && (modoVenta.equals("P") && nTipoDoc == 0)) {

            binding.edNumPedido.isEnabled = false

            if(binding.edNumAlbaran == null) binding.edNumAlbaran
            if(binding.edNumSerieAlbaran == null) binding.edNumSerieAlbaran
            if(binding.btnBuscarSerieAlbaran == null) binding.edNumSerieAlbaran
        }

        //seriealbaran.setText(ManCabVenta.getSerie())

        if(!binding.edNumSerie.equals(binding.edNumSerieAlbaran)) binding.edNumSerie.text = binding.edNumSerieAlbaran.text

//        String nSiguienteNumero = ManCabVenta.SiguientePedidoOAlbaranSerie(seriealbaran.getText().toString(), true);
//        if(nSiguienteNumero == null)
//        {
//            ((Activity) contexto).setResult(Activity.CONTEXT_RESTRICTED);
//            ((Activity) contexto).finish();
//        }
//
//        else
//        {
//            numalbaran.setText(nSiguienteNumero);
//            numalbaran.setText(ManCabVenta.ComprobarExistenciaAlbaran(seriealbaran.getText().toString(), numalbaran.getText().toString()));
//
//            if(Cliente.getCFACTURAINMEDIATA().equals("S") && !ManCabVenta.getEsMultiCliente())
//            {
//                Toast.makeText(contexto, "El cliente tiene activa la facturación inmediata.", Toast.LENGTH_LONG).show();
//                layoutfactura.setVisibility(View.VISIBLE);
//                check_facturar.setVisibility(View.GONE);
//                CargarCamposFactura();
//                chkfacturable.setChecked(true);
//                chkfacturable.setEnabled(false);
//            }
//        }
//    }
//    else
//    {
//        if(check_solopedido == null)
//            check_solopedido = (CheckBox) findViewById(R.id.tabventaID_checksolopedido);
//        check_solopedido.setVisibility(View.GONE);
//    }

    }
    private fun comprobarFacturable(){
        //Faltaría aquí objeto de pedido para implementarlo a la lógica
        //Implementar objeto Cliente
        val clienteFacturable = intent.getStringExtra("facturable")

        //Obtener multicliente teniendo en cuenta el fabricante
        //multiClienteViewmodel.obtenerMultiClientes(cliente, fabricante)


        if(clienteFacturable != null) {
            if(clienteFacturable.equals("N") ){
                binding.checkFacturable.isChecked = false
                binding.checkFacturable.isEnabled = false
            }
        }

        if(clienteFacturable.equals("S")) {
            //if(ManCabVenta.getMultiCliente() != null && ManCabVenta.getMultiCliente().getCFACTURABLE() != null && ManCabVenta.getMultiCliente().getCFACTURABLE().equals("S"))
            binding.checkFacturable.isChecked = true
            if(autorizadoModFacturable)  binding.checkFacturable.isEnabled = true
            else  binding.checkFacturable.isEnabled = false
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.botones_pedidos, menu)

        val aceptarPedido = menu.findItem(R.id.aceptarPedido)
        val editarPedido = menu.findItem(R.id.editablePedido)
        val calendarioPedido = menu.findItem(R.id.calendarioPedido)

        aceptarPedido?.setOnMenuItemClickListener {

            obligadoContado = false
            nMetodoAContinuar = 1

            true
        }

        editarPedido?.setOnMenuItemClickListener {

            true
        }

        calendarioPedido?.setOnMenuItemClickListener {

            true
        }
        return true
    }
}