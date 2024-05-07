package com.advantys.brsmovilidaderp.Domain.UseCases

import com.advantys.brsmovilidaderp.Data.Repositories.PromocionesClientes_Repository
import com.advantys.brsmovilidaderp.Domain.Models.PromoCliente
import javax.inject.Inject

class PromocionesClientes_UseCase @Inject constructor(private val repository: PromocionesClientes_Repository) {

    suspend  fun getPromocionesParticulares():List<PromoCliente>{
        return repository.getPromocionesParticulares()
    }
}