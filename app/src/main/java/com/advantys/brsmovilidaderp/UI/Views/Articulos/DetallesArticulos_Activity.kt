package com.advantys.brsmovilidaderp.UI.Views.Articulos

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.advantys.brsmovilidaderp.Domain.Models.Articulo
import com.advantys.brsmovilidaderp.UI.ViewModels.Articulo_ViewModel
import com.advantys.brsmovilidaderp.Utils.BDUtil
import com.advantys.brsmovilidaderp.databinding.ActivityDetallesArticulosBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetallesArticulos_Activity : AppCompatActivity() {
    val articuloViewmodel:Articulo_ViewModel by viewModels()
    private lateinit var binding: ActivityDetallesArticulosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityDetallesArticulosBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title="ARTÍCULOS"
            subtitle="DETALLES"
        }

        BDUtil.KeyboardUtil.esconderTeclado(this)
        val articuloC= intent.getStringExtra("articulo")
        articuloViewmodel.onCreateDetalles(articuloC)
        articuloViewmodel.articuloModel.observe(this, Observer { articulo ->
            articulo?.let { verDetallesArticulo(articulo)}
        })
    }
    //Funcion para manejar botones
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

    private fun verDetallesArticulo(articulo: Articulo) {
        binding.edCodigoArticulo.setText(articulo.articulo)
        binding.edFCarticulo.setText(articulo.udsCaja.toString())
        binding.edNombreArticulo.setText(articulo.nombre)
        binding.edNombreCortoArticulo.setText(articulo.nombreCorto)
        binding.edTipoIVA.setText(articulo.tipoIVA.toString())
        binding.edPrecioCoste.setText(articulo.precoste.toString())
        binding.edUltCompra.setText(articulo.preUltCompra.toString())
        binding.edPuntoVerde.setText(articulo.puntoVerde.toString())
        binding.edAlcohol.setText(articulo.alcohol.toString())
        binding.edManipulacion.setText(articulo.manipulacion.toString())
    }
}