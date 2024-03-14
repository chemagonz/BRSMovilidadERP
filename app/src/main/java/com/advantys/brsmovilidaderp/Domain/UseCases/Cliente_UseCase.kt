package com.advantys.brsmovilidaderp.Domain.UseCases

import com.advantys.brsmovilidaderp.Data.DataBase.Daos.columnas
import com.advantys.brsmovilidaderp.Data.DataBase.Daos.diasSemana
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

    suspend operator  fun invoke(dias:diasSemana, ordenar: ordenarPor, marcado:Boolean, desmarcado:Boolean):List<Cliente>{
        return repository.obtenerConsultaCliente(dias,ordenar,marcado,desmarcado)
    }
}