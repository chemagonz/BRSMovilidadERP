package com.advantys.brsmovilidaderp.UI.Views.Configuracion

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.advantys.brsmovilidaderp.databinding.ActivityConfiguracionBinding

class Configuracion_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityConfiguracionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityConfiguracionBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Variables para los expandibles
        val exFechas= binding.expandable
        val exExportacion= binding.expandableExportacionDeFicheros
        val exVentas= binding.expandableVentas
        val exBusqueda= binding.expandableBusquedas
        var gExportacion= 0
        var gFechas= 0
        var gVentas= 0
        var gBusquedas=0

        //Eventos click de los expandibles
        exFechas.setOnClickListener{
            if(gFechas==0){
                gFechas=1
                exFechas.expand()
            }else{
                gFechas=0
                exFechas.collapse()
            }
        }
        exExportacion.setOnClickListener{
            if(gExportacion==0){
                gExportacion=1
                exExportacion.expand()
            }else{
                gExportacion=0
                exExportacion.collapse()
            }
        }
        exVentas.setOnClickListener{
            if(gVentas==0){
                gVentas=1
                exVentas.expand()
            }else{
                gVentas=0
                exVentas.collapse()
            }
        }
        exBusqueda.setOnClickListener{
            if(gBusquedas==0){
                gBusquedas=1
                exBusqueda.expand()
            }else{
                gBusquedas=0
                exBusqueda.collapse()
            }
        }

    }
}