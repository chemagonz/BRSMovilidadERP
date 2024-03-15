package com.advantys.brsmovilidaderp.UI.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Data.DataBase.Daos.columnas
import com.advantys.brsmovilidaderp.Data.DataBase.Daos.ordenarPor
import com.advantys.brsmovilidaderp.Domain.Models.Cliente
import com.advantys.brsmovilidaderp.Domain.UseCases.Cliente_UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Cliente_ViewModel @Inject constructor(private val ClienteUsecase: Cliente_UseCase): ViewModel() {
    val ClientesModel= MutableLiveData<List<Cliente>>()
    fun onCreate(){
        viewModelScope.launch(Dispatchers.Default) {
            val resultado= ClienteUsecase()
            if(!resultado.isNullOrEmpty()) ClientesModel.postValue(resultado)
        }
    }
    fun buscarClientes(tipo:columnas, query:String){
        viewModelScope.launch(Dispatchers.Default) {
            val resultado= ClienteUsecase(tipo, query)
            if (!resultado.isNullOrEmpty()) ClientesModel.postValue(resultado)
        }
    }

    fun obtenerConsultaClientes(ordenar:ordenarPor){
        viewModelScope.launch(Dispatchers.Default) {
            val resultado= ClienteUsecase(ordenar)
            if(!resultado.isNullOrEmpty()) ClientesModel.postValue(resultado)
        }
    }
}