package com.advantys.brsmovilidaderp.UI.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.advantys.brsmovilidaderp.Domain.UseCases.TipoOperacion_UseCase

class TipoOperacionViewModelFactory(private val tipoOperacionUsecase: TipoOperacion_UseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TipoOperacion_ViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TipoOperacion_ViewModel(tipoOperacionUsecase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}