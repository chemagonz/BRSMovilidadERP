package com.advantys.brsmovilidaderp.UI.Views.Series

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.advantys.brsmovilidaderp.Data.DataBase.Daos.Series_Dao
import com.advantys.brsmovilidaderp.Data.Repositories.Serie_Repository
import com.advantys.brsmovilidaderp.Domain.Models.Serie
import com.advantys.brsmovilidaderp.Domain.UseCases.Serie_UseCase
import com.advantys.brsmovilidaderp.UI.ViewModels.Serie_ViewModel
import com.advantys.brsmovilidaderp.databinding.ActivityDetallesSerieBinding

class DetallesSerie_Activity () : AppCompatActivity() {

    private val series= Series_Dao(this)
    private val seriesR= Serie_Repository(series)
    private val serieUse= Serie_UseCase(seriesR)
    private val serieViewModel = Serie_ViewModel(serieUse)
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
        //ACTION BAR
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "SERIES"
            setSubtitle("DETALLES")
        }
    }
    fun verDetallesSeries(serie: Serie){
        val detalles= StringBuilder()
        binding.edCodigoSerie.setText(serie.cSeries)
        binding.edAplicaIVA.setText(serie.aplicaIva)
        binding.edNombreSerie.setText(serie.nombre)
        binding.edUltPedido.setText(serie.ultPedido.toString())
        binding.edUltAlbaran.setText(serie.ultAlbaran.toString())
        binding.edUltFactura.setText(serie.ultFactura.toString())
        binding.edCentroSerie.setText(serie.centro.toString())
        detalles.append(serie.artServicio).append("\n")
        detalles.append(serie.fabServicio)
        binding.edArtServicioSerie.setText(detalles.toString())
    }
}