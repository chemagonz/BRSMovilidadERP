package com.advantys.brsmovilidaderp.Data.Repositories

import com.advantys.brsmovilidaderp.Data.DataBase.Daos.Series_Dao
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Series_Entity
import com.advantys.brsmovilidaderp.Domain.Models.Serie
import com.advantys.brsmovilidaderp.Domain.Models.toDomain
import javax.inject.Inject

class Serie_Repository @Inject constructor( private val seriesDao:Series_Dao){
    suspend fun getAllSeries():List<Serie>{
        val response : List<Series_Entity?> = seriesDao.getAll()
        return response.filterNotNull().map { it.toDomain() }
    }
    suspend fun getAllDetalles(serie: String?):Serie?{
        val response: Series_Entity? = seriesDao.getAllDetalles(serie)
        return response?.toDomain()
    }
    suspend fun getSerieNombre(serie: String?):Serie?{
        val response: Series_Entity?= seriesDao.getNombreSerie(serie)
        return response?.toDomain()
    }
}