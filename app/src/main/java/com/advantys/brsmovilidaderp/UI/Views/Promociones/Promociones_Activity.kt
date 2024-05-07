package com.advantys.brsmovilidaderp.UI.Views.Promociones

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.PromocionesClientes_ViewModel
import com.advantys.brsmovilidaderp.UI.ViewModels.Promociones_ViewModel
import com.advantys.brsmovilidaderp.databinding.ActivityPromocionesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Promociones_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityPromocionesBinding
    private val promocionesViewmodel: Promociones_ViewModel by viewModels()
    private val promocionesParViewmodel: PromocionesClientes_ViewModel by viewModels()
    private lateinit var adapterPromociones: Promociones_Adapter
    private lateinit var adapterPromocionesPar: PromocionesParticulares_Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPromocionesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            title = "PROMOCIONES"
        }

        mostrarPromocionesGeneralesVista()
        mostrarPromocionesGenerales()
        mostrarPromocionesParticulares()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.aceptar_promociones, menu)
        val aceptarPromociones = menu.findItem(R.id.aceptar_promociones)
        aceptarPromociones?.setOnMenuItemClickListener {

            true
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
             finish()
             return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun mostrarPromocionesGeneralesVista(){
        promocionesViewmodel.getPromocionesGenerales()
        promocionesViewmodel.promocionesModel.observe(this, Observer {
            adapterPromociones =  Promociones_Adapter(it, promocionesViewmodel)
            binding.recyclerViewPromociones.layoutManager = LinearLayoutManager(this)
            binding.recyclerViewPromociones.adapter = adapterPromociones

        })
    }
    fun mostrarPromocionesGenerales(){
        binding.btnPromocionesGenerales.setOnClickListener {
            promocionesViewmodel.getPromocionesGenerales()
            promocionesViewmodel.promocionesModel.observe(this, Observer {
                adapterPromociones =  Promociones_Adapter(it, promocionesViewmodel)
                binding.recyclerViewPromociones.layoutManager = LinearLayoutManager(this)
                binding.recyclerViewPromociones.adapter = adapterPromociones

            })
        }
    }
    fun mostrarPromocionesParticulares(){
        binding.btnPromocionesParticulares.setOnClickListener {
            promocionesParViewmodel.getPromocionesParticulares()
            promocionesParViewmodel.promocionesParModel.observe(this, Observer {
                adapterPromocionesPar =  PromocionesParticulares_Adapter(it, promocionesParViewmodel)
                binding.recyclerViewPromociones.layoutManager = LinearLayoutManager(this)
                binding.recyclerViewPromociones.adapter = adapterPromocionesPar

            })
        }
    }
}