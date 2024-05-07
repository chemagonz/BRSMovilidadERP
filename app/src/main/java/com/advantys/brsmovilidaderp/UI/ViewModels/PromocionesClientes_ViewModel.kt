package com.advantys.brsmovilidaderp.UI.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Domain.Models.PromoCliente
import com.advantys.brsmovilidaderp.Domain.UseCases.PromocionesClientes_UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PromocionesClientes_ViewModel @Inject constructor(private val promocionesPar_Usecase: PromocionesClientes_UseCase): ViewModel() {

    val promocionesParModel= MutableLiveData<List<PromoCliente>>()

    fun getPromocionesParticulares(cliente:Int?, delegacion: Short?){
        viewModelScope.launch(Dispatchers.Default) {
            val resultado= promocionesPar_Usecase.getPromocionesParticulares(cliente, delegacion)
            if(!resultado.isNullOrEmpty()) promocionesParModel.postValue(resultado)
        }
    }

    fun btnDetalle(){}
}