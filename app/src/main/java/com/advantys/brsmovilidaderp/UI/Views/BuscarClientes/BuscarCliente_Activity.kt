package com.advantys.brsmovilidaderp.UI.Views.BuscarClientes

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.advantys.brsmovilidaderp.Data.DataBase.Daos.BuscarClientes_Dao
import com.advantys.brsmovilidaderp.Data.Repositories.BuscarClientes_Repository
import com.advantys.brsmovilidaderp.Domain.UseCases.BuscarCliente_UseCase
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.BuscarClienteViewModelFactoy
import com.advantys.brsmovilidaderp.UI.ViewModels.BuscarCliente_ViewModel
import com.advantys.brsmovilidaderp.databinding.ActivityBuscarClienteBinding

class BuscarCliente_Activity : AppCompatActivity() {

    private val buscarClientesDao= BuscarClientes_Dao(this)
    private val buscarClientesRepository= BuscarClientes_Repository(buscarClientesDao)
    private val buscarClientesUseCase= BuscarCliente_UseCase(buscarClientesRepository)
    private val buscarClientesVieModel: BuscarCliente_ViewModel by viewModels { BuscarClienteViewModelFactoy(buscarClientesUseCase) }
    private var tipoSeleccionado:String? =null

    lateinit var binding: ActivityBuscarClienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityBuscarClienteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //ACTION BAR
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "BUSCAR CLIENTE"


        }
        buscarClientesVieModel.onCreate()
        buscarClientesVieModel.buscarClientesModel.observe(this , Observer {
            binding.buscarClientesRecyclerView.layoutManager= LinearLayoutManager(this)
            binding.buscarClientesRecyclerView.adapter= BuscarClientes_Adapter(it, buscarClientesVieModel)
        })

    }
    // Configuración del icono de búsqueda
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.configcliente, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.queryHint = "Buscar cliente"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {

                /*Manejo del cambio de texto en la busqueda, si el texto no esta vacio y se selecciona un tipo de busqueda (tipoSeleccionado) se realiza
                la busqueda mediante la llamada a buscarClientesViewModel, si no se selecciona nada devuelve la lista vacia ya que no hay nada que mostrar*/
                if (!newText.isNullOrEmpty() && (tipoSeleccionado == "Nombre" || tipoSeleccionado == "Codigo")) {
                    buscarClientesVieModel.buscarClientes(tipoSeleccionado!!, newText)
                } else {
                    //Esto sirve por si se busca alguna letra que no se compone por algun nombre o numero por algun codigo, devuelva la lista de clientes vacia
                    binding.buscarClientesRecyclerView.adapter= BuscarClientes_Adapter(emptyList(), buscarClientesVieModel)
                }
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_configuracion -> {
                //Aqui se llama a la funcion que configura el boton de configuracion con su dialog
                tipoBusqueda()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    fun tipoBusqueda(){
        val tipo= arrayOf("Nombre", "Codigo")

        AlertDialog.Builder(this)
            .setSingleChoiceItems(tipo, -1){dialog,pos->
                tipoSeleccionado = tipo[pos]
            }
            .setNegativeButton("Cancelar",null)
            .setPositiveButton("Aceptar"){_,_->
                supportActionBar?.subtitle = when (tipoSeleccionado) {
                    "Nombre" -> "POR NOMBRE"
                    "Codigo" -> "POR CÓDIGO"
                    else -> "POR NOMBRE"
                }
            }.show()
    }

}