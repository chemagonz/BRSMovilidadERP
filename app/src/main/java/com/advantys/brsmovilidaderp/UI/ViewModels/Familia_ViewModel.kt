package com.advantys.brsmovilidaderp.UI.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Domain.Models.Familia
import com.advantys.brsmovilidaderp.Domain.UseCases.Familia_UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Familia_ViewModel @Inject constructor(private var familiaUsecase: Familia_UseCase): ViewModel()  {
    val familiasModel = MutableLiveData<List<Familia>>()
    fun onCreate(){ viewModelScope.launch(Dispatchers.Default ) {
        val resultado = familiaUsecase()
        if(!resultado.isNullOrEmpty()) familiasModel.postValue(resultado)
        }
    }
}