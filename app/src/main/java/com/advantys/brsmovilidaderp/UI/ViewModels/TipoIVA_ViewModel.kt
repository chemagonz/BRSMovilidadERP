package com.advantys.brsmovilidaderp.UI.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Domain.Models.TipoIVA
import com.advantys.brsmovilidaderp.Domain.UseCases.TipoIVA_UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TipoIVA_ViewModel @Inject constructor(private val tipoivaUsecase: TipoIVA_UseCase ): ViewModel() {
    val tipoIVAModel = MutableLiveData<TipoIVA?>()
    fun onCreateGetIVA(IVA: Short?) {
        viewModelScope.launch(Dispatchers.Default) {
            val resultado = tipoivaUsecase.getIVA(IVA)
            if (resultado != null) {
                tipoIVAModel.postValue(resultado)
            }
        }
    }
}