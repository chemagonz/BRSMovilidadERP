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
import com.advantys.brsmovilidaderp.Utils.Dias
import com.advantys.brsmovilidaderp.Utils.diasSeleccionados
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
                setResult(RESULT_OK)
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
        builder.setTitle("Seleccione los días de visita")
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(0, 32, 0, 0)

        //CompounButton sirve para detectar cambios en los checkbox, segun el estado del checkbox se actualizara en UTILS
        val checkBoxListener = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            val dia = buttonView.text.toString()
            when (dia) {

                "Lunes" -> {
                    diasSeleccionados[Dias.lunes] = isChecked
                }
                "Martes" -> {
                    diasSeleccionados[Dias.martes] = isChecked
                }
                "Miércoles" -> {
                    diasSeleccionados [Dias.miercoles]= isChecked
                }
                "Jueves" -> {
                    diasSeleccionados [Dias.jueves]= isChecked
                }
                "Viernes" ->{
                    diasSeleccionados [Dias.viernes]= isChecked
                }
                "Sábado" -> {
                    diasSeleccionados [Dias.sabado]= isChecked
                }
                "Domingo" ->{
                    diasSeleccionados [Dias.domingo]= isChecked
                }
                "Todos" -> {
                   diasSeleccionados[Dias.todos]
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
                "Lunes" -> checkBox.isChecked = diasSeleccionados[Dias.lunes] ?: false
                "Martes" -> checkBox.isChecked = diasSeleccionados[Dias.martes] ?: false
                "Miércoles" -> checkBox.isChecked = diasSeleccionados[Dias.miercoles] ?: false
                "Jueves" -> checkBox.isChecked = diasSeleccionados[Dias.jueves] ?: false
                "Viernes" -> checkBox.isChecked = diasSeleccionados[Dias.viernes] ?: false
                "Sábado" -> checkBox.isChecked = diasSeleccionados[Dias.sabado] ?: false
                "Domingo" -> checkBox.isChecked = diasSeleccionados[Dias.domingo] ?: false
                "Todos" -> {
                    // Verificar si todos los días están marcados
                    val todosChecked = diasSeleccionados[Dias.lunes]== true && diasSeleccionados[Dias.martes] == true && diasSeleccionados[Dias.miercoles] == true &&
                            diasSeleccionados[Dias.jueves] == true && diasSeleccionados[Dias.viernes] == true && diasSeleccionados[Dias.sabado] == true && diasSeleccionados[Dias.domingo] == true
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




