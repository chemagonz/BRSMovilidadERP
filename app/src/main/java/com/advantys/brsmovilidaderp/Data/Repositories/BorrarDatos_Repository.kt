package com.advantys.brsmovilidaderp.Data.Repositories

import com.advantys.brsmovilidaderp.Data.DataBase.Daos.BorrarDatos_Dao
import javax.inject.Inject

class BorrarDatos_Repository  @Inject constructor(private val borrardatosDao: BorrarDatos_Dao)  {

    suspend fun borrarCobros(fecha:String):Boolean{
       return borrardatosDao.borrarCobros(fecha)
    }
    suspend fun borrarCargaCero():Boolean{
        return borrardatosDao.borrarCargaCero()
    }
    suspend fun borrarVisitas():Boolean{
       return borrardatosDao.borrarVisitas()
    }
    suspend fun borrarHojaCarga():Boolean{
       return borrardatosDao.borrarHojaCarga()
    }
    suspend fun borrarVentasDetPedidos(fecha:String):Boolean{
        return borrardatosDao.borrarVentasDetPedidos(fecha)
    }
    suspend fun borrarVentasCabPedidos(fecha:String):Boolean{
        return borrardatosDao.borrarVentasCabPedidos(fecha)
    }
    suspend fun borrarRegistrosSueltos():Boolean{
       return borrardatosDao.borrarRegistrosSueltos()
    }
    suspend fun comprobarDatosPendientesCabPedidos(fecha:String): Int? {
        return  borrardatosDao.comprobarDatosPendientesCabPedidos(fecha)
    }
    suspend fun comprobarDatosPendientesVisitas(fecha:String): Int? {
        return  borrardatosDao.comprobarDatosPendientesVisitas(fecha)
    }
    suspend fun comprobarDatosPendientesCobros(fecha:String): Int? {
        return  borrardatosDao.comprobarDatosPendientesCobros(fecha)
    }
    suspend fun compactarBD(){
      return borrardatosDao.compactarBD()
    }
}