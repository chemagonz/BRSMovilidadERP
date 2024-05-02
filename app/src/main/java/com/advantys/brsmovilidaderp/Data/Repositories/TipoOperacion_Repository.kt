package com.advantys.brsmovilidaderp.Data.Repositories

import com.advantys.brsmovilidaderp.Data.DataBase.Daos.TipoOperacion_Dao
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.TipoOperacion_Entity
import com.advantys.brsmovilidaderp.Domain.Models.TipoOperacion
import com.advantys.brsmovilidaderp.Domain.Models.toDomain
import javax.inject.Inject

class TipoOperacion_Repository @Inject constructor(private val tipoOperacionDao: TipoOperacion_Dao) {
    suspend fun getAllTipos():List<TipoOperacion>{
        val response: List <TipoOperacion_Entity?> = tipoOperacionDao.getAll()
        return response.filterNotNull().map { it.toDomain() }
    }

    suspend fun getNombreOperacion(operacion: Short?): TipoOperacion? {
        val response: TipoOperacion_Entity? = tipoOperacionDao.getNombreOperacion(operacion)
        return response?.toDomain()
    }
}