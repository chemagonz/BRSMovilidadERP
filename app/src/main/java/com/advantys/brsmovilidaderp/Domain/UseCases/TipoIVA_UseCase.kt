package com.advantys.brsmovilidaderp.Domain.UseCases

import com.advantys.brsmovilidaderp.Data.Repositories.TiposIVA_Repository
import com.advantys.brsmovilidaderp.Domain.Models.TipoIVA
import javax.inject.Inject

class TipoIVA_UseCase @Inject constructor(private val repository: TiposIVA_Repository) {
    suspend fun getIVA(IVA: Short?):TipoIVA?{
           return repository.getIVA(IVA)
    }
}