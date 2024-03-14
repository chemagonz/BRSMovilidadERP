package com.advantys.brsmovilidaderp.UI.Views.BuscarClientes

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.advantys.brsmovilidaderp.Data.DataBase.Daos.columnas
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.Cliente_ViewModel
import com.advantys.brsmovilidaderp.databinding.ActivityBuscarClienteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BuscarCliente_Activity : AppCompatActivity() {

//    private val buscarClientesDao= BuscarClientes_Dao(this)
//    private val buscarClientesRepository= BuscarClientes_Repository(buscarClientesDao)
//    private val buscarClientesUseCase= BuscarCliente_UseCase(buscarClientesRepository)
    val buscarClientesVieModel: Cliente_ViewModel by viewModels()
    //{ BuscarClienteViewModelFactoy(buscarClientesUseCase) }
    private var tipoSeleccionado:columnas?= columnas.Nombre




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
            subtitle= "POR NOMBRE"


        }
        buscarClientesVieModel.onCreate()
        buscarClientesVieModel.ClientesModel.observe(this , Observer {
            binding.buscarClientesRecyclerView.layoutManager= LinearLayoutManager(this)
            binding.buscarClientesRecyclerView.adapter= BuscarClientes_Adapter(it, buscarClientesVieModel)
        })

    }
    // Configuración del icono de búsqueda
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.configcliente, menu)

        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView
        searchView.queryHint = "Buscar cliente"

        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {

            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
               return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                buscarClientesVieModel.onCreate()
                return true
            }
        })
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {

                /*Manejo del cambio de texto en la busqueda, si el texto no esta vacio y se selecciona un tipo de busqueda (tipoSeleccionado) se realiza
                la busqueda mediante la llamada a buscarClientesViewModel, si no se selecciona nada devuelve la lista vacia ya que no hay nada que mostrar*/
                if (!newText.isNullOrEmpty() && tipoSeleccionado !=null) {
                    buscarClientesVieModel.buscarClientes(tipoSeleccionado!!, newText)
                }else binding.buscarClientesRecyclerView.adapter= BuscarClientes_Adapter(emptyList(), buscarClientesVieModel)

                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.busquedaPor -> {
                showPopupMenu()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    //Ventana para elegir por nombre o por codigo
    private fun showPopupMenu() {
        val popupMenu = PopupMenu(this, findViewById(R.id.busquedaPor))
        popupMenu.menuInflater.inflate(R.menu.busquedaclientes, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nombre -> {
                    tipoSeleccionado = columnas.Nombre
                    supportActionBar?.subtitle = "POR NOMBRE"
                    true
                }
                R.id.codigo -> {
                    tipoSeleccionado = columnas.Codigo
                    supportActionBar?.subtitle = "POR CODIGO"
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }
}

