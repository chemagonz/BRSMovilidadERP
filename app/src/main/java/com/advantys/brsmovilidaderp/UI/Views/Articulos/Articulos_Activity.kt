package com.advantys.brsmovilidaderp.UI.Views.Articulos

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.Articulo_ViewModel
import com.advantys.brsmovilidaderp.Utils.buscarArticulosPor
import com.advantys.brsmovilidaderp.databinding.ActivityArticulosBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Articulos_Activity : AppCompatActivity() {
    val articulosViewModel: Articulo_ViewModel by viewModels()
    var tipoSeleccionado: buscarArticulosPor = buscarArticulosPor.descripcion

    private var searchView: SearchView? = null

    private lateinit var binding: ActivityArticulosBinding

    private val responseLauncher= registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ activityResult->

        if(activityResult.resultCode== RESULT_OK){
            val familia = activityResult.data?.getShortExtra("familia", 0)
            val subfamilia = activityResult.data?.getShortExtra("subfamilia",0)
            val formato = activityResult.data?.getIntExtra("formato",0)
            val marca = activityResult.data?.getStringExtra("marca")
            val sabor =activityResult.data?.getStringExtra("sabor")
            articulosViewModel.buscarArticulosFiltro(buscarArticulosPor.descripcion, familia,subfamilia,formato,marca,sabor)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityArticulosBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            title="ARTÍCULOS"
        }
        articulosViewModel.buscarArticulosFiltro(tipoSeleccionado)
        articulosViewModel.articulosModel.observe(this, Observer {
            binding.articulosRecyclerView.layoutManager= LinearLayoutManager(this)
            binding.articulosRecyclerView.adapter = Articulos_Adapter(it, articulosViewModel)
        })
    }


    //Manejo de boton para la actividad buscar articulos
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.filtrar_articulo, menu)

        val searchItem = menu.findItem(R.id.searchArticulo)
        val searchView = searchItem.actionView as SearchView
        searchView.queryHint = "Buscar artículo"

        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {

            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                articulosViewModel.buscarArticulosFiltro(tipoSeleccionado)
                return true

            }
        })
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {

                if (!newText.isNullOrEmpty() && tipoSeleccionado!=null) {
                    articulosViewModel.buscarArticulos(tipoSeleccionado!!,newText)
                }else binding.articulosRecyclerView.adapter= Articulos_Adapter(emptyList(), articulosViewModel)

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
            R.id.filtrarArticulo ->{
                val intent= Intent(this, FiltrarArticulos_Activity::class.java)
                responseLauncher.launch(intent)
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