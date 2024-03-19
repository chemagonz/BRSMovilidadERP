package com.advantys.brsmovilidaderp.Domain.UseCases

import com.advantys.brsmovilidaderp.Data.DataBase.Daos.columnas
import com.advantys.brsmovilidaderp.Data.DataBase.Daos.ordenarPor
import com.advantys.brsmovilidaderp.Data.Repositories.Clientes_Repository
import com.advantys.brsmovilidaderp.Domain.Models.Cliente
import javax.inject.Inject

class Cliente_UseCase @Inject constructor(private val repository: Clientes_Repository) {

   //Funcion para obtener todos los clientes
    suspend operator fun invoke():List<Cliente>{
        val cliente= repository.getAllClientes()
        return if(cliente.isNullOrEmpty()){
            listOf<Cliente>()
        }else cliente
    }
    //Funcion para filtrar clientes
    suspend operator fun invoke(tipo:columnas, query:String):List<Cliente>{
        return repository.getFilter(tipo,query)
    }
    //Funcion para ordenar clientes segun la elección.(ruta,secuencia, nombre,cliente, ordenpersonalizado...)
    suspend operator  fun invoke(ordenar: ordenarPor):List<Cliente>{
        return repository.obtenerConsultaCliente(ordenar)
    }
}