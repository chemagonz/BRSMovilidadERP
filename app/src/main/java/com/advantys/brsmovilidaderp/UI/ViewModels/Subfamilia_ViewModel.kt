package com.advantys.brsmovilidaderp.UI.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Domain.Models.Subfamilia
import com.advantys.brsmovilidaderp.Domain.UseCases.Subfamilia_UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Subfamilia_ViewModel @Inject constructor(private var subfamiliaUsecase: Subfamilia_UseCase): ViewModel()  {
    val subfamiliasModel = MutableLiveData<List<Subfamilia>>()

    fun onCreate(){ viewModelScope.launch(Dispatchers.Default ) {
        val resultado = subfamiliaUsecase()

        if(!resultado.isNullOrEmpty()) subfamiliasModel.postValue(resultado)
    }
    }

}