package com.advantys.brsmovilidaderp.Data.Repositories

import com.advantys.brsmovilidaderp.Data.DataBase.Daos.Familias_Dao
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Familias_Entity
import com.advantys.brsmovilidaderp.Domain.Models.Familia
import com.advantys.brsmovilidaderp.Domain.Models.toDomain
import javax.inject.Inject

class Familias_Repository @Inject constructor(private val familiasDao: Familias_Dao) {
    suspend fun getAll():List<Familia>{
        val response: List<Familias_Entity?> = familiasDao.getAll()
        return response.filterNotNull().map { it.toDomain() }
    }
}