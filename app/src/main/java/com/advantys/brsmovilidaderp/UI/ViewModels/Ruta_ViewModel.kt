package com.advantys.brsmovilidaderp.UI.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Domain.Models.Ruta
import com.advantys.brsmovilidaderp.Domain.UseCases.Ruta_UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Ruta_ViewModel (private val rutaUsecase: Ruta_UseCase): ViewModel() {
    val rutasModel = MutableLiveData<List<Ruta>>()

    fun onCreate(){
        viewModelScope.launch(Dispatchers.Default) {
            val resultado = rutaUsecase()
            if(!resultado.isNullOrEmpty()) rutasModel.postValue(resultado)
        }
    }

}
class RutaViewModelFactory (private val rutaUsecase: Ruta_UseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(Ruta_ViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return Ruta_ViewModel(rutaUsecase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}