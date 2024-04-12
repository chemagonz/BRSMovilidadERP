package com.advantys.brsmovilidaderp.Domain.UseCases

import com.advantys.brsmovilidaderp.Data.Repositories.BorrarDatos_Repository
import javax.inject.Inject

class BorrarDatos_UseCase @Inject constructor(private val repository: BorrarDatos_Repository) {

    suspend fun borrarCobros(fecha:String){
        repository.borrarCobros(fecha)
    }
    suspend fun borrarCargaCero(){
        repository.borrarCargaCero()
    }
    suspend fun borrarVisitas(){
        repository.borrarVisitas()
    }
    suspend fun borrarHojaCarga(){
        repository.borrarHojaCarga()
    }
    suspend fun borrarVentas(fecha:String){
        repository.borrarVentas(fecha)
    }
    suspend fun borrarRegistrosSueltos(){
        repository.borrarRegistrosSueltos()
    }
}