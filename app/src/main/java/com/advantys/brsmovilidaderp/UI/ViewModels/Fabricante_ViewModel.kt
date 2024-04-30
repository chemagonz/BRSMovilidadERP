package com.advantys.brsmovilidaderp.UI.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Domain.Models.Fabricante
import com.advantys.brsmovilidaderp.Domain.UseCases.Fabricante_UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Fabricante_ViewModel @Inject constructor(private val fabricanteUsecase: Fabricante_UseCase): ViewModel() {

    val fabricanteModel = MutableLiveData<Fabricante?>()
    fun onCreateGetNombreFab(fabricante: Short?) {
        viewModelScope.launch(Dispatchers.Default) {
            val resultado = fabricanteUsecase.getNombreFabricante(fabricante)
            if (resultado != null) {
                fabricanteModel.postValue(resultado)
            }
        }
    }
}