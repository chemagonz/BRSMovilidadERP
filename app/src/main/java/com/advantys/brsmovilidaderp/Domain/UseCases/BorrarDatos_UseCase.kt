package com.advantys.brsmovilidaderp.Domain.UseCases

import com.advantys.brsmovilidaderp.Data.Repositories.BorrarDatos_Repository
import javax.inject.Inject

class BorrarDatos_UseCase @Inject constructor(private val repository: BorrarDatos_Repository) {

    suspend fun borrarCobros(fecha:String):Boolean{
       return repository.borrarCobros(fecha)
    }
    suspend fun borrarCargaCero():Boolean{
       return repository.borrarCargaCero()
    }
    suspend fun borrarVisitas():Boolean{
        return repository.borrarVisitas()
    }
    suspend fun borrarHojaCarga():Boolean{
        return repository.borrarHojaCarga()
    }
    suspend fun borrarVentasDetPedidos(fecha:String):Boolean{
        return repository.borrarVentasDetPedidos(fecha)
    }
    suspend fun borrarVentasCabPedidos(fecha:String):Boolean{
        return repository.borrarVentasCabPedidos(fecha)
    }
    suspend fun borrarRegistrosSueltos():Boolean{
        return repository.borrarRegistrosSueltos()
    }
    suspend fun comprobarDatosPendientesCabPedidos(fecha:String):Int?{
        return repository.comprobarDatosPendientesCabPedidos(fecha)
    }
    suspend fun comprobarDatosPendientesVisitas(fecha:String):Int?{
        return repository.comprobarDatosPendientesVisitas(fecha)
    }
    suspend fun comprobarDatosPendientesCobros(fecha:String):Int?{
        return repository.comprobarDatosPendientesCobros(fecha)
    }
    suspend fun compactarBD(){
      return repository.compactarBD()
    }
}