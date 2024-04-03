package com.advantys.brsmovilidaderp.Data.Repositories

import com.advantys.brsmovilidaderp.Data.DataBase.Daos.Formato_Dao
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Formato_Entity
import com.advantys.brsmovilidaderp.Domain.Models.Formato
import com.advantys.brsmovilidaderp.Domain.Models.toDomain
import javax.inject.Inject

class Formato_Repository @Inject constructor(private val formatoDao: Formato_Dao) {
    suspend fun getAll():List<Formato>{
        val response: List<Formato_Entity?> = formatoDao.getAll()
        return response.filterNotNull().map { it.toDomain() }
    }
}