package com.advantys.brsmovilidaderp.Data.Repositories

import com.advantys.brsmovilidaderp.Data.DataBase.Daos.Rutas_Dao
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Rutas_Entity
import com.advantys.brsmovilidaderp.Domain.Models.Ruta
import com.advantys.brsmovilidaderp.Domain.Models.toDomain

class Ruta_Repository (private val rutasDao: Rutas_Dao) {
        suspend fun getAllRutas():List<Ruta>{
       val response: List<Rutas_Entity?> = rutasDao.getAll()
        return response.filterNotNull().map { it.toDomain() }
    }
}