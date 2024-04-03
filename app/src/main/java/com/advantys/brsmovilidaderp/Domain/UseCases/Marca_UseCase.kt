package com.advantys.brsmovilidaderp.Domain.UseCases

import com.advantys.brsmovilidaderp.Data.Repositories.Marca_Repository
import com.advantys.brsmovilidaderp.Domain.Models.Marca
import javax.inject.Inject

class Marca_UseCase @Inject constructor (private val repository: Marca_Repository) {
    suspend operator fun invoke(): List<Marca>{
        val marca = repository.getAll()
        return if(marca.isNullOrEmpty())
            listOf<Marca>()
        else marca
    }
}