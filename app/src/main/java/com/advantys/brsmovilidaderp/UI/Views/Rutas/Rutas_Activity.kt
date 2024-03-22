package com.advantys.brsmovilidaderp.UI.Views.Rutas


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.Cliente_ViewModel
import com.advantys.brsmovilidaderp.UI.ViewModels.Ruta_ViewModel
import com.advantys.brsmovilidaderp.Utils.Utils
import com.advantys.brsmovilidaderp.Utils.dias
import com.advantys.brsmovilidaderp.databinding.ActivityRutasBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Rutas_Activity : AppCompatActivity() {
    val rutaViewModel: Ruta_ViewModel by viewModels()
    val clienteViewmodel: Cliente_ViewModel by viewModels()

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
        menuInflater.inflate(R.menu.calendario_dias_rutas, menu)
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
                finish()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun mostrarMenuDiasSemana() {
        //Definir un array para los dias de la semana y el apartado de todos los dias
        val diasSemana = arrayOf("Todos","Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Seleccione los días")
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(0, 32, 0, 0)

        //CompounButton sirve para detectar cambios en los checkbox, segun el estado del checkbox se actualizara en UTILS
        val checkBoxListener = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            val dia = buttonView.text.toString()
            when (dia) {

                "Lunes" -> Utils.diasSeleccionados[dias.lunes] = isChecked
                "Martes" -> Utils.diasSeleccionados[dias.martes] = isChecked
                "Miércoles" -> Utils.diasSeleccionados [dias.miercoles]= isChecked
                "Jueves" -> Utils.diasSeleccionados [dias.jueves]= isChecked
                "Viernes" ->Utils.diasSeleccionados [dias.viernes]= isChecked
                "Sábado" -> Utils.diasSeleccionados [dias.sabado]= isChecked
                "Domingo" ->Utils.diasSeleccionados [dias.domingo]= isChecked
                "Todos" -> {
                   Utils.diasSeleccionados[dias.todos]
                    //Se actualiza los checkbox para cuando se marque o se desmarque Todos.
                    for (i in 1 until layout.childCount) {
                        val checkbox = layout.getChildAt(i) as CheckBox
                        checkbox.isChecked = isChecked
                    }
                }
            }
        }
        for (dia in diasSemana) {
            val checkBox = CheckBox(this)
            checkBox.text = dia
            checkBox.setOnCheckedChangeListener(checkBoxListener)

            // Configurar el estado inicial de los checkboxes
            when (dia) {
                "Lunes" -> checkBox.isChecked = Utils.diasSeleccionados[dias.lunes] ?: false
                "Martes" -> checkBox.isChecked = Utils.diasSeleccionados[dias.martes] ?: false
                "Miércoles" -> checkBox.isChecked = Utils.diasSeleccionados[dias.miercoles] ?: false
                "Jueves" -> checkBox.isChecked = Utils.diasSeleccionados[dias.jueves] ?: false
                "Viernes" -> checkBox.isChecked = Utils.diasSeleccionados[dias.viernes] ?: false
                "Sábado" -> checkBox.isChecked = Utils.diasSeleccionados[dias.sabado] ?: false
                "Domingo" -> checkBox.isChecked = Utils.diasSeleccionados[dias.domingo] ?: false
                "Todos" -> {
                    // Verificar si todos los días están marcados
                    val todosChecked = Utils.diasSeleccionados[dias.lunes]== true && Utils.diasSeleccionados[dias.martes] == true && Utils.diasSeleccionados[dias.miercoles] == true &&
                            Utils.diasSeleccionados[dias.jueves] == true && Utils.diasSeleccionados[dias.viernes] == true && Utils.diasSeleccionados[dias.sabado] == true && Utils.diasSeleccionados[dias.domingo] == true
                    checkBox.isChecked = todosChecked
                }
            }
            layout.addView(checkBox)
        }
        builder.setView(layout)
        val dialog = builder.create()
        dialog.show()
    }
}




