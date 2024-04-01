package com.advantys.brsmovilidaderp.UI.Views.Articulos

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.Articulo_ViewModel
import com.advantys.brsmovilidaderp.UI.Views.BuscarArticulos.BuscarArticulos_Activity
import com.advantys.brsmovilidaderp.databinding.ActivityArticulosBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Articulos_Activity : AppCompatActivity() {
    val articulosViewModel: Articulo_ViewModel by viewModels()
    private lateinit var binding: ActivityArticulosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityArticulosBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            title="ARTÍCULOS"
        }

        articulosViewModel.onCreate()
        articulosViewModel.articulosModel.observe(this, Observer {
            binding.articulosRecyclerView.layoutManager= LinearLayoutManager(this)
            binding.articulosRecyclerView.adapter = Articulos_Adapter(it, articulosViewModel)
        })
    }


    //Manejo de boton para la actividad buscar articulos
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.buscar, menu)
        val busquedaItem= menu?.findItem(R.id.busqueda)
        busquedaItem?.setOnMenuItemClickListener {
            val intent= Intent(this, BuscarArticulos_Activity::class.java)
            startActivity(intent)
            true
        }
        return super.onCreateOptionsMenu(menu)
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