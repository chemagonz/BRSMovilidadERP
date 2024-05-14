package com.advantys.brsmovilidaderp.UI.Views.Series

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
import com.advantys.brsmovilidaderp.UI.ViewModels.Serie_ViewModel
import com.advantys.brsmovilidaderp.databinding.ActivitySeriesBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Series_Activity : AppCompatActivity() {

     val seriesViewModel: Serie_ViewModel by viewModels()
    private lateinit var adapterSerie: Series_Adapter

    //VARIABLES

    var modoseleccion = false
    var boton = ""
    var centroCliente: Int = 0

    private lateinit var binding: ActivitySeriesBinding
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySeriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val Datos = intent
        modoseleccion = Datos.getBooleanExtra("Seleccion", false)
        centroCliente = -1

        boton =
            if (Datos.getStringArrayExtra("Boton") != null)
                Datos.getStringExtra("Boton")!!
            else "Pedido"

        //ACTION BAR
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "SERIES"
        }
        seriesViewModel.onCreate()
        seriesViewModel.seriesModel.observe(this, Observer{
            binding.seriesRecyclerView.layoutManager= LinearLayoutManager(this)
            binding.seriesRecyclerView.adapter= Series_Adapter(it, seriesViewModel)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.aceptar_cancelar_series, menu)
        val aceptarItem = menu.findItem(R.id.aceptar_serie)
        val cancelarItem = menu.findItem(R.id.cancelar_serie)

        aceptarItem?.setOnMenuItemClickListener {
            val serie = adapterSerie.getElementoSeleccionado()

            if(serie != null) {

                val resultado = Intent()
                resultado.putExtra("Serie", serie.cSeries)
                resultado.putExtra("Boton", boton)
                setResult(RESULT_OK, resultado)
                finish()

            } else {

                setResult(RESULT_CANCELED)
                finish()
            }




            true
        }

        cancelarItem?.setOnMenuItemClickListener {

            true
        }

        return true
    }
    //Manejo de botones del action bar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                //Boton para atras
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}