package com.advantys.brsmovilidaderp.UI.ViewModels.Centro_ViewModel

import Centro_ViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.advantys.brsmovilidaderp.Domain.UseCases.Centro_UseCase

class CentroViewModelFactory (private val centroUseCase: Centro_UseCase) : ViewModelProvider.Factory {

   override fun <T : ViewModel> create(modelClass: Class<T>): T {
       if (modelClass.isAssignableFrom(Centro_ViewModel::class.java)) {
           @Suppress("UNCHECKED_CAST")
           return Centro_ViewModel(centroUseCase) as T
      }
       throw IllegalArgumentException("Unknown ViewModel class")
   }
}