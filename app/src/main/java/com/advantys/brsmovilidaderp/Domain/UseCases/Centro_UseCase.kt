package com.advantys.brsmovilidaderp.Domain.UseCases

import com.advantys.brsmovilidaderp.Data.Repositories.Centro_Repository
import com.advantys.brsmovilidaderp.Data.Repositories.Serie_Repository
import com.advantys.brsmovilidaderp.Domain.Models.Centro
import com.advantys.brsmovilidaderp.Domain.Models.Serie
import javax.inject.Inject

class Centro_UseCase   @Inject constructor (private val repository: Centro_Repository, private val repositorySerie: Serie_Repository) {
    suspend operator fun invoke(): List<Centro>{
        val centro = repository.getCentros()
        return if(centro.isNullOrEmpty()) listOf<Centro>()
        else centro
    }
    suspend operator fun invoke(centro: Int?): Centro{
        centro?.let {
            val centroDet = repository.getCentro(centro)
            return centroDet ?: throw NoSuchElementException("Error")
        }?:throw IllegalArgumentException("no puede ser nulo")
    }
    suspend operator fun invoke(serie:String?): Serie? {
        serie?.let {
            val nombreSerie = repositorySerie.getSerieNombre(serie)
            return nombreSerie ?: throw NoSuchElementException("Error")
        }?:throw IllegalArgumentException("no puede ser nulo")
    }
}