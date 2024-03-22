package com.advantys.brsmovilidaderp.UI.Views.BuscarArticulos

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.advantys.brsmovilidaderp.UI.ViewModels.Articulo_ViewModel
import com.advantys.brsmovilidaderp.UI.Views.Articulos.Articulos_Adapter
import com.advantys.brsmovilidaderp.databinding.ActivityBuscarArticulosBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BuscarArticulos_Activity : AppCompatActivity() {
    val buscarArticulosViewModel: Articulo_ViewModel by viewModels()
    private lateinit var binding: ActivityBuscarArticulosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityBuscarArticulosBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        supportActionBar?.apply {
            title="BUSCAR ARTÍCULOS"
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }

        buscarArticulosViewModel.onCreate()
        buscarArticulosViewModel.articulosModel.observe(this, Observer {
            binding.busquedaArticulosRecyclerView.layoutManager=LinearLayoutManager(this)
            binding.busquedaArticulosRecyclerView.adapter= Articulos_Adapter(it, buscarArticulosViewModel)
        })

    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.buscar, menu)
//
//        val searchItem = menu.findItem(R.id.busqueda)
//        val searchView = searchItem.actionView as SearchView
//        searchView.queryHint = "Buscar articulo"
//
//        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
//
//            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
//                return true
//            }
//
//            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
//                articulosViewModel.onCreate()
//                return true
//            }
//        })
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//            override fun onQueryTextChange(newText: String?): Boolean {
//
//                /*Manejo del cambio de texto en la busqueda, si el texto no esta vacio y se selecciona un tipo de busqueda (tipoSeleccionado) se realiza
//                la busqueda mediante la llamada a buscarClientesViewModel, si no se selecciona nada devuelve la lista vacia ya que no hay nada que mostrar*/
//                if (!newText.isNullOrEmpty() && tipoSeleccionado !=null) {
//                    buscarClientesVieModel.buscarClientes(tipoSeleccionado!!, newText)
//                }else binding.buscarClientesRecyclerView.adapter= BuscarClientes_Adapter(emptyList(), buscarClientesVieModel)
//
//                return false
//            }
//        })
//        return super.onCreateOptionsMenu(menu)
//    }
}

