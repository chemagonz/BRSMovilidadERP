package com.advantys.brsmovilidaderp.Domain.UseCases

import com.advantys.brsmovilidaderp.Data.Repositories.Formato_Repository
import com.advantys.brsmovilidaderp.Domain.Models.Formato
import javax.inject.Inject

class Formato_UseCase @Inject constructor (private val repository: Formato_Repository) {
    suspend operator fun invoke(): List<Formato>{
        val formato = repository.getAll()
        return if(formato.isNullOrEmpty())
            listOf<Formato>()
        else formato
    }
}