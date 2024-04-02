package com.advantys.brsmovilidaderp.Domain.UseCases

import com.advantys.brsmovilidaderp.Data.Repositories.Articulos_Repository
import com.advantys.brsmovilidaderp.Domain.Models.Articulo
import com.advantys.brsmovilidaderp.Utils.buscarArticulosPor
import javax.inject.Inject

class Articulo_UseCase @Inject constructor (private val repository: Articulos_Repository){
    suspend operator fun invoke(): List<Articulo>{
        val articulo = repository.getAllArticulos()
        return if(articulo.isNullOrEmpty())
            listOf<Articulo>()
        else articulo
    }
    suspend operator fun invoke(columnas: buscarArticulosPor,query:String):List<Articulo>{
        return repository.getFilter(columnas, query)
    }
    suspend  fun detalles(articulo:String?, fabricante:Short?): Articulo {
        articulo?.let {
            val articuloDet= repository.getDetalles(articulo, fabricante)
            return articuloDet ?: throw NoSuchElementException("Error")
        }?:throw IllegalArgumentException("no puede ser nulo")
    }
}