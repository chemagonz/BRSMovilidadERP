package com.advantys.brsmovilidaderp.Data.Repositories

import com.advantys.brsmovilidaderp.Data.DataBase.Daos.TiposIVA_Dao
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.TiposIVA_Entity
import com.advantys.brsmovilidaderp.Domain.Models.TipoIVA
import com.advantys.brsmovilidaderp.Domain.Models.toDomain
import javax.inject.Inject

class TiposIVA_Repository @Inject constructor(private val tiposivaDao: TiposIVA_Dao) {
    suspend fun getIVA(IVA:Short?): TipoIVA?{
        val response : TiposIVA_Entity?= tiposivaDao.getIVA(IVA)
        return response?.toDomain()
    }
}