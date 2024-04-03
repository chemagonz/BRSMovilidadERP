package com.advantys.brsmovilidaderp.UI.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Domain.Models.Formato
import com.advantys.brsmovilidaderp.Domain.UseCases.Formato_UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Formato_ViewModel @Inject constructor(private var formatoUsecase: Formato_UseCase): ViewModel()  {
    val formatosModel = MutableLiveData<List<Formato>>()
    fun onCreate(){ viewModelScope.launch(Dispatchers.Default ) {
        val resultado = formatoUsecase()
        if(!resultado.isNullOrEmpty()) formatosModel.postValue(resultado)
    }
    }
}