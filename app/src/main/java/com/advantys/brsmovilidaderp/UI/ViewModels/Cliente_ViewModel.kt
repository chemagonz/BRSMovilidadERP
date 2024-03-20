package com.advantys.brsmovilidaderp.UI.ViewModels

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Data.DataBase.Daos.columnas
import com.advantys.brsmovilidaderp.Data.DataBase.Daos.ordenarPor
import com.advantys.brsmovilidaderp.Domain.Models.Cliente
import com.advantys.brsmovilidaderp.Domain.UseCases.Cliente_UseCase
import com.advantys.brsmovilidaderp.UI.Views.Clientes.DetallesClientes_Activity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Cliente_ViewModel @Inject constructor(private val ClienteUsecase: Cliente_UseCase): ViewModel() {
    val ClientesModel= MutableLiveData<List<Cliente>>()
    val clienteModel= MutableLiveData<Cliente?>()
    fun onCreate(){ viewModelScope.launch(Dispatchers.Default) {
        val resultado= ClienteUsecase()
        if(!resultado.isNullOrEmpty()) ClientesModel.postValue(resultado)
        }
    }
    fun onCreateDetalles(cliente: Int?){ viewModelScope.launch(Dispatchers.Default) {
        val resultado= ClienteUsecase(cliente)
        if (resultado != null) {
            clienteModel.postValue(resultado)
        }
    }}
    fun buscarClientes(tipo:columnas=columnas.Nombre, query:String){ viewModelScope.launch(Dispatchers.Default) {
            val resultado= ClienteUsecase(tipo, query)
            if (!resultado.isNullOrEmpty()) ClientesModel.postValue(resultado)
        }
    }
    //Se inicializa por ordenar ruta, valor predeterminado
    fun obtenerConsultaClientes(ordenar:ordenarPor=ordenarPor.ruta){ viewModelScope.launch(Dispatchers.Default) {
            val resultado= ClienteUsecase(ordenar)
            //if(!resultado.isNullOrEmpty()) ClientesModel.postValue(resultado)
            ClientesModel.postValue(resultado)
        }
    }
    fun updateMarcado(cliente:Int?,valor:Boolean?) {
        viewModelScope.launch(Dispatchers.Default) {
            ClienteUsecase(cliente,valor)
        }
    }

    fun btnDetalle(item: Cliente?, context: Context) {
        val intent = Intent(context, DetallesClientes_Activity::class.java)
        intent.putExtra("numClientes", item?.numClientes)
        context.startActivity(intent)
    }

}