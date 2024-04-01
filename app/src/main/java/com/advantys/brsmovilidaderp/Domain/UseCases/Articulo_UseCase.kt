package com.advantys.brsmovilidaderp.Domain.UseCases

import com.advantys.brsmovilidaderp.Data.Repositories.Articulos_Repository
import com.advantys.brsmovilidaderp.Domain.Models.Articulo
import javax.inject.Inject

class Articulo_UseCase @Inject constructor (private val repository: Articulos_Repository){
    suspend operator fun invoke(): List<Articulo>{
        val articulo = repository.getAllArticulos()
        return if(articulo.isNullOrEmpty())
            listOf<Articulo>()
        else articulo
    }
    suspend operator fun invoke(query:String):List<Articulo>{
        return repository.getFilter(query)
    }
}