package com.advantys.brsmovilidaderp.UI.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Domain.Models.TipoOperacion
import com.advantys.brsmovilidaderp.Domain.UseCases.TipoOperacion_UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TipoOperacion_ViewModel @Inject constructor(private val tipoOperacionUsecase: TipoOperacion_UseCase) : ViewModel() {
    val tipoOperacionModel = MutableLiveData<List<TipoOperacion>>()
    val operacionModel = MutableLiveData<TipoOperacion>()
    fun onCreate(){ viewModelScope.launch {
            val resultado = tipoOperacionUsecase()
            if(!resultado.isNullOrEmpty()) tipoOperacionModel.postValue(resultado)
        }
    }

    fun onCreate(operacion:Short?){
        viewModelScope.launch(Dispatchers.Default) {
            val resultado= tipoOperacionUsecase(operacion)
            operacionModel.postValue(resultado)
        }
    }
}

