package com.advantys.brsmovilidaderp.Data.Repositories

import com.advantys.brsmovilidaderp.Data.DataBase.Daos.Marca_Dao
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Marca_Entity
import com.advantys.brsmovilidaderp.Domain.Models.Marca
import com.advantys.brsmovilidaderp.Domain.Models.toDomain
import javax.inject.Inject

class Marca_Repository @Inject constructor(private val marcaDao: Marca_Dao) {
    suspend fun getAll():List<Marca>{
        val response: List<Marca_Entity?> = marcaDao.getAll()
        return response.filterNotNull().map { it.toDomain() }
    }
}