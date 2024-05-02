package com.advantys.brsmovilidaderp.UI.Views.MultiClientes

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.MultiClientes_ViewModel
import com.advantys.brsmovilidaderp.Utils.TipoAlerta
import com.advantys.brsmovilidaderp.Utils.mostrarSnackbar
import com.advantys.brsmovilidaderp.databinding.ActivityMultiClientesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MultiClientes_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityMultiClientesBinding
    private lateinit var adapterMultiCliente: MultiClientes_Adapter
    val multiClientesVieModel: MultiClientes_ViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
       binding = ActivityMultiClientesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            title = "MULTICÓDIGOS"
        }

        multiClientesVieModel.onCreate()
        multiClientesVieModel.multiClientesModel.observe(this , Observer {
            adapterMultiCliente = MultiClientes_Adapter(it, multiClientesVieModel)
            binding.recyclerViewMultiClientes.layoutManager= LinearLayoutManager(this)
            binding.recyclerViewMultiClientes.adapter= adapterMultiCliente
        })
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.aceptar_multiclientes, menu)
        val aceptarMulticliente = menu.findItem(R.id.aceptar_multiclientes)
        aceptarMulticliente?.setOnMenuItemClickListener {
            val elementoSeleccionado = adapterMultiCliente.getElementoSeleccionado()
            if (elementoSeleccionado != null) {
                    multiClientesVieModel.codigoFabricante(elementoSeleccionado.fabricante)
                    mostrarSnackbar("Proceso realizado con éxito", TipoAlerta.ok)
            }
            true
        }
        return true
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
}