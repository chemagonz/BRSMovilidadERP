package com.advantys.brsmovilidaderp.Data.Repositories

import com.advantys.brsmovilidaderp.Data.DataBase.Daos.FormasPago_Dao
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.FormasPago_Entity
import com.advantys.brsmovilidaderp.Domain.Models.FormasPago
import com.advantys.brsmovilidaderp.Domain.Models.toDomain
import javax.inject.Inject

class FormasPago_Repository  @Inject constructor (private val formaspagoDao: FormasPago_Dao) {

    suspend fun obtenerFormasPago():List<FormasPago>{
        val response: List<FormasPago_Entity?> = formaspagoDao.obtenerFormasPago()
        return response.filterNotNull().map { it.toDomain() }
    }

    suspend fun obtenerFormaPago(forma: Int): FormasPago? {
        val response: FormasPago_Entity? = formaspagoDao.obtenerFormaPago(forma)
        return response?.toDomain()
    }
}