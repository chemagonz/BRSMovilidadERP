package com.advantys.brsmovilidaderp.Domain.UseCases

import com.advantys.brsmovilidaderp.Data.Repositories.Licencia_Repository
import com.advantys.brsmovilidaderp.Domain.Models.Licencia
import com.advantys.brsmovilidaderp.Utils.EncriptarUtils
import java.util.Locale
import javax.inject.Inject

class Licencia_UseCase @Inject constructor (private val repository: Licencia_Repository) {

//    suspend operator fun invoke():Licencia? {
//        val licencia= repository.getLicencia()
//        return licencia?: throw NoSuchElementException("Error")
//    }

    suspend fun insertLicencia(licencia: Licencia): Boolean{
       return repository.insertLicencia(licencia)
    }

    suspend fun deleteLicencia(){
        val licencia= repository.deleteLicencia()
        return licencia?: throw NoSuchElementException("Error")
    }
   suspend fun verificarLicencia(licencia: Licencia):Boolean{
       return if(licencia.licencia?.uppercase().equals(ObtenerLicencia(licencia.idenProg?.uppercase()))) true
       else false
    }
   suspend fun ObtenerLicencia(codigo: String?): String {
        var licencia = ""
        val aux = StringBuilder(codigo)
        licencia = EncriptarUtils.CRC32(aux.reverse().toString())
        licencia += "-"
        licencia += EncriptarUtils.CRCCCITT(aux.toString())
        licencia = licencia.uppercase(Locale.getDefault())
        return licencia
    }

}