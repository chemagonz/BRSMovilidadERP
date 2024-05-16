package com.advantys.brsmovilidaderp.UI.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Domain.Models.FormasPago
import com.advantys.brsmovilidaderp.Domain.UseCases.FormasPago_UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormasPago_ViewModel  @Inject constructor (private var formaspagoUsecase: FormasPago_UseCase): ViewModel() {

    val formasPagomodel = MutableLiveData<List<FormasPago>>()
    val formaPagomodel = MutableLiveData<FormasPago?>()

    fun obtenerFormasPago() {
        viewModelScope.launch(Dispatchers.Default) {

            val resultado = formaspagoUsecase.obtenerFormasPago()
            if(!resultado.isNullOrEmpty()) formasPagomodel.postValue(resultado)
        }
    }

    fun obtenerFormaPago(forma: Int) {
        viewModelScope.launch(Dispatchers.Default) {

            val resultado = formaspagoUsecase.obtenerFormaPago(forma)

            if(resultado!= null){
                formaPagomodel.postValue(resultado)
            }
        }
    }
}