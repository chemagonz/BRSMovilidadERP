package com.advantys.brsmovilidaderp.Data.Repositories

import com.advantys.brsmovilidaderp.Data.DataBase.Daos.Articulos_Dao
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Articulos_Entity
import com.advantys.brsmovilidaderp.Domain.Models.Articulo
import com.advantys.brsmovilidaderp.Domain.Models.toDomain
import javax.inject.Inject

class Articulos_Repository @Inject constructor(private val articulosDao: Articulos_Dao){

    suspend fun getAllArticulos():List<Articulo>{
        val response: List<Articulos_Entity?> = articulosDao.getAll()
        return response.filterNotNull().map { it.toDomain() }
    }
    suspend fun getFilter(tipoConsulta: String?):List<Articulo>{
        val response :List<Articulos_Entity?> =articulosDao.getFilter(tipoConsulta)
        return response.filterNotNull().map { it.toDomain() }
    }
}