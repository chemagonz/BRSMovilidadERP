package com.advantys.brsmovilidaderp.Domain.UseCases

import com.advantys.brsmovilidaderp.Data.Repositories.Tarifas_Repository
import com.advantys.brsmovilidaderp.Domain.Models.Tarifa
import javax.inject.Inject

class Tarifa_UseCase@Inject constructor(private val repository: Tarifas_Repository) {

    suspend operator fun invoke(tarifas:Short?):Tarifa{
      tarifas?.let {
          val tarifaNombre = repository.getNombreTarifa(tarifas)
          return tarifaNombre?:throw NoSuchElementException("Error")
      }?:throw IllegalArgumentException("no puede ser nulo")
    }
}


