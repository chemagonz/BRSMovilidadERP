package com.advantys.brsmovilidaderp.Domain.UseCases

import com.advantys.brsmovilidaderp.Data.Repositories.Tarifas_Repository
import com.advantys.brsmovilidaderp.Domain.Models.Tarifa
import javax.inject.Inject

class Tarifa_UseCase@Inject constructor(private val repository: Tarifas_Repository) {

    suspend operator fun invoke(tarifas:String?): List<Tarifa>{
        val tarifa = repository.getNombreTarifa(tarifas)
        return if(tarifa.isNullOrEmpty())
            listOf<Tarifa>()
        else tarifa
    }
}