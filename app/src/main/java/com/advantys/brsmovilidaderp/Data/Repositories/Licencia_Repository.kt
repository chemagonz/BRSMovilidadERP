package com.advantys.brsmovilidaderp.Data.Repositories

import com.advantys.brsmovilidaderp.Data.DataBase.Daos.Licencia_Dao
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Licencia_Entity
import javax.inject.Inject

class Licencia_Repository @Inject constructor(private val licenciaDao: Licencia_Dao) {

//    suspend fun getLicencia(): Licencia? {
//        val response:  Licencia_Entity?= licenciaDao.getLicencia()
//        return response?.toDomain()
//    }
    suspend fun insertLicencia(licencia: Licencia_Entity){
         licenciaDao.insertLicencia(licencia)
    }
    suspend fun deleteLicencia(){
        licenciaDao.borrarLicenciaSiexiste()
    }

}