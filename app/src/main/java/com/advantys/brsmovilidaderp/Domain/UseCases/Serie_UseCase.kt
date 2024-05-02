package com.advantys.brsmovilidaderp.Domain.UseCases

import com.advantys.brsmovilidaderp.Data.Repositories.Serie_Repository
import com.advantys.brsmovilidaderp.Domain.Models.Serie
import javax.inject.Inject

class Serie_UseCase @Inject constructor(private val repository:Serie_Repository) {
    suspend operator fun invoke():List<Serie>{
        val serie= repository.getAllSeries()

        return if(serie.isNullOrEmpty())
            listOf<Serie>()
        else serie
    }
    suspend operator fun invoke(serie:String?): Serie {
        serie?.let {
            val serieDet= repository.getAllDetalles(serie)
            return serieDet ?: throw NoSuchElementException("Error")
        }?:throw IllegalArgumentException("no puede ser nulo")
    }

    suspend  fun getNombreSerie(serie:String?): Serie? {
            return repository.getSerieNombre(serie)
    }
}