package com.advantys.brsmovilidaderp.UI.Views.Centros

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.advantys.brsmovilidaderp.Data.DataBase.Daos.Centros_Dao
import com.advantys.brsmovilidaderp.databinding.ActivityDetallesCentroBinding

class DetallesCentro_Activity : AppCompatActivity() {

    //Instancia de Centros_Dao
    private val centros = Centros_Dao(this)
    //Llamada a funciones
    val centrosList= centros.getAllDetalles()
    //BindingView

    lateinit var binding: ActivityDetallesCentroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetallesCentroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        verDetallesCentro()
    }

    //Falta variables de los componentes y sus id
    @SuppressLint("SuspiciousIndentation")
    private fun verDetallesCentro(){

        if(!centrosList.isEmpty()){
            val centro= centrosList.first()
                centro?.let {
                    binding.edNIF.setText(it.NIF)
                    binding.edNombre.setText(it.nombre)
                    binding.edCalleCPMunicProvin.setText(it.direccion)
                    binding.edCalleCPMunicProvin.setText(it.codigoPostal?.toString())
                    binding.edCalleCPMunicProvin.setText(it.poblacion)
                    binding.edCalleCPMunicProvin.setText(it.provincia)
                    binding.edTelefono.setText(it.telefono?.toString())
                    binding.edSerie.setText(it.serie?.toString())
                    it.ventaMenorA?.let { ventaMenorA ->
                        binding.checkVenMenorA.isChecked = ventaMenorA
                    }
                    it.aplicaCargo?.let { aplicaCargo ->
                        binding.checkAplCargo.isChecked = aplicaCargo
                    }
            }
        }
    }
}