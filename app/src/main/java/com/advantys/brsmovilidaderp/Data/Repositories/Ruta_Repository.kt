package com.advantys.brsmovilidaderp.Data.Repositories

import com.advantys.brsmovilidaderp.Data.DataBase.Daos.Rutas_Dao
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Rutas_Entity
import com.advantys.brsmovilidaderp.Domain.Models.Ruta
import com.advantys.brsmovilidaderp.Domain.Models.toDomain
import javax.inject.Inject

class Ruta_Repository @Inject constructor(private val rutasDao: Rutas_Dao) {
    suspend fun getAllRutas():List<Ruta> {
        val response: List<Rutas_Entity?> = rutasDao.getAll()
        return response.filterNotNull().map { it.toDomain() }
    }
    suspend fun getUpdateRutas(valor:Boolean?, ruta:String?){
        rutasDao.updateCheck(valor, ruta)
    }
}