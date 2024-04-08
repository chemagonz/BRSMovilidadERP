package com.advantys.brsmovilidaderp.Data.Repositories

import com.advantys.brsmovilidaderp.Data.DataBase.Daos.Tarifas_Dao
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Tarifas_Entity
import com.advantys.brsmovilidaderp.Domain.Models.Tarifa
import com.advantys.brsmovilidaderp.Domain.Models.toDomain
import javax.inject.Inject

class Tarifas_Repository @Inject constructor(private val tarifas_Dao: Tarifas_Dao) {

    suspend fun getNombreTarifa(tarifa: String?): List<Tarifa> {
        val response: List<Tarifas_Entity?> = tarifas_Dao.getNombreTarifa(tarifa)
        return response.filterNotNull().map { it.toDomain() }
    }
}