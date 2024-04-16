package com.advantys.brsmovilidaderp.UI.Views.BorrarVentas

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.BorrarDatos_ViewModel
import com.advantys.brsmovilidaderp.databinding.ActivityBorrarDatosBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import java.text.SimpleDateFormat
import java.util.Date

@AndroidEntryPoint
class BorrarDatos_Activity : AppCompatActivity() , CoroutineScope by MainScope(){
    private lateinit var binding: ActivityBorrarDatosBinding
    val borrarDatosviewModel: BorrarDatos_ViewModel by viewModels()
    private lateinit var fecha: String

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBorrarDatosBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            title = "BORRAR DATOS"
        }
        binding.edborrarPedidos.setOnClickListener {
            datePicker()
        }
        binding.borrarpreventaIDCompactar.setOnClickListener {
            borrarDatosviewModel.compactarBaseDeDatos(this@BorrarDatos_Activity)
        }
    }
    @SuppressLint("SimpleDateFormat")
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.aceptar_borrar_datos, menu)
        val borrarDatos = menu.findItem(R.id.aceptar_borrarDatos)
        borrarDatos?.setOnMenuItemClickListener {
            var date = Date()
            val dateFormat = SimpleDateFormat("yyyy/MM/dd")
            fecha = dateFormat.format(date)
            borrarDatosviewModel.comprobarDatosCorrutina(this@BorrarDatos_Activity,fecha,binding.borrarPedidos.isChecked,binding.borrarCobros.isChecked,binding.borrarHojadeCarga.isChecked,binding.borrarIncidencias.isChecked)
            true
        }
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun datePicker() {
        val year = 2024
        val month = 0
        val day = 1

        val datePickerDialog = DatePickerDialog(
            this,
            { view, year1, monthOfYear, dayOfMonth ->
                val dateChoice = (dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year1)
                binding.edborrarPedidos.setText(dateChoice)
                //temp = dateChoice
            }, year, month, day
        )
        datePickerDialog.show()
    }
}

