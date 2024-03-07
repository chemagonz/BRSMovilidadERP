package com.advantys.brsmovilidaderp.Data.Repositories

import com.advantys.brsmovilidaderp.Data.DataBase.Daos.Centros_Dao
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Centros_Entity
import com.advantys.brsmovilidaderp.Domain.Models.Centro
import com.advantys.brsmovilidaderp.Domain.Models.toDomain

class Centro_Repository (private val centroDao: Centros_Dao) {
    suspend fun getAllCentros():List<Centro>{
        val response: List<Centros_Entity?> = centroDao.getAll()
        return response.filterNotNull().map { it.toDomain() }
    }

    suspend fun getAllDetalles(centro: Int?):Centro?{
        val response : Centros_Entity?= centroDao.getAllDetalles(centro)
        return response?.toDomain()
    }
}