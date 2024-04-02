package com.advantys.brsmovilidaderp.Domain.UseCases

import com.advantys.brsmovilidaderp.Data.Repositories.TiposIVA_Repository
import com.advantys.brsmovilidaderp.Domain.Models.TipoIVA
import javax.inject.Inject

class TipoIVA_UseCase @Inject constructor(private val repository: TiposIVA_Repository) {
    suspend fun getIVA(IVA: Short?):TipoIVA{
        IVA?.let {
            val ivaDet= repository.getIVA(IVA)
            return ivaDet?: throw NoSuchElementException("Error")
        }?:throw IllegalArgumentException("no puede ser nulo")
    }
}