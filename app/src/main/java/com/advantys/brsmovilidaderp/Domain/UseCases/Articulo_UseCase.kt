package com.advantys.brsmovilidaderp.Domain.UseCases

import com.advantys.brsmovilidaderp.Data.Repositories.Articulos_Repository
import com.advantys.brsmovilidaderp.Domain.Models.Articulo
import com.advantys.brsmovilidaderp.Utils.BuscarArticulosPor
import javax.inject.Inject

class Articulo_UseCase @Inject constructor (private val repository: Articulos_Repository){
    suspend operator fun invoke(): List<Articulo>{
        val articulo = repository.getAllArticulos()
        return if(articulo.isNullOrEmpty())
            listOf<Articulo>()
        else articulo
    }
    suspend operator fun invoke(columnas: BuscarArticulosPor, query:String):List<Articulo>{
        return repository.getFilter(columnas, query)
    }
    suspend  fun detalles(articulo:String?): Articulo? {
        return repository.getDetalles(articulo)
    }

    suspend operator fun invoke(buscarArticulosPor: BuscarArticulosPor, codfamilia: Short?, codsubfamilia:Short?, codformato:Int?, codmarca:String?, codsabor:String?, tipoConsulta: String?): List<Articulo>{
        val articulo = repository.obtenerArticulos(buscarArticulosPor, codfamilia, codsubfamilia, codformato, codmarca, codsabor, tipoConsulta)
        return if(articulo.isNullOrEmpty())
            listOf<Articulo>()
        else articulo
    }

}