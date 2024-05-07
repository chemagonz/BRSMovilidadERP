package com.advantys.brsmovilidaderp.Data.Repositories

import com.advantys.brsmovilidaderp.Data.DataBase.Daos.Promociones_Dao
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Promociones_Entity
import com.advantys.brsmovilidaderp.Domain.Models.Promocion
import com.advantys.brsmovilidaderp.Domain.Models.toDomain
import javax.inject.Inject

class Promociones_Repository @Inject constructor(private val promocionesDao: Promociones_Dao) {

    suspend fun getPromocionesGenerales():List<Promocion>{
        val response: List<Promociones_Entity?> = promocionesDao.getPromocionesGenerales()
        return response.filterNotNull().map { it.toDomain() }
    }
}