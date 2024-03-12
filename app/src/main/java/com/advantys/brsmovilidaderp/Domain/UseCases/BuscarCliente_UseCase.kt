package com.advantys.brsmovilidaderp.Domain.UseCases

import com.advantys.brsmovilidaderp.Data.Repositories.BuscarClientes_Repository
import com.advantys.brsmovilidaderp.Domain.Models.Cliente

class BuscarCliente_UseCase(private val repository: BuscarClientes_Repository) {

    suspend operator fun invoke():List<Cliente>{
        val cliente= repository.getAllClientes()
        return if(cliente.isNullOrEmpty()){
            listOf<Cliente>()
        }else cliente
    }

    suspend operator fun invoke(tipo:String, query:String):List<Cliente>{
        return when (tipo){
            "Nombre" ->repository.getClientesNombre(query)
            "Codigo" -> repository.getClientesCodigo(query)
            else-> emptyList()
        }.takeIf { it.isNotEmpty() } ?: emptyList()
    }
}