package com.advantys.brsmovilidaderp.UI.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Domain.Models.Ruta
import com.advantys.brsmovilidaderp.Domain.UseCases.Ruta_UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Ruta_ViewModel @Inject constructor(private val rutaUsecase: Ruta_UseCase): ViewModel() {
    val rutasModel = MutableLiveData<List<Ruta>>()
    val rutaModel= MutableLiveData<Ruta>()

    fun onCreate(){
        viewModelScope.launch(Dispatchers.Default) {
            val resultado = rutaUsecase()
            if(!resultado.isNullOrEmpty()) rutasModel.postValue(resultado)
        }
    }

    fun cambiarCheck(valor:Boolean?, ruta:String?, check:Boolean?){
        viewModelScope.launch  (Dispatchers.Default){
            rutaUsecase(valor,ruta)
        }
    }
}

//class RutaViewModelFactory (private val rutaUsecase: Ruta_UseCase) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(Ruta_ViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return Ruta_ViewModel(rutaUsecase) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}