package com.advantys.brsmovilidaderp.UI.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Licencia_Entity
import com.advantys.brsmovilidaderp.Domain.Models.Licencia
import com.advantys.brsmovilidaderp.Domain.UseCases.Licencia_UseCase
import com.advantys.brsmovilidaderp.Utils.EncriptarUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class Licencia_ViewModel @Inject constructor(private var licenciaUsecase: Licencia_UseCase): ViewModel() {
val licenciaModel= MutableLiveData<Licencia?>()

    fun getLicencia(){
        viewModelScope.launch(Dispatchers.Default) {
            val resultado= licenciaUsecase()
            if(resultado != null) licenciaModel.postValue(resultado)
        }
    }
    fun insertLicencia(licencia: Licencia_Entity): Boolean {
        viewModelScope.launch(Dispatchers.Default) {
            licenciaUsecase.insertLicencia(licencia)
        }
        return true
    }
    fun deleteLicencia(){
        viewModelScope.launch(Dispatchers.Default) {
            licenciaUsecase.deleteLicencia()
        }
    }

    fun verificarLicencia(licencia: Licencia_Entity):Boolean{
        var ok= false
//        var obtener= ObtenerLicencia(licencia.idenProg?.uppercase())
        if(licencia.licencia?.uppercase().equals(ObtenerLicencia(licencia.idenProg?.uppercase())))
          ok= true
      //PONER UN DIALOGO, ARREGLAR CONTEXTO
        return ok
    }
    fun aceptar(licencia: Licencia_Entity):Boolean{
        var ok= false
        if(verificarLicencia(licencia))
            if(insertLicencia(licencia))
                ok=true
        return ok
    }
    fun ObtenerLicencia(codigo: String?): String {
        var licencia = ""
        val aux = StringBuilder(codigo)
        licencia = EncriptarUtils.CRC32(aux.reverse().toString())
        licencia += "-"
        licencia += EncriptarUtils.CRCCCITT(aux.toString())
        licencia = licencia.uppercase(Locale.getDefault())
        return licencia
    }

}