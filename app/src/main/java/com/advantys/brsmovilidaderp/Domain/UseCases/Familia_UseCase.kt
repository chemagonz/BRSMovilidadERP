package com.advantys.brsmovilidaderp.Domain.UseCases

import com.advantys.brsmovilidaderp.Data.Repositories.Familias_Repository
import com.advantys.brsmovilidaderp.Domain.Models.Familia
import javax.inject.Inject

class Familia_UseCase @Inject constructor (private val repository: Familias_Repository) {
    suspend operator fun invoke(): List<Familia>{
        val familia = repository.getAll()
        return if(familia.isNullOrEmpty())
            listOf<Familia>()
        else familia
    }
}