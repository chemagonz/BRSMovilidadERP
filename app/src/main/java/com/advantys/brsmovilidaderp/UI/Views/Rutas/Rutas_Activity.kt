package com.advantys.brsmovilidaderp.UI.Views.Rutas

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.PopupMenu
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
                showPopMenu()
                true
            }
            android.R.id.home -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showPopMenu(){
        val popMenu= PopupMenu(this, findViewById(R.id.calendario))
        popMenu.menuInflater.inflate(R.menu.semana, popMenu.menu)
        popMenu.setOnMenuItemClickListener { menuItem->
            when(menuItem.itemId) {
                R.id.lunes->true
                R.id.martes->true
                R.id.miercoles->true
                R.id.jueves->true
                R.id.viernes->true
                R.id.sabado->true
                R.id.domingo->true
                else -> false
            }
        }
        popMenu.show()
    }

//    fun mostrarVentana(){
//        val diasSemana = arrayOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")
//        val checkedItems = booleanArrayOf(false, false, false, false, false, false, false)
//        AlertDialog.Builder(this)
//            .setTitle("Selecciona los días de la semana")
//            .setMultiChoiceItems(diasSemana, checkedItems) { _, _, _ -> }
//            .setNegativeButton("Cancelar", null)
//            .setPositiveButton("Aceptar") { _, _ ->
//                //Aqui ya se modificara la accion de aceptar en la base de datos
//            }
//            .show()
//    }
}



