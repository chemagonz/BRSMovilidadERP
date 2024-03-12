package com.advantys.brsmovilidaderp.UI.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Data.DataBase.Daos.columnas
import com.advantys.brsmovilidaderp.Domain.Models.Cliente
import com.advantys.brsmovilidaderp.Domain.UseCases.BuscarCliente_UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BuscarCliente_ViewModel (private val buscarClienteUsecase: BuscarCliente_UseCase): ViewModel() {
    val buscarClientesModel= MutableLiveData<List<Cliente>>()

    fun onCreate(){
        viewModelScope.launch(Dispatchers.Default) {
            val resultado= buscarClienteUsecase()
            if(!resultado.isNullOrEmpty()) buscarClientesModel.postValue(resultado)
        }
    }

    fun buscarClientes(tipo:columnas, query:String){
        viewModelScope.launch(Dispatchers.Default) {
            val resultado= buscarClienteUsecase(tipo, query)
            if (!resultado.isNullOrEmpty()) buscarClientesModel.postValue(resultado)
        }
    }
}

class BuscarClienteViewModelFactoy(private val buscarClienteUsecase: BuscarCliente_UseCase): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BuscarCliente_ViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BuscarCliente_ViewModel(buscarClienteUsecase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}