package com.advantys.brsmovilidaderp.Data.Repositories

import com.advantys.brsmovilidaderp.Data.DataBase.Daos.TipoOperacion_Dao
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.TipoOperacion_Entity
import com.advantys.brsmovilidaderp.Domain.Models.tipoOperacion
import com.advantys.brsmovilidaderp.Domain.Models.toDomain

class TipoOperacion_Repository (private val tipoOperacionDao: TipoOperacion_Dao) {
    suspend fun getAllTipos():List<tipoOperacion>{
        val response: List <TipoOperacion_Entity?> = tipoOperacionDao.getAll()
        return response.filterNotNull().map { it.toDomain() }
    }

}