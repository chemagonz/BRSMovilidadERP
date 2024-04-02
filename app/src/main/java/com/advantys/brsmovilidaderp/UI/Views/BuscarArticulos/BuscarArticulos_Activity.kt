package com.advantys.brsmovilidaderp.UI.Views.BuscarArticulos

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.Articulo_ViewModel
import com.advantys.brsmovilidaderp.Utils.buscarArticulosPor
import com.advantys.brsmovilidaderp.databinding.ActivityBuscarArticulosBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BuscarArticulos_Activity : AppCompatActivity() {
    val buscarArticulosViewModel: Articulo_ViewModel by viewModels()
    var tipoSeleccionado: buscarArticulosPor= buscarArticulosPor.descripcion
    private lateinit var binding: ActivityBuscarArticulosBinding
    private var searchView: SearchView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityBuscarArticulosBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title="BUSCAR ARTÍCULOS"
        }

        buscarArticulosViewModel.onCreate()
        buscarArticulosViewModel.articulosModel.observe(this, Observer {
            binding.busquedaArticulosRecyclerView.layoutManager=LinearLayoutManager(this)
            binding.busquedaArticulosRecyclerView.adapter= BuscarArtictulos_Adapter(it, buscarArticulosViewModel)
        })

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.filtrar_articulo, menu)

        val searchItem = menu.findItem(R.id.searchArticulo)
        val searchView = searchItem.actionView as SearchView
        searchView.queryHint = "Buscar articulo"

        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {

            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                buscarArticulosViewModel.onCreate()
                return true

            }
        })
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {

                if (!newText.isNullOrEmpty() && tipoSeleccionado!=null) {
                    buscarArticulosViewModel.buscarArticulos(tipoSeleccionado!!,newText)
                }else binding.busquedaArticulosRecyclerView.adapter= BuscarArtictulos_Adapter(emptyList(), buscarArticulosViewModel)

                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home->{
                finish()
                true
            }
            R.id.busquedaArticuloPor ->{
                showPopupMenu()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showPopupMenu() {
        val popupMenu = PopupMenu(this, findViewById(R.id.busquedaArticuloPor))
        popupMenu.menuInflater.inflate(R.menu.articulos_descripcion_codigo, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.descripcionlarga -> {
                    tipoSeleccionado = buscarArticulosPor.descripcion
                    supportActionBar?.subtitle = "DESCRIPCIÓN"
                    searchView?.setQuery("", false)
                    true
                }
                R.id.codigo -> {
                    tipoSeleccionado = buscarArticulosPor.codigo
                    supportActionBar?.subtitle = "CÓDIGO"
                    searchView?.setQuery("", false)
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }

}

