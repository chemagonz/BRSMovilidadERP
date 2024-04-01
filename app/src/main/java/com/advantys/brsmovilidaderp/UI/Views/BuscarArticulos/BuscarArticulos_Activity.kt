package com.advantys.brsmovilidaderp.UI.Views.BuscarArticulos

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.Articulo_ViewModel
import com.advantys.brsmovilidaderp.databinding.ActivityBuscarArticulosBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BuscarArticulos_Activity : AppCompatActivity() {
    val buscarArticulosViewModel: Articulo_ViewModel by viewModels()
    private lateinit var binding: ActivityBuscarArticulosBinding
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
        menuInflater.inflate(R.menu.buscar_articulo, menu)

        val searchItem = menu.findItem(R.id.buscarArticulo)
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

                if (!newText.isNullOrEmpty()) {
                    buscarArticulosViewModel.buscarArticulos(newText)
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
            else -> super.onOptionsItemSelected(item)
        }
    }
}

