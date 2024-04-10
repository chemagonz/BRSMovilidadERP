package com.advantys.brsmovilidaderp.UI.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Licencia_Entity
import com.advantys.brsmovilidaderp.Domain.Models.Licencia
import com.advantys.brsmovilidaderp.Domain.UseCases.Licencia_UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
    fun insertLicencia(licencia: Licencia_Entity){
        viewModelScope.launch(Dispatchers.Default) {
            licenciaUsecase.insertLicencia(licencia)
        }
    }
    fun deleteLicencia(){
        viewModelScope.launch(Dispatchers.Default) {
            licenciaUsecase.deleteLicencia()
        }
    }
}