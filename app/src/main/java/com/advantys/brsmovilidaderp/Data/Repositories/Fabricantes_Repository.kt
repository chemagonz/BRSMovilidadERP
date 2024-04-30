package com.advantys.brsmovilidaderp.Data.Repositories

import com.advantys.brsmovilidaderp.Data.DataBase.Daos.Fabricantes_Dao
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Fabricante_Entity
import com.advantys.brsmovilidaderp.Domain.Models.Fabricante
import com.advantys.brsmovilidaderp.Domain.Models.toDomain
import javax.inject.Inject

class Fabricantes_Repository @Inject constructor(private val fabricanteDao: Fabricantes_Dao) {

    suspend fun getNombreFabricante(fabricante: Short?): Fabricante?{
        val response : Fabricante_Entity?= fabricanteDao.getNombreFabricante(fabricante)
        return response?.toDomain()
    }
}