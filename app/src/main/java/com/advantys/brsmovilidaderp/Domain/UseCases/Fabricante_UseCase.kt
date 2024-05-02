package com.advantys.brsmovilidaderp.Domain.UseCases

import com.advantys.brsmovilidaderp.Data.Repositories.Fabricantes_Repository
import com.advantys.brsmovilidaderp.Domain.Models.Fabricante
import javax.inject.Inject

class Fabricante_UseCase  @Inject constructor(private val repository: Fabricantes_Repository) {

    suspend fun getNombreFabricante(fabricante: Short?): Fabricante? {
            return repository.getNombreFabricante(fabricante)
    }
}