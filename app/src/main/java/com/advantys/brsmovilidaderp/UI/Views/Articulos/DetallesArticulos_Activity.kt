package com.advantys.brsmovilidaderp.UI.Views.Articulos

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.advantys.brsmovilidaderp.Domain.Models.Articulo
import com.advantys.brsmovilidaderp.Domain.Models.TipoIVA
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.Articulo_ViewModel
import com.advantys.brsmovilidaderp.UI.ViewModels.TarifaArticulo_ViewModel
import com.advantys.brsmovilidaderp.UI.ViewModels.Tarifa_ViewModel
import com.advantys.brsmovilidaderp.UI.ViewModels.TipoIVA_ViewModel
import com.advantys.brsmovilidaderp.Utils.BDUtil.KeyboardUtil.esconderTeclado
import com.advantys.brsmovilidaderp.databinding.ActivityDetallesArticulosBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetallesArticulos_Activity : AppCompatActivity() {

    val articuloViewmodel:Articulo_ViewModel by viewModels()
    val tipoIvaViewModel: TipoIVA_ViewModel by viewModels()
    val tarifaArticuloViewmodel: TarifaArticulo_ViewModel by viewModels()
    val tarifaViewmodel: Tarifa_ViewModel by viewModels()

    private lateinit var binding: ActivityDetallesArticulosBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityDetallesArticulosBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        esconderTeclado(this)
        appbar()
        mostrarDetalles()
        mostrarDetallesIVA()
        mostrarTarifas()

//        tarifaViewmodel.onCreate(articuloC,articuloFab)
//        tarifaViewmodel.tarifasModel.observe(this, Observer {
//            binding.recyclerViewTarifaArticulos.layoutManager= LinearLayoutManager(this)
//            binding.recyclerViewTarifaArticulos.adapter= Tarifas_Adapter(it, tarifaViewmodel)
//        })
    }

    //Funcion para manejar botones
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun verDetallesArticulo(articulo: Articulo){
        val detalles= StringBuilder()

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

        val articuloRet = articulo.articuloRet
        if(articuloRet != null){
            detalles.append(if (articuloRet.isEmpty()) "" else "$articuloRet , ")
        }

        detalles.append(articulo.fabricanteRet)
        binding.edEnvase.setText(detalles.toString())
        binding.edStockCP.setText(articulo.disponible1.toString())
        binding.edStockUK.setText(articulo.disponible2.toString())
        binding.imageArticulo.setImageResource(R.mipmap.imagen_prueba_foreground)
    }
    private fun verDetallesTipoIVA(tipoIVA: TipoIVA) {
        binding.edTipoIVA.setText(tipoIVA.porcIVA.toString())
        binding.edTipoIVAREC.setText(tipoIVA.porCREC.toString())
    }

    private fun appbar(){
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title="ARTÍCULOS"
            subtitle="DETALLES"
        }
    }

    private fun mostrarDetalles() {
        val articuloC= intent.getStringExtra("articulo")
        //val articuloFab= intent.getShortExtra("fabricante", 0)

        articuloViewmodel.obtenerDetalles(articuloC)
        articuloViewmodel.articuloModel.observe(this, Observer { articulo ->
            articulo?.let { verDetallesArticulo(articulo)}
            tipoIvaViewModel.onCreateGetIVA(articulo?.tipoIVA)
        })
    }

    private fun mostrarDetallesIVA() {
        tipoIvaViewModel.tipoIVAModel.observe(this, Observer { tipoIVA ->
            tipoIVA?.let { verDetallesTipoIVA(tipoIVA) }
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun mostrarTarifas() {
        val articuloC= intent.getStringExtra("articulo")
        //val articuloFab= intent.getShortExtra("fabricante", 0)

        tarifaArticuloViewmodel.onCreate(articuloC)
        tarifaArticuloViewmodel.tarifasArticuloModel.observe(this, Observer {
            binding.recyclerViewTarifaArticulos.layoutManager= LinearLayoutManager(this)
            binding.recyclerViewTarifaArticulos.adapter = TarifasArticulo_Adapter(it, tarifaArticuloViewmodel)
        })
    }
}