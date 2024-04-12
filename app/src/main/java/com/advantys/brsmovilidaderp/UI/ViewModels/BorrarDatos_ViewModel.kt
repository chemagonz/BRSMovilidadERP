package com.advantys.brsmovilidaderp.UI.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Domain.UseCases.BorrarDatos_UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class BorrarDatos_ViewModel @Inject constructor(private var borrarDatosUsecase: BorrarDatos_UseCase): ViewModel() {
    fun borrarCobros(fecha:String){
        viewModelScope.launch(Dispatchers.Default) {
            borrarDatosUsecase.borrarCobros(fecha)
        }
    }
    fun borrarCargaCero(){
        viewModelScope.launch(Dispatchers.Default) {
            borrarDatosUsecase.borrarCargaCero()
        }
    }
    fun borrarVisitas(){
        viewModelScope.launch(Dispatchers.Default) {
            borrarDatosUsecase.borrarVisitas()
        }
    }
    fun borrarHojaCarga(){
        viewModelScope.launch(Dispatchers.Default) {
            borrarDatosUsecase.borrarHojaCarga()
        }
    }
    fun borrarVentas(fecha:String){
        viewModelScope.launch(Dispatchers.Default) {
            borrarDatosUsecase.borrarVentas(fecha)
        }
    }
   fun borrarRegistrosSueltos(){
       viewModelScope.launch(Dispatchers.Default) {
           borrarDatosUsecase.borrarRegistrosSueltos()
       }
    }
}