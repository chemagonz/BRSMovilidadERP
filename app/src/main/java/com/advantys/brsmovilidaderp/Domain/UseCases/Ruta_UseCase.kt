package com.advantys.brsmovilidaderp.Domain.UseCases

import com.advantys.brsmovilidaderp.Data.Repositories.Ruta_Repository
import com.advantys.brsmovilidaderp.Domain.Models.Ruta
import javax.inject.Inject

class Ruta_UseCase @Inject constructor(private val repository: Ruta_Repository) {
    suspend operator fun invoke():List<Ruta>{
        val ruta= repository.getAllRutas()
        return if(ruta.isNullOrEmpty()) {
            listOf<Ruta>()
        } else ruta
    }
    suspend operator fun invoke(valor: Boolean?, ruta:String?) {
        ruta?.let {
            val rutaUp= repository.getUpdateRutas(valor,ruta)
            return rutaUp?: throw NoSuchElementException("Error")
        }?:throw IllegalArgumentException("no puede ser nulo")
    }
}
