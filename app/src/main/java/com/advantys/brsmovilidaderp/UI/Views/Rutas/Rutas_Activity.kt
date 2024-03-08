package com.advantys.brsmovilidaderp.UI.Views.Rutas

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.advantys.brsmovilidaderp.Data.DataBase.Daos.Rutas_Dao
import com.advantys.brsmovilidaderp.Data.Repositories.Ruta_Repository
import com.advantys.brsmovilidaderp.Domain.UseCases.Ruta_UseCase
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.RutaViewModelFactory
import com.advantys.brsmovilidaderp.UI.ViewModels.Ruta_ViewModel
import com.advantys.brsmovilidaderp.databinding.ActivityRutasBinding

class Rutas_Activity : AppCompatActivity() {

    private val rutasDao= Rutas_Dao(this)
    private val rutaRepository= Ruta_Repository(rutasDao)
    private val rutaUsecase= Ruta_UseCase(rutaRepository)
    private val rutaViewModel: Ruta_ViewModel by viewModels { RutaViewModelFactory(rutaUsecase)  }

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
            binding.rutasRecyclerView.post {
                binding.rutasRecyclerView.adapter= Rutas_Adapter(it,rutaViewModel)
            }
        })
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Manejar las acciones del menú
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.drawable.calendario -> {
                true
            }
            android.R.id.home -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}