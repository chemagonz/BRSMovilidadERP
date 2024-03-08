package com.advantys.brsmovilidaderp.Domain.UseCases

import com.advantys.brsmovilidaderp.Data.Repositories.Centro_Repository
import com.advantys.brsmovilidaderp.Domain.Models.Centro

class Centro_UseCase (private val repository: Centro_Repository) {
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
}