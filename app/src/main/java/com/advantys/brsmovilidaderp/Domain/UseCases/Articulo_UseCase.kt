package com.advantys.brsmovilidaderp.Domain.UseCases

import com.advantys.brsmovilidaderp.Data.Repositories.Articulos_Repository
import com.advantys.brsmovilidaderp.Domain.Models.Articulo
import com.advantys.brsmovilidaderp.Utils.BuscarArticulosPor
import javax.inject.Inject

class Articulo_UseCase @Inject constructor (private val repository: Articulos_Repository) {

    suspend  fun obtenerTodosArticulos(): List<Articulo>{
        val articulo = repository.obtenerTodosArticulos()

        return if(articulo.isNullOrEmpty())
            listOf<Articulo>()
        else articulo
    }

    suspend  fun detalles(articulo: String?): Articulo? {
        return repository.obtenerDetalles(articulo)
    }

    suspend  fun obtenerArticulos(buscarArticulosPor: BuscarArticulosPor, codfamilia: Short?, codsubfamilia:Short?, codformato:Int?, codmarca:String?, codsabor:String?, tipoConsulta: String?): List<Articulo> {
        val articulo = repository.obtenerArticulos(buscarArticulosPor, codfamilia, codsubfamilia, codformato, codmarca, codsabor, tipoConsulta)

        return if(articulo.isNullOrEmpty())
            listOf<Articulo>()
        else articulo
    }
}