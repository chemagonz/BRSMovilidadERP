package com.advantys.brsmovilidaderp.UI.Views.Articulos

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.Articulo_ViewModel
import com.advantys.brsmovilidaderp.Utils.BuscarArticulosPor
import com.advantys.brsmovilidaderp.databinding.ActivityArticulosBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Articulos_Activity : AppCompatActivity() {

    val articulosViewModel: Articulo_ViewModel by viewModels()
    var tipoSeleccionado: BuscarArticulosPor = BuscarArticulosPor.descripcion

    private var searchView: SearchView? = null
    private var familiaID: Short? = null
    private var subfamiliaID: Short? = null
    private var formatoID: Int? = null
    private var marcaID: String? = null
    private var saborID: String? = null

    private lateinit var binding: ActivityArticulosBinding

    private lateinit var sharedPreferences: SharedPreferences
    private val SHARED_PREFS_KEY = "FiltrarArticulosPrefs"
    private val KEY_SELECTED_FAMILIA = "Familia"
    private val KEY_SELECTED_SUBFAMILIA = "Subfamilia"
    private val KEY_SELECTED_FORMATO = "Formato"
    private val KEY_SELECTED_MARCA = "Marca"
    private val KEY_SELECTED_SABOR = "Sabor"

    private val responseLauncher= registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ activityResult->

        if(activityResult.resultCode== RESULT_OK){
            familiaID= activityResult.data?.getShortExtra("familia", -1)
            subfamiliaID= activityResult.data?.getShortExtra("subfamilia",-1)
            formatoID = activityResult.data?.getIntExtra("formato",-1)
            marcaID= activityResult.data?.getStringExtra("marca")
            saborID =activityResult.data?.getStringExtra("sabor")
            articulosViewModel.buscarArticulosFiltro(BuscarArticulosPor.descripcion,familiaID,subfamiliaID,formatoID,marcaID,saborID)
        }else articulosViewModel.buscarArticulosFiltro(tipoSeleccionado)
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
        sharedPreferences = getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE)
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
                    articulosViewModel.buscarArticulosFiltro(tipoSeleccionado!!,familiaID,subfamiliaID, formatoID,marcaID,saborID,newText)
                }else binding.articulosRecyclerView.adapter= Articulos_Adapter(emptyList(), articulosViewModel)

                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home->{
                cleanFiltros()
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
                    tipoSeleccionado = BuscarArticulosPor.descripcion
                    supportActionBar?.subtitle = "DESCRIPCIÓN"
                    searchView?.setQuery("", false)
                    true
                }

                R.id.codigo -> {
                    tipoSeleccionado = BuscarArticulosPor.codigo
                    supportActionBar?.subtitle = "CÓDIGO"
                    searchView?.setQuery("", false)
                    true
                }

                R.id.mostrar -> {
                    articulosViewModel.buscarArticulosFiltro(tipoSeleccionado)
                    articulosViewModel.articulosModel.observe(this, Observer {
                        binding.articulosRecyclerView.adapter = Articulos_Adapter_mostrar(it, articulosViewModel)
                        val layoutManager = GridLayoutManager(this, 2)
                        binding.articulosRecyclerView.layoutManager = layoutManager
                    })
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }


    private fun cleanFiltros() {
        val editor = sharedPreferences.edit()
        editor.remove(KEY_SELECTED_FAMILIA)
        editor.remove(KEY_SELECTED_SUBFAMILIA)
        editor.remove(KEY_SELECTED_FORMATO)
        editor.remove(KEY_SELECTED_MARCA)
        editor.remove(KEY_SELECTED_SABOR)
        editor.apply()
    }
}