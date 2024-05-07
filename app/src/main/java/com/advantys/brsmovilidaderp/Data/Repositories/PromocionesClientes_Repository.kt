package com.advantys.brsmovilidaderp.Data.Repositories

import com.advantys.brsmovilidaderp.Data.DataBase.Daos.PromocionesClientes_Dao
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.PromocionesClientes_Entity
import com.advantys.brsmovilidaderp.Domain.Models.PromoCliente
import com.advantys.brsmovilidaderp.Domain.Models.toDomain
import javax.inject.Inject

class PromocionesClientes_Repository @Inject constructor(private val promocionesDao: PromocionesClientes_Dao){

    suspend fun getPromocionesParticulares(cliente: Int?, delegacion: Short?):List<PromoCliente>{
        val response: List<PromocionesClientes_Entity?> = promocionesDao.getPromocionesParticulares(cliente, delegacion)
        return response.filterNotNull().map { it.toDomain() }
    }
}