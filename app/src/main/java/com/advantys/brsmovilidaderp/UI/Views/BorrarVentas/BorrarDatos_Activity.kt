package com.advantys.brsmovilidaderp.UI.Views.BorrarVentas

import android.app.DatePickerDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.BorrarDatos_ViewModel
import com.advantys.brsmovilidaderp.databinding.ActivityBorrarDatosBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.concurrent.Executors


class BorrarDatos_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityBorrarDatosBinding
    val borrarDatosviewModel: BorrarDatos_ViewModel by viewModels()
    private var tablas = arrayOfNulls<Boolean>(6)
    private lateinit var fecha: String
    private lateinit var progressBar: ProgressBar
    private val myExecutor = Executors.newSingleThreadExecutor()
    private val myHandler = Handler(Looper.getMainLooper())
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

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.aceptar_borrar_datos, menu)
        val borrarDatos = menu.findItem(R.id.aceptar_borrarDatos)
        borrarDatos?.setOnMenuItemClickListener {
            var date = Date()
            val dateFormat = SimpleDateFormat("yyyy/MM/dd")
            fecha = dateFormat.format(date)

            if (tablas == null) {
                tablas = arrayOfNulls<Boolean>(6)
            }
            datosBorrar()
//            comprobarDatos().execute(fecha)
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

    private fun datosBorrar() {
        tablas[0] = binding.borrarCobros.isChecked
        tablas[1] = binding.borrarPedidos.isChecked
        tablas[2] = binding.borrarIncidencias.isChecked
        tablas[3] = binding.borrarHojadeCarga.isChecked
    }
//  private fun comprobarDatos(strings: Strings): String {
//      var mensaje= ""
//      myExecutor.execute{
//          val pendientes: IntArray? = borrarDatosviewModel.comprobarDatosPendientes(fecha)
//          if(pendientes!=null){
//              if(pendientes[0]!=0) mensaje+="Ventas: "+pendientes[0]+"\n";
//              if(pendientes[1]!=0) mensaje+="Visitas: "+pendientes[1]+"\n";
//              if(pendientes[2]!=0) mensaje+="Cobros: "+pendientes[2]+"\n";
//          }
//      }
//      return mensaje
//
//      myHandler.post {
//
//      }
//  }

    //MIRAR
    abstract class comprobarDatos<Params,Result>{
    protected open fun onPreExecute() {
        //Mensaje progressbar
    }

    protected fun doInBackground(vararg params: Params): Result?{
        var mensaje= ""
        val pendientes: IntArray?
        return null
    }

    protected open fun onPostExecute(result: Result?) {}

}

}

