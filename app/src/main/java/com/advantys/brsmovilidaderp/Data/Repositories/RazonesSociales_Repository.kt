package com.advantys.brsmovilidaderp.Data.Repositories

import com.advantys.brsmovilidaderp.Data.DataBase.Daos.RazonesSociales_Dao
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.RazonesSociales_Entity
import com.advantys.brsmovilidaderp.Domain.Models.RazonSocial
import com.advantys.brsmovilidaderp.Domain.Models.toDomain
import javax.inject.Inject

class RazonesSociales_Repository  @Inject constructor (private val razonesSociales_Dao: RazonesSociales_Dao) {

    suspend fun obtenerRazonesSociales(codigo:Int): Map<Int, RazonSocial> {
        val response: Map<Int, RazonesSociales_Entity> = razonesSociales_Dao.obtenerRazonesSociales(codigo)
        return response.filterValues { it != null }.mapValues { it.value.toDomain() }
    }
}