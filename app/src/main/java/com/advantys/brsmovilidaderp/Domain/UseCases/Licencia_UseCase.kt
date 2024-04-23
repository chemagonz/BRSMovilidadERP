package com.advantys.brsmovilidaderp.Domain.UseCases

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Licencia_Entity
import com.advantys.brsmovilidaderp.Data.Repositories.Licencia_Repository
import javax.inject.Inject

class Licencia_UseCase @Inject constructor (private val repository: Licencia_Repository) {
//    suspend operator fun invoke():Licencia? {
//        val licencia= repository.getLicencia()
//        return licencia?: throw NoSuchElementException("Error")
//    }

    suspend fun insertLicencia(licencia: Licencia_Entity){
        val licencia= repository.insertLicencia(licencia)
        return licencia?: throw NoSuchElementException("Error")
    }

    suspend fun deleteLicencia(){
        val licencia= repository.deleteLicencia()
        return licencia?: throw NoSuchElementException("Error")
    }
}