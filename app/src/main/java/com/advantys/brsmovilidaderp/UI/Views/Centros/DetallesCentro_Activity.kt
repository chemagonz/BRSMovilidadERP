package com.advantys.brsmovilidaderp.UI.Views.Centros

import Centro_ViewModel
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.advantys.brsmovilidaderp.Data.DataBase.Daos.Centros_Dao
import com.advantys.brsmovilidaderp.Data.Repositories.Centro_Repository
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
        val centro= intent.getIntExtra("numCentro", 0)
//        centroViewmodel.centroModel.observe(this, Observer{
//            centroViewmodel.onCreateDetalles()
//        })

        //verDetallesCentro()
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "CENTROS"
            setSubtitle("DETALLES")
        }
    }
//    private fun verDetallesCentro() {
//        centroViewmodel.onCreateDetalles().let {
//            binding.edCodigoCentro.setText(it?.codigo.toString())
//            binding.edNIFCentros.setText(it?.NIF)
//            binding.edNombreCentro.setText(it?.nombre)
//            binding.edCalleCPMunicProvin.setText(it?.direccion)
//            binding.edCalleCPMunicProvin.setText(it?.codigoPostal?.toString())
//            binding.edCalleCPMunicProvin.setText(it?.poblacion)
//            binding.edCalleCPMunicProvin.setText(it?.provincia)
//            binding.edTelefonoCentro.setText(it?.telefono?.toString())
//            binding.edSerieCentro.setText(it?.serie?.toString())
//            binding.edVentaMenorA.setText(it?.ventaMenorA?.toString())
//            it?.aplicaCargo?.let { aplicaCargo ->
//                binding.checkAplicaCargo.isChecked = aplicaCargo
//            }
//        }
//    }
}