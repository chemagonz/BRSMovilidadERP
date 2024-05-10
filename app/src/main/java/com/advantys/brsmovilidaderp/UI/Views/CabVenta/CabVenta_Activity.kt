package com.advantys.brsmovilidaderp.UI.Views.CabVenta

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.Utils.actionBar
import com.advantys.brsmovilidaderp.databinding.ActivityCabVentaBinding

class CabVenta_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityCabVentaBinding


     var pantallaPrincipal = true

    private var mostrandose = true
    private var mostrandosec = false
    private var mostrandosef = false

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCabVentaBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        actionBar("PEDIDOS")
        //inicializarComponentesPedidos()
        cabeceraAutoventa()

    }

    //Para la comprobación de modo venta, modo autoventa... hay que añadir lógica al apartado Configuración.

    private fun cabeceraAutoventa() {
        binding.checkPedido.setOnClickListener {

            if(binding.checkPedido.isChecked) {
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

            if(binding.checkFacturar.isChecked) {
                mostrarCamposFactura()
                cargarCamposFactura()
            } else {
                ocultarCamposFactura()
                binding.edNumSerieFactura.setText("")
                binding.edNumFactura.setText("")
            }
        }

        binding.checkFacturable.setOnClickListener {

            if(!binding.checkFacturable.isChecked) {

                if(binding.checkFacturar.isChecked) {

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
        if(bloquear) {
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
        if(binding.layoutFactura != null) {
            binding.layoutFactura.isVisible = true
        }
    }

    private fun ocultarCamposFactura() {
        if(binding.layoutFactura != null) {
            binding.layoutFactura.isVisible = false
        }
    }

    private fun mostrarCamposAlbaran() {
        if(binding.layoutAlbaran != null) {
            binding.layoutAlbaran.isVisible = true
        }
    }

    private fun ocultarCamposAlbaran() {
        binding.edNumPedido.isEnabled = true
        binding.edNumPedido.isFocusable = true
        binding.edNumPedido.setText("")

        if(binding.layoutAlbaran != null) {
            binding.layoutAlbaran.isVisible = false
        }
    }

    private fun ocultarAutoventa() {
        if(binding.layoutAlbaran != null) {
            binding.layoutAlbaran.isVisible = false
        }

        if(binding.layoutFactura!= null) {
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

//    private fun inicializarEventosPedidos() {
//        binding.btnBuscarPedido.setOnClickListener {
//
//            if(binding.btnBuscarPedido != null) {
//            //Cuando se busca pedido, va a la pantalla series, cuando se selecciona un pedido de series y se da a aceptar, muestra en pedido, el cliente y el centro.
////                VarGlobales.HemosEntradoEnMulti = true;
////                ManCabVenta.BuscarSerie(ManCabVenta.getCliente().getNCENTRO());//(centro!=null)? centro.getText().toString() :"-1");
//            }
//        }
//
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
//        }
//    }

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
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
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