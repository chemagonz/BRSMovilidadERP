package com.advantys.brsmovilidaderp.UI.Views.Centros

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.advantys.brsmovilidaderp.Domain.Models.Centro
import com.advantys.brsmovilidaderp.UI.ViewModels.Centro_ViewModel
import com.advantys.brsmovilidaderp.Utils.BDUtil
import com.advantys.brsmovilidaderp.databinding.ActivityDetallesCentroBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetallesCentro_Activity (): AppCompatActivity() {
    val centroViewmodel: Centro_ViewModel by viewModels()
    lateinit var binding: ActivityDetallesCentroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesCentroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        BDUtil.KeyboardUtil.esconderTeclado(this)
        val centroC= intent.getIntExtra("numCentro", 0)
        centroViewmodel.onCreateDetalles(centroC)
        centroViewmodel.centroModel.observe(this, Observer{ centro ->
            centro?.let { verDetallesCentro(centro) }
        })
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "CENTROS"
            setSubtitle("DETALLES")
        }
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
    //Funcion para ver los detalles de cada centro, stringbuilder para editText multiline
    private fun verDetallesCentro(centro: Centro) {
        val detalles = StringBuilder()

        binding.edCodigoCentro.setText(centro.numCentro.toString())
        binding.edNIFCentros.setText(centro.nif)
        binding.edNombreCentro.setText(centro.nombre)
        detalles.append(centro.direccion).append("\n")
        detalles.append(centro.codigoPostal).append("\n")
        detalles.append(centro.poblacion).append("\n")
        detalles.append(centro.provincia)
        binding.edCalleCPMunicProvin.setText(detalles.toString())
        binding.edTelefonoCentro.setText(centro.telefono.toString())
        binding.edSerieCentro.setText(centro.serie)
        //detalles.append(centroViewmodel.onCreateSerie(centro.serie))
        //binding.edSerieCentro.setText(detalles)
        binding.edVentaMenorA.setText(centro.ventaMenorA.toString())
        binding.checkAplicaCargo.isChecked = centro.aplicaCargo
    }
}