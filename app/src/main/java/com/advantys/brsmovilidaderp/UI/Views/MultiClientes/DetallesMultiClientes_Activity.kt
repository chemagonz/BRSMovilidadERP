package com.advantys.brsmovilidaderp.UI.Views.MultiClientes

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.advantys.brsmovilidaderp.Domain.Models.Fabricante
import com.advantys.brsmovilidaderp.Domain.Models.MultiCliente
import com.advantys.brsmovilidaderp.UI.ViewModels.Fabricante_ViewModel
import com.advantys.brsmovilidaderp.UI.ViewModels.MultiClientes_ViewModel
import com.advantys.brsmovilidaderp.databinding.ActivityDetallesMultiClientesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetallesMultiClientes_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityDetallesMultiClientesBinding
    val multiclientesViewmodel: MultiClientes_ViewModel by viewModels()
    val fabricanteViewmodel: Fabricante_ViewModel by viewModels()
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
        })

        fabricanteViewmodel.fabricanteModel.observe(this, Observer { fabricante ->
            fabricante?.let { verDetallesNombreFab(fabricante) }
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
        binding.edFacturable.setText(multiCliente.facturable.toString())
        binding.edTipoOperacion.setText(multiCliente.tipoOperacion.toString())
        binding.edTarifa.setText(multiCliente.tarifa.toString())
        binding.edSerie.setText(multiCliente.serieAlbaran.toString())

    }

    private fun verDetallesNombreFab(fabricante: Fabricante){
        binding.edNombrefab.setText(fabricante.nombre)
    }
}