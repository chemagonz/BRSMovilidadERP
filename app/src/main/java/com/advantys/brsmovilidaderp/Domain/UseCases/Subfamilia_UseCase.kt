package com.advantys.brsmovilidaderp.Domain.UseCases

import com.advantys.brsmovilidaderp.Data.Repositories.Subfamilias_Repository
import com.advantys.brsmovilidaderp.Domain.Models.Subfamilia
import javax.inject.Inject

class Subfamilia_UseCase @Inject constructor (private val repository: Subfamilias_Repository) {
    suspend operator fun invoke(): List<Subfamilia>{
        val subfamilia = repository.getAll()
        return if(subfamilia.isNullOrEmpty())
            listOf<Subfamilia>()
        else subfamilia
    }
}