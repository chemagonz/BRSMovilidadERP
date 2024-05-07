package com.advantys.brsmovilidaderp.Domain.UseCases

import com.advantys.brsmovilidaderp.Data.Repositories.Promociones_Repository
import com.advantys.brsmovilidaderp.Domain.Models.Promocion
import javax.inject.Inject

class Promociones_UseCase @Inject constructor(private val repository: Promociones_Repository) {

    suspend fun getPromocionesGenerales ():List<Promocion>{
        return repository.getPromocionesGenerales()
    }
}