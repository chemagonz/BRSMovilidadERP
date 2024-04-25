package com.advantys.brsmovilidaderp.UI.Views.BorrarVentas

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.BorrarDatos_ViewModel
import com.advantys.brsmovilidaderp.Utils.showProgressDialog
import com.advantys.brsmovilidaderp.databinding.ActivityBorrarDatosBinding
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

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

        obtenerFechaActual()

        binding.edborrarPedidos.setOnClickListener {
           selectorDeFecha()
        }

        binding.borrarpreventaIDCompactar.setOnClickListener {
            borrarDatosviewModel.compactarBaseDeDatos(this@BorrarDatos_Activity)
        }

        borrarDatosviewModel.respuestaDialogo.observe(this,  Observer { respuesta ->
            showProgressDialog(respuesta.mensaje,1000)
        })

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
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun selectorDeFecha() {
        val defaultLocale = Locale.getDefault()

        val spanishLocale = Locale("es", "ES")
        Locale.setDefault(spanishLocale)

        val builder = MaterialDatePicker.Builder.datePicker()
            .setInputMode(MaterialDatePicker.INPUT_MODE_TEXT)
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .setTitleText("Selecciona una fecha")
        val picker = builder.build()

        picker.addOnPositiveButtonClickListener { dateInMillis ->
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = dateInMillis

            val anio = calendar.get(Calendar.YEAR)
            val mes = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, spanishLocale)
            val dia = calendar.get(Calendar.DAY_OF_MONTH)

            val eleccionDeFecha = "${dia}/$mes/$anio"
            val textoFinal = "$eleccionDeFecha"
            binding.edborrarPedidos.setText(textoFinal)

            Locale.setDefault(defaultLocale)
        }
        picker.show(supportFragmentManager, picker.toString())
    }
    fun obtenerFechaActual(){
        val spanishLocale = Locale("es", "ES")
        Locale.setDefault(spanishLocale)
        // Obtener la fecha actual
        val fechaActual = Calendar.getInstance()
        val anioActual = fechaActual.get(Calendar.YEAR)
        val mesActual = fechaActual.getDisplayName(Calendar.MONTH, Calendar.LONG, spanishLocale)
        val diaActual = fechaActual.get(Calendar.DAY_OF_MONTH)
        // Establecer el texto predeterminado en el EditText
        val textoPredeterminado = "$diaActual de $mesActual de $anioActual"
        binding.edborrarPedidos.setText(textoPredeterminado)
    }
}

