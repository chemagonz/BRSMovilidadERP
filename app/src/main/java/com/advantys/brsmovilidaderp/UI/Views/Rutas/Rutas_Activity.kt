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
import com.advantys.brsmovilidaderp.Utils.EnumUtil.Dias
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
                    Utils.diasSeleccionados[Dias.lunes] = isChecked
                }
                "Martes" -> {
                    Utils.diasSeleccionados[Dias.martes] = isChecked
                }
                "Miércoles" -> {
                    Utils.diasSeleccionados [Dias.miercoles]= isChecked
                }
                "Jueves" -> {
                    Utils.diasSeleccionados [Dias.jueves]= isChecked
                }
                "Viernes" ->{
                    Utils.diasSeleccionados [Dias.viernes]= isChecked
                }
                "Sábado" -> {
                    Utils.diasSeleccionados [Dias.sabado]= isChecked
                }
                "Domingo" ->{
                    Utils.diasSeleccionados [Dias.domingo]= isChecked
                }
                "Todos" -> {
                   Utils.diasSeleccionados[Dias.todos]
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
                "Lunes" -> checkBox.isChecked = Utils.diasSeleccionados[Dias.lunes] ?: false
                "Martes" -> checkBox.isChecked = Utils.diasSeleccionados[Dias.martes] ?: false
                "Miércoles" -> checkBox.isChecked = Utils.diasSeleccionados[Dias.miercoles] ?: false
                "Jueves" -> checkBox.isChecked = Utils.diasSeleccionados[Dias.jueves] ?: false
                "Viernes" -> checkBox.isChecked = Utils.diasSeleccionados[Dias.viernes] ?: false
                "Sábado" -> checkBox.isChecked = Utils.diasSeleccionados[Dias.sabado] ?: false
                "Domingo" -> checkBox.isChecked = Utils.diasSeleccionados[Dias.domingo] ?: false
                "Todos" -> {
                    // Verificar si todos los días están marcados
                    val todosChecked = Utils.diasSeleccionados[Dias.lunes]== true && Utils.diasSeleccionados[Dias.martes] == true && Utils.diasSeleccionados[Dias.miercoles] == true &&
                            Utils.diasSeleccionados[Dias.jueves] == true && Utils.diasSeleccionados[Dias.viernes] == true && Utils.diasSeleccionados[Dias.sabado] == true && Utils.diasSeleccionados[Dias.domingo] == true
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




