package com.advantys.brsmovilidaderp.Domain.UseCases

import com.advantys.brsmovilidaderp.Data.Repositories.TipoOperacion_Repository
import com.advantys.brsmovilidaderp.Domain.Models.TipoOperacion
import javax.inject.Inject

class TipoOperacion_UseCase @Inject constructor(private val repository: TipoOperacion_Repository)
{
    suspend operator fun invoke(): List<TipoOperacion>{
        val tipoOp = repository.getAllTipos()
        return if (tipoOp.isNullOrEmpty())
            listOf<TipoOperacion>()
        else tipoOp
    }

    suspend operator fun invoke(operacion:Short?): TipoOperacion {
        operacion?.let {
            val operacionNombre = repository.getNombreOperacion(operacion)
            return operacionNombre?:throw NoSuchElementException("Error")
        }?:throw IllegalArgumentException("no puede ser nulo")
    }
}