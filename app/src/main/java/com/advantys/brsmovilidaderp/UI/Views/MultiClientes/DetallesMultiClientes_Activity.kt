package com.advantys.brsmovilidaderp.UI.Views.MultiClientes

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.advantys.brsmovilidaderp.Domain.Models.Cliente
import com.advantys.brsmovilidaderp.Domain.Models.Fabricante
import com.advantys.brsmovilidaderp.Domain.Models.MultiCliente
import com.advantys.brsmovilidaderp.Domain.Models.Serie
import com.advantys.brsmovilidaderp.Domain.Models.Tarifa
import com.advantys.brsmovilidaderp.Domain.Models.TipoOperacion
import com.advantys.brsmovilidaderp.UI.ViewModels.Cliente_ViewModel
import com.advantys.brsmovilidaderp.UI.ViewModels.Fabricante_ViewModel
import com.advantys.brsmovilidaderp.UI.ViewModels.MultiClientes_ViewModel
import com.advantys.brsmovilidaderp.UI.ViewModels.Serie_ViewModel
import com.advantys.brsmovilidaderp.UI.ViewModels.Tarifa_ViewModel
import com.advantys.brsmovilidaderp.UI.ViewModels.TipoOperacion_ViewModel
import com.advantys.brsmovilidaderp.databinding.ActivityDetallesMultiClientesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetallesMultiClientes_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityDetallesMultiClientesBinding
    val multiclientesViewmodel: MultiClientes_ViewModel by viewModels()
    val fabricanteViewmodel: Fabricante_ViewModel by viewModels()
    val clienteViewmodel: Cliente_ViewModel by viewModels()
    val tarifaViewmodel: Tarifa_ViewModel by viewModels()
    val serieViewmodel: Serie_ViewModel by viewModels()
    val tipoOperacionViewmodel: TipoOperacion_ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetallesMultiClientesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            title = "DETALLES"
            subtitle = "MULTICÓDIGOS"
        }

        val multiclienteDet= intent.getIntExtra("multiCliente", 0)
        multiclientesViewmodel.onCreateDetalles(multiclienteDet)
        multiclientesViewmodel.multiClienteModel.observe(this, Observer{ multicliente ->
            multicliente?.let { verDetallesMultiCliente(multicliente)}
            fabricanteViewmodel.onCreateGetNombreFab(multicliente?.fabricante)
            clienteViewmodel.onCreateNombre(multicliente?.multiCliente)
            tarifaViewmodel.onCreate(multicliente?.tarifa)
            serieViewmodel.onCreateNombreSerie(multicliente?.serieAlbaran)
            tipoOperacionViewmodel.onCreate(multicliente?.tipoOperacion)
        })

        fabricanteViewmodel.fabricanteModel.observe(this, Observer { fabricante ->
            fabricante?.let { verDetallesNombreFab(fabricante) }
        })

        clienteViewmodel.clienteModel.observe(this, Observer { cliente ->
            cliente?.let { nombreCliente(cliente) }
        })

        tarifaViewmodel.tarifasModel.observe(this, Observer { tarifa ->
            tarifa?.let { nombreTarifa(tarifa) }
        })

       serieViewmodel.serieModel.observe(this, Observer { serie ->
            serie?.let { nombreSerie(serie) }
        })

        tipoOperacionViewmodel.operacionModel.observe(this, Observer { operacion ->
            operacion?.let { nombreOperacion(operacion) }
        })
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

    private fun verDetallesMultiCliente(multiCliente: MultiCliente) {

        binding.edCodigomultiCliente.setText(multiCliente.multiCliente.toString())
        binding.edDelegacion.setText(multiCliente.multiDelegacion.toString())
        binding.edSuCliente.setText(multiCliente.clieFabri.toString())
        //binding.edSerie.setText(multiCliente.serieAlbaran)
    }

    private fun verDetallesNombreFab(fabricante: Fabricante){
        binding.edCodFab.setText(fabricante.numfabricante.toString())
        binding.edNombreFab.setText(fabricante.nombre)
    }

    private fun nombreCliente(cliente: Cliente){
        binding.edNombreCliente.setText(cliente.nombre)
    }
    private fun nombreTarifa(tarifa: Tarifa){
        val detalles = StringBuilder()

        detalles.append(tarifa.numTarifa).append(" - ")
        detalles.append(tarifa.nombre)
        binding.edTarifa.setText(detalles)

    }

    private fun nombreSerie(serie : Serie){
        val detalles = StringBuilder()

        detalles.append(serie.cSeries).append(" - ")
        detalles.append(serie.nombre)
        binding.edSerie.setText(detalles)

    }

    private fun nombreOperacion(operacion : TipoOperacion){
        val detalles = StringBuilder()

        detalles.append(operacion.tipoOperacion).append(" - ")
        detalles.append(operacion.nombre)
        binding.edTipoOperacion.setText(detalles)

    }
}