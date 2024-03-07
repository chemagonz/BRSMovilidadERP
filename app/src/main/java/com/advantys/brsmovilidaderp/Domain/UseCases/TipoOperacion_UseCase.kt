package com.advantys.brsmovilidaderp.Domain.UseCases

import com.advantys.brsmovilidaderp.Data.Repositories.TipoOperacion_Repository
import com.advantys.brsmovilidaderp.Domain.Models.tipoOperacion

class TipoOperacion_UseCase (private val repository: TipoOperacion_Repository)
{
    suspend operator fun invoke(): List<tipoOperacion>{
        val tipoOp = repository.getAllTipos()
        return if (tipoOp.isNullOrEmpty())
            listOf<tipoOperacion>()
        else tipoOp
    }
}