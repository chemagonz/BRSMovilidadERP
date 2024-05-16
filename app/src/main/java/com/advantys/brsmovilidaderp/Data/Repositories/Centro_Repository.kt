package com.advantys.brsmovilidaderp.Data.Repositories

import com.advantys.brsmovilidaderp.Data.DataBase.Daos.Centros_Dao
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Centros_Entity
import com.advantys.brsmovilidaderp.Domain.Models.Centro
import com.advantys.brsmovilidaderp.Domain.Models.toDomain
import javax.inject.Inject

class Centro_Repository @Inject constructor(private val centroDao: Centros_Dao) {
    suspend fun getCentros():List<Centro>{
        val response: List<Centros_Entity?> = centroDao.getCentros()
        return response.filterNotNull().map { it.toDomain() }
    }
    suspend fun getCentro(centro: Int?):Centro?{
        val response: Centros_Entity? = centroDao.getCentro(centro)
        return response?.toDomain()
    }

    suspend fun centroValido(centro: Int?): Boolean {
        val response: Boolean = centroDao.centroValido(centro)
        return response
    }

    suspend fun obtenerSerieDeCentro(centro: Int?): String? {
        val response: String? = centroDao.obtenerSerieDeCentro(centro)
        return response
    }

    suspend fun primeraSerieCentros(): String? {
        val response: String? = centroDao.primeraSerieCentros()
        return response
    }

    suspend fun obtenerCodCentro():Int {
        val response: Int = centroDao.obtenerCodCentro()
        return response
    }


}