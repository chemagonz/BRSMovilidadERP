package com.advantys.brsmovilidaderp.UI.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Domain.Models.Sabor
import com.advantys.brsmovilidaderp.Domain.UseCases.Sabor_UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Sabor_ViewModel @Inject constructor(private var saborUsecase: Sabor_UseCase): ViewModel()  {
    val saboresModel = MutableLiveData<List<Sabor>>()

    fun onCreate(){ viewModelScope.launch(Dispatchers.Default ) {
        val resultado = saborUsecase()
        if(!resultado.isNullOrEmpty()) saboresModel.postValue(resultado)
    }
    }
}