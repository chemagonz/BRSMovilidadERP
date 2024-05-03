package com.advantys.brsmovilidaderp.UI.Views.Clientes

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.Cliente_ViewModel
import com.advantys.brsmovilidaderp.Utils.MostrarPor
import com.advantys.brsmovilidaderp.Utils.OrdenarPor
import com.advantys.brsmovilidaderp.databinding.ActivityOrdenarClientesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrdenarClientes_Activity : AppCompatActivity(){
    private lateinit var binding: ActivityOrdenarClientesBinding
    private val ordenarClientesViewModel: Cliente_ViewModel by viewModels()
    private lateinit var adapter: OrdenarClientes_Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityOrdenarClientesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            title = "ORDENAR RUTA"
        }
        adapter = OrdenarClientes_Adapter(mutableListOf(), ordenarClientesViewModel)
        binding.recyclerviewOrdenarClientes.layoutManager = LinearLayoutManager(this)
        binding.recyclerviewOrdenarClientes.adapter = adapter

        val moverCliente = ItemTouchHelper(MoverCliente(adapter))
        moverCliente.attachToRecyclerView(binding.recyclerviewOrdenarClientes)

        //ordenarClientesViewModel.onCreate()
        ordenarClientesViewModel.obtenerConsultaClientes(OrdenarPor.ruta, MostrarPor.todos)
        ordenarClientesViewModel.ClientesModel.observe(this, Observer { ordenarClienteList ->
            // Actualiza los datos del adaptador
            adapter.ordenarClientesList.clear()
            adapter.ordenarClientesList.addAll(ordenarClienteList)
            adapter.notifyDataSetChanged()
        })

    }

    private fun guardarOrdenEnBaseDeDatos() {

        for (i in 0 until adapter.itemCount) {
            val cliente = adapter.ordenarClientesList[i]
             ordenarClientesViewModel.guardarOrdenClientes(cliente?.numClientes, cliente?.delegacion, i)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.botones_ordenar_clientes, menu)
        val aceptarOrdenClientes = menu.findItem(R.id.aceptar_ordenar_clientes)
        val borrarOrdenClientes = menu.findItem(R.id.borrar_orden)
        aceptarOrdenClientes?.setOnMenuItemClickListener {
                guardarOrdenEnBaseDeDatos()
                setResult(RESULT_OK)
            true
        }
        borrarOrdenClientes?.setOnMenuItemClickListener {
                ordenarClientesViewModel.guardarNullOrdenClientes()
            true
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home->{
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}