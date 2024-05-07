package com.advantys.brsmovilidaderp.UI.Views.Clientes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.advantys.brsmovilidaderp.Domain.Models.Cliente
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.Cliente_ViewModel
import com.advantys.brsmovilidaderp.UI.Views.BuscarClientes.BuscarCliente_Activity
import com.advantys.brsmovilidaderp.UI.Views.Promociones.Promociones_Activity
import com.advantys.brsmovilidaderp.Utils.BDUtil
import com.advantys.brsmovilidaderp.Utils.TipoPromocion
import com.advantys.brsmovilidaderp.databinding.ActivityDetallesClientesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetallesClientes_Activity : AppCompatActivity() {
    val clientesViewmodel: Cliente_ViewModel by viewModels()
    private lateinit var binding: ActivityDetallesClientesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityDetallesClientesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        BDUtil.KeyboardUtil.esconderTeclado(this)
        val clienteDet= intent.getIntExtra("numClientes", 0)

        clientesViewmodel.onCreateDetalles(clienteDet)
        clientesViewmodel.clienteModel.observe(this, Observer{ cliente ->
            cliente?.let { verDetallesCliente(cliente) }
        })
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "CLIENTES"
            subtitle= "DETALLES"
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.ir_promociones, menu)
        val irPromociones = menu.findItem(R.id.irPromociones)
        irPromociones?.setOnMenuItemClickListener {

            clientesViewmodel.clienteModel.value?.let { cliente ->
                val intent = Intent(this, Promociones_Activity::class.java)
                intent.putExtra("Tipo", TipoPromocion.particular);
                intent.putExtra("cliente_codigo", cliente.numClientes)
                intent.putExtra("cliente_delegacion", cliente.delegacion)
                startActivity(intent)
                finish()
                true
            } ?: run {

                false
            }
        }
        return true
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
    private fun verDetallesCliente(cliente: Cliente) {
        val detalles = StringBuilder()
        binding.edCodigoCliente.setText(cliente.numClientes.toString())
        binding.edNIFClientes.setText(if (cliente.NIF!= null)cliente.NIF.toString() else "")
        binding.edNombreClientes.setText(cliente.nombre)
        binding.edRazonSocial.setText(cliente.razonSocial)
        val direccion = cliente?.direccion // Si cliente.direccion es nulo, asigna una cadena vacía
        if (direccion != null) {
            detalles.append(if (direccion.isEmpty()) "" else direccion).append(",\n")
        }
        val codpostal= cliente.codPostal
        if(codpostal!=null){
            detalles.append(if (codpostal.isEmpty()) "" else codpostal).append(",\n")
        }
        val poblacion= cliente.poblacion
        if(poblacion!=null){
            detalles.append(if (poblacion.isEmpty()) "" else poblacion).append(",\n")
        }
        val provincia= cliente.provincia
        if(provincia!=null){
            detalles.append(if (provincia.isEmpty()) "" else provincia).append(",\n")
        }

        binding.edCalleCPMunicProvinClientes.setText(detalles.toString())
        binding.edTelefono1Cliente.setText(cliente.telefono1)
        binding.edTelefono1Cliente.setText(cliente.telefono2)
    }
}