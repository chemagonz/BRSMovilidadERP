package com.advantys.brsmovilidaderp.UI.Views.Centros

import Centro_ViewModel
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.advantys.brsmovilidaderp.Data.DataBase.Daos.Centros_Dao
import com.advantys.brsmovilidaderp.Data.Repositories.Centro_Repository
import com.advantys.brsmovilidaderp.Domain.Models.Centro
import com.advantys.brsmovilidaderp.Domain.UseCases.Centro_UseCase
import com.advantys.brsmovilidaderp.databinding.ActivityDetallesCentroBinding


class DetallesCentro_Activity (): AppCompatActivity() {
    private val centros = Centros_Dao(this)
    private val centrosR= Centro_Repository(centros)
    private val centroUse= Centro_UseCase(centrosR)
    private var centroViewmodel= Centro_ViewModel(centroUse)

    //BindingView
    lateinit var binding: ActivityDetallesCentroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesCentroBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
                val intent = Intent(this, Centros_Activity::class.java)
                startActivity(intent)
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
        binding.edVentaMenorA.setText(centro.ventaMenorA.toString())
        binding.checkAplicaCargo.isChecked = centro.aplicaCargo
    }
}