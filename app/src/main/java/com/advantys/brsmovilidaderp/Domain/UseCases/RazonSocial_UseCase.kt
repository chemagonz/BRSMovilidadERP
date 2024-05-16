package com.advantys.brsmovilidaderp.Domain.UseCases

import com.advantys.brsmovilidaderp.Data.Repositories.RazonesSociales_Repository
import com.advantys.brsmovilidaderp.Domain.Models.RazonSocial
import javax.inject.Inject

class RazonSocial_UseCase @Inject constructor (private val repository: RazonesSociales_Repository) {

    suspend  fun obtenerRazonesSociales(codigo: Int): Map<Int, RazonSocial>{
        val razon = repository.obtenerRazonesSociales(codigo)
        return if(razon.isNullOrEmpty()) mapOf<Int,RazonSocial>()
        else razon
    }
}