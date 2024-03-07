package com.advantys.brsmovilidaderp.Domain.UseCases

import com.advantys.brsmovilidaderp.Data.Repositories.Serie_Repository
import com.advantys.brsmovilidaderp.Domain.Models.Serie

class Serie_UseCase (private val repository:Serie_Repository) {
    suspend operator fun invoke():List<Serie>{
        val serie= repository.getAllSeries()

        return if(serie.isNullOrEmpty())
            listOf<Serie>()
        else serie
    }
}