package com.advantys.brsmovilidaderp.Data.Repositories

import com.advantys.brsmovilidaderp.Data.DataBase.Daos.Sabor_Dao
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Sabor_Entity
import com.advantys.brsmovilidaderp.Domain.Models.Sabor
import com.advantys.brsmovilidaderp.Domain.Models.toDomain
import javax.inject.Inject

class Sabor_Repository @Inject constructor(private val saborDao: Sabor_Dao) {
    suspend fun getAll():List<Sabor>{
        val response: List<Sabor_Entity?> = saborDao.getAll()
        return response.filterNotNull().map { it.toDomain() }
    }
}