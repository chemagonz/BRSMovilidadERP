package com.advantys.brsmovilidaderp.UI.Views.Series

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.advantys.brsmovilidaderp.Data.DataBase.Daos.Series_Dao
import com.advantys.brsmovilidaderp.databinding.ActivityDetallesSerieBinding

class DetallesSerie_Activity (private val series: Series_Dao) : AppCompatActivity() {

    //private val seriesList= series.getAllDetalles("")
    lateinit var binding: ActivityDetallesSerieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetallesSerieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //ACTION BAR
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "SERIES"
            setSubtitle("DETALLES")
        }


    }

    //Al no ser una lista, da error, corregir
//    private fun verDetallesSeries(){
//        if(!seriesList.isEmpty()){
//            val serie= seriesList.first()
//            serie?.let {
//                binding.edCodigoSerie.setText(it.cSeries.toString())
//                binding.edAplicaIVA.setText(it.aplicaIva)
//                binding.edNombreSerie.setText(it.nombre)
//                binding.edUltPedido.setText(it.ultPedido.toString())
//                binding.edUltAlbaran.setText(it.ultAlbaran.toString())
//                binding.edUltFactura.setText(it.ultFactura.toString())
//                binding.edCentroSerie.setText(it.centro.toString())
//                binding.edArtServicioSerie.setText(it.artServicio.toString())
//                binding.edArtServicioSerie.setText(it.fabServicio.toString())
//            }
//        }
//    }
}