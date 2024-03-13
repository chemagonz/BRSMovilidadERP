package com.advantys.brsmovilidaderp.UI.Views.Rutas

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.Ruta_ViewModel
import com.advantys.brsmovilidaderp.UI.Views.Clientes.Clientes_Activity
import com.advantys.brsmovilidaderp.databinding.ActivityRutasBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Rutas_Activity : AppCompatActivity() {

//    private val rutasDao= Rutas_Dao(this)
//    private val rutaRepository= Ruta_Repository(rutasDao)
//    private val rutaUsecase= Ruta_UseCase(rutaRepository)
    val rutaViewModel: Ruta_ViewModel by viewModels()
//    { RutaViewModelFactory(rutaUsecase)  }

    private lateinit var binding: ActivityRutasBinding
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRutasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //ACTION BAR
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "RUTAS"

        }
        rutaViewModel.onCreate()
        rutaViewModel.rutasModel.observe(this, Observer {
            binding.rutasRecyclerView.layoutManager = LinearLayoutManager(this)
            binding.rutasRecyclerView.adapter= Rutas_Adapter(it,rutaViewModel)
            //binding.rutasRecyclerView.addItemDecoration(CustomItemDecoration(0)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       )

        })
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Manejar las acciones del menu
    @SuppressLint("ResourceType")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.calendario->{
                true
            }
            android.R.id.home -> {
                //Boton para atras
                val intent = Intent(this, Clientes_Activity::class.java)
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}



