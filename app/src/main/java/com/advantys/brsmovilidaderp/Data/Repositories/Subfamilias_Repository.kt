package com.advantys.brsmovilidaderp.Data.Repositories

import com.advantys.brsmovilidaderp.Data.DataBase.Daos.Subfamilias_Dao
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Subfamilias_Entity
import com.advantys.brsmovilidaderp.Domain.Models.Subfamilia
import com.advantys.brsmovilidaderp.Domain.Models.toDomain
import javax.inject.Inject

class Subfamilias_Repository  @Inject constructor(private val subfamiliasDao: Subfamilias_Dao)  {
    suspend fun getAll():List<Subfamilia>{
        val response: List<Subfamilias_Entity?> = subfamiliasDao.getAll()
        return response.filterNotNull().map { it.toDomain() }
    }
}