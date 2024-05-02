package com.advantys.brsmovilidaderp.Domain.UseCases

import com.advantys.brsmovilidaderp.Data.Repositories.MultiClientes_Repository
import com.advantys.brsmovilidaderp.Domain.Models.MultiCliente
import javax.inject.Inject

class MultiCliente_UseCase @Inject constructor(private val repository: MultiClientes_Repository) {

    suspend operator fun invoke():List<MultiCliente>{
        val multicliente= repository.getAllmultiClientes()
        return if(multicliente.isNullOrEmpty()){
            listOf<MultiCliente>()
        }else multicliente
    }

    suspend operator fun invoke(multicliente:Int?): MultiCliente? {
          return repository.getDetallesMulticlientes(multicliente)
    }

    suspend  fun codigoFabricante(multiClienteFab: Short?): MultiCliente? {
        return repository.codigoFabricante(multiClienteFab)
    }

}