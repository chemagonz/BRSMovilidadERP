package com.advantys.brsmovilidaderp.Domain.UseCases

import com.advantys.brsmovilidaderp.Data.Repositories.Sabor_Repository
import com.advantys.brsmovilidaderp.Domain.Models.Sabor
import javax.inject.Inject

class Sabor_UseCase@Inject constructor (private val repository: Sabor_Repository) {
    suspend operator fun invoke(): List<Sabor>{
        val sabor = repository.getAll()
        return if(sabor.isNullOrEmpty())
            listOf<Sabor>()
        else sabor
    }
}