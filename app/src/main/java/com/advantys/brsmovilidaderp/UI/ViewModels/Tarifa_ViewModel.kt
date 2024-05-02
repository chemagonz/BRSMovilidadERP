package com.advantys.brsmovilidaderp.UI.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Domain.Models.Tarifa
import com.advantys.brsmovilidaderp.Domain.UseCases.Tarifa_UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Tarifa_ViewModel @Inject constructor(private val tarifa_UseCase: Tarifa_UseCase) : ViewModel() {
    val tarifasModel= MutableLiveData<Tarifa>()
    fun onCreate(tarifa:Short?){
        viewModelScope.launch(Dispatchers.Default) {
            val resultado= tarifa_UseCase(tarifa)
            tarifasModel.postValue(resultado)
        }
    }
}