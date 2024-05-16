package com.advantys.brsmovilidaderp.Domain.UseCases

import com.advantys.brsmovilidaderp.Data.Repositories.FormasPago_Repository
import com.advantys.brsmovilidaderp.Domain.Models.FormasPago
import javax.inject.Inject

class FormasPago_UseCase @Inject constructor (private val repository: FormasPago_Repository)  {

    suspend  fun obtenerFormasPago(): List<FormasPago>{
        val formasPago = repository.obtenerFormasPago()

        return if(formasPago.isNullOrEmpty())
            listOf<FormasPago>()
        else formasPago
    }

    suspend  fun obtenerFormaPago(forma: Int): FormasPago? {
        return repository.obtenerFormaPago(forma)
    }
}