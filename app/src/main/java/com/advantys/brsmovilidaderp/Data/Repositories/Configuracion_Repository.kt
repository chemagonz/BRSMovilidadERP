package com.advantys.brsmovilidaderp.Data.Repositories

import android.os.Build
import androidx.annotation.RequiresApi
import com.advantys.brsmovilidaderp.Data.DataBase.Daos.Configuracion_Dao
import javax.inject.Inject

class Configuracion_Repository  @Inject constructor(private val configDao: Configuracion_Dao) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getTerminal(): Int {
           return  configDao.getTerminal()
    }
}