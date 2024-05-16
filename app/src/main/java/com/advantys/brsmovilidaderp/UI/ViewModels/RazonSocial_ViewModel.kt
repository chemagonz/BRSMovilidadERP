package com.advantys.brsmovilidaderp.UI.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Domain.Models.RazonSocial
import com.advantys.brsmovilidaderp.Domain.UseCases.RazonSocial_UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RazonSocial_ViewModel @Inject constructor (private var razonsocialUsecase: RazonSocial_UseCase): ViewModel() {

    val razonesModel = MutableLiveData<Map<Int,RazonSocial>>()

    fun obtenerRazonesSociales(codigo:Int) {
        viewModelScope.launch(Dispatchers.Default ) {
            val resultado = razonsocialUsecase.obtenerRazonesSociales(codigo)
            if(!resultado.isNullOrEmpty()) razonesModel.postValue(resultado)
         }
    }
}