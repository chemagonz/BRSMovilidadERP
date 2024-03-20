package com.advantys.brsmovilidaderp.UI.Views.Clientes

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.advantys.brsmovilidaderp.Domain.Models.Cliente
import com.advantys.brsmovilidaderp.UI.ViewModels.Cliente_ViewModel
import com.advantys.brsmovilidaderp.Utils.BDUtil
import com.advantys.brsmovilidaderp.databinding.ActivityDetallesClientesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetallesClientes_Activity : AppCompatActivity() {
    val clientesViewmodel: Cliente_ViewModel by viewModels()

    private lateinit var binding: ActivityDetallesClientesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityDetallesClientesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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
            setSubtitle("DETALLES")
        }
    }
    private fun verDetallesCliente(cliente: Cliente) {
        val detalles = StringBuilder()
        binding.edCodigoCliente.setText(cliente.numClientes.toString())
        binding.edNIFClientes.setText(if (cliente.NIF!= null)cliente.NIF.toString() else "")
        binding.edNombreClientes.setText(cliente.nombre)
        binding.edRazonSocial.setText(cliente.razonSocial)
        val direccion = cliente?.direccion ?:""  // Si cliente.direccion es nulo, asigna una cadena vacía
        detalles.append(if (direccion.isEmpty()) "\"\"" else direccion).append("\n")
        detalles.append(cliente.provincia).append("\n")
        detalles.append(cliente.codPostal).append("\n")
        detalles.append(cliente.poblacion)
        binding.edCalleCPMunicProvinClientes.setText(detalles.toString())
        binding.edTelefono1Cliente.setText(cliente.telefono1)
        binding.edTelefono1Cliente.setText(cliente.telefono2)

    }
}