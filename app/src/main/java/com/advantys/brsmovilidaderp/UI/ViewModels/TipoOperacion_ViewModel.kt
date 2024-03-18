package com.advantys.brsmovilidaderp.UI.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Domain.Models.tipoOperacion
import com.advantys.brsmovilidaderp.Domain.UseCases.TipoOperacion_UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TipoOperacion_ViewModel @Inject constructor(private val tipoOperacionUsecase: TipoOperacion_UseCase) : ViewModel() {
    val tipoOperacionModel = MutableLiveData<List<tipoOperacion>>()

    fun onCreate(){ viewModelScope.launch {
            val resultado = tipoOperacionUsecase()
            if(!resultado.isNullOrEmpty()) tipoOperacionModel.postValue(resultado)
        }
    }
}

