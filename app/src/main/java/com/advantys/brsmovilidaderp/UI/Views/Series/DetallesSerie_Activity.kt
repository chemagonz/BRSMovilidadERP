package com.advantys.brsmovilidaderp.UI.Views.Series

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.advantys.brsmovilidaderp.Domain.Models.Serie
import com.advantys.brsmovilidaderp.UI.ViewModels.Serie_ViewModel
import com.advantys.brsmovilidaderp.Utils.BDUtil
import com.advantys.brsmovilidaderp.databinding.ActivityDetallesSerieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetallesSerie_Activity () : AppCompatActivity() {
    private val serieViewModel : Serie_ViewModel by viewModels()
    lateinit var binding: ActivityDetallesSerieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetallesSerieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val serieD= intent.getStringExtra("cSeries")
        serieViewModel.onCreateDetalles(serieD)
        serieViewModel.serieModel.observe(this, Observer { serie->
            serie?.let {
                verDetallesSeries(serie)
            }

        })
        BDUtil.KeyboardUtil.esconderTeclado(this)
        //ACTION BAR
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "SERIES"
            setSubtitle("DETALLES")
        }
    }
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
    fun verDetallesSeries(serie: Serie){
        val detalles= StringBuilder()
        binding.edCodigoSerie.setText( if(serie.cSeries !="0") serie.cSeries else "")
        binding.edAplicaIVA.setText(if (serie.aplicaIva != "0") serie.aplicaIva else "")
        binding.edNombreSerie.setText(serie.nombre)
        binding.edUltPedido.setText(if (serie.ultPedido != 0) serie.ultPedido.toString() else "")
        binding.edUltAlbaran.setText(if (serie.ultAlbaran != 0) serie.ultAlbaran.toString() else "")
        binding.edUltFactura.setText(if (serie.ultFactura != 0) serie.ultFactura.toString() else "")
        binding.edCentroSerie.setText(if (serie.centro != 0) serie.centro.toString() else "")
        detalles.append(serie.artServicio).append("\n")
        detalles.append(serie.fabServicio)
        binding.edArtServicioSerie.setText(detalles.toString())
    }
}