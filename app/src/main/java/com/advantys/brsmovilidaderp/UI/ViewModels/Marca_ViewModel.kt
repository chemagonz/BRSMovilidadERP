package com.advantys.brsmovilidaderp.UI.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Domain.Models.Marca
import com.advantys.brsmovilidaderp.Domain.UseCases.Marca_UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Marca_ViewModel @Inject constructor(private var marcaUsecase: Marca_UseCase): ViewModel()  {
    val marcasModel = MutableLiveData<List<Marca>>()

    fun onCreate(){ viewModelScope.launch(Dispatchers.Default ) {
        val resultado = marcaUsecase()
        if(!resultado.isNullOrEmpty()) marcasModel.postValue(resultado)
    }
    }
}