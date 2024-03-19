package com.advantys.brsmovilidaderp.Domain.UseCases

import com.advantys.brsmovilidaderp.Data.Repositories.Centro_Repository
import com.advantys.brsmovilidaderp.Domain.Models.Centro
import com.advantys.brsmovilidaderp.Domain.Models.Serie
import javax.inject.Inject

class Centro_UseCase   @Inject constructor (private val repository: Centro_Repository) {
    suspend operator fun invoke(): List<Centro>{
        val centro = repository.getAllCentros()
        return if(centro.isNullOrEmpty())
            listOf<Centro>()
        else centro
    }
    suspend operator fun invoke(centro:Int?):Centro{
        centro?.let {
            val centroDet= repository.getAllDetalles(centro)
            return centroDet ?: throw NoSuchElementException("Error")
        }?:throw IllegalArgumentException("no puede ser nulo")
    }
    suspend operator fun invoke(codigo:String?): Serie {
        codigo?.let {
            val nombreSerie = repository.getSerieNombre(codigo)
            return nombreSerie ?: throw NoSuchElementException("Error")
        }?:throw IllegalArgumentException("no puede ser nulo")
    }
}