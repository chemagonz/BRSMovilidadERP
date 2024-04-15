package com.advantys.brsmovilidaderp.Data.Repositories

import com.advantys.brsmovilidaderp.Data.DataBase.Daos.BorrarDatos_Dao
import javax.inject.Inject

class BorrarDatos_Repository  @Inject constructor(private val borrardatosDao: BorrarDatos_Dao)  {

    suspend fun borrarCobros(fecha:String){
        borrardatosDao.borrarCobros(fecha)
    }
    suspend fun borrarCargaCero(){
        borrardatosDao.borrarCargaCero()
    }
    suspend fun borrarVisitas(){
        borrardatosDao.borrarVisitas()
    }
    suspend fun borrarHojaCarga(){
        borrardatosDao.borrarHojaCarga()
    }
    suspend fun borrarVentas(fecha:String){
        borrardatosDao.borrarVentas(fecha)
    }
    suspend fun borrarRegistrosSueltos(){
        borrardatosDao.borrarRegistrosSueltos()
    }
    suspend fun comprobarDatosPendientes(fecha:String): IntArray? {
        return  borrardatosDao.comprobarDatosPendientes(fecha)
    }
}