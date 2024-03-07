package com.advantys.brsmovilidaderp.Domain.UseCases

import com.advantys.brsmovilidaderp.Data.Repositories.Ruta_Repository
import com.advantys.brsmovilidaderp.Domain.Models.Ruta

class Ruta_UseCase(private val repository:Ruta_Repository) {
    suspend operator fun invoke():List<Ruta>{
        val ruta= repository.getAllRutas()
        return if(!ruta.isNullOrEmpty())
            listOf<Ruta>()
        else ruta
    }

}