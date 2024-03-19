package com.advantys.brsmovilidaderp.UI.Views.Rutas


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.Ruta_ViewModel
import com.advantys.brsmovilidaderp.databinding.ActivityRutasBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Rutas_Activity : AppCompatActivity() {
    val rutaViewModel: Ruta_ViewModel by viewModels()

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
        })
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.calendario, menu)
        return super.onCreateOptionsMenu(menu)
    }
    // Manejar las acciones del menu
    @SuppressLint("ResourceType")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.calendario->{
                mostrarMenuDiasSemana()
                true
            }
            android.R.id.home -> {
                //Boton para atras
                finish()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun mostrarMenuDiasSemana() {
        val diasSemana = arrayOf("Todos","Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Seleccione los días")
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(0, 32, 0, 0)
        for (dia in diasSemana) {
            val checkBox = CheckBox(this)
            checkBox.text = dia
            checkBox.setOnCheckedChangeListener { _, isChecked ->

            }
            layout.addView(checkBox)
        }
        builder.setView(layout)
        val dialog = builder.create()
        dialog.show()
    }
}




