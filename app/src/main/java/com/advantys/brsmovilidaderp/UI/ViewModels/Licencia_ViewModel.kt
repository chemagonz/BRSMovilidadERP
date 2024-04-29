package com.advantys.brsmovilidaderp.UI.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Domain.Models.Licencia
import com.advantys.brsmovilidaderp.Domain.UseCases.Licencia_UseCase
import com.advantys.brsmovilidaderp.Utils.Respuesta
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Licencia_ViewModel @Inject constructor(private var licenciaUsecase: Licencia_UseCase): ViewModel() {

    val licenciaModel = MutableLiveData<Licencia?>()
    private var _respuesta = MutableLiveData<Respuesta>()
    private var _respuestaDialogo = MutableLiveData<Respuesta>()
    val respuesta : LiveData<Respuesta>  get() = _respuesta
    val respuestaDialogo : LiveData<Respuesta>  get() = _respuestaDialogo


//    fun getLicencia(){
//        viewModelScope.launch(Dispatchers.Default) {
//            val resultado= licenciaUsecase()
//            if(resultado != null) licenciaModel.postValue(resultado)
//        }
//    }

   suspend fun insertLicencia(licencia: Licencia): Boolean {
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

//    fun aceptar(licencia: Licencia_Entity):Boolean{
//        var ok= false
//        if(verificarLicencia(licencia))
//            if(insertLicencia(licencia))
//                ok=true
//        return ok
//    }

    fun aceptar(licencia: Licencia): Boolean {
        var ok = false

        _respuestaDialogo.postValue(Respuesta.cargando("Verificando licencia"))

        viewModelScope.launch(Dispatchers.Default) {
            delay(1200)
            if (licenciaUsecase.verificarLicencia(licencia)) {
                val resultado = async { insertLicencia(licencia) }
                if (resultado.await()) {
                    _respuesta.postValue(Respuesta.ok("Licencia insertada correctamente"))
                    ok = true
                }
            } else {
                _respuesta.postValue(Respuesta.error("Error al insertar la licencia"))
                ok = false
            }
        }

        return ok
    }
}