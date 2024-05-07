package com.advantys.brsmovilidaderp.UI.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Domain.Models.Promocion
import com.advantys.brsmovilidaderp.Domain.UseCases.Promociones_UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Promociones_ViewModel @Inject constructor(private val promociones_UseCase: Promociones_UseCase): ViewModel()  {

    val promocionesModel= MutableLiveData<List<Promocion>>()

    fun getPromocionesGenerales(){
        viewModelScope.launch(Dispatchers.Default) {
            val resultado= promociones_UseCase.getPromocionesGenerales()
            if(!resultado.isNullOrEmpty()) promocionesModel.postValue(resultado)
        }
    }

    fun btnDetalle(){}
}