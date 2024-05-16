package com.advantys.brsmovilidaderp.UI.ViewModels

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Domain.Models.Cliente
import com.advantys.brsmovilidaderp.Domain.Models.MultiCliente
import com.advantys.brsmovilidaderp.Domain.UseCases.MultiCliente_UseCase
import com.advantys.brsmovilidaderp.UI.Views.MultiClientes.DetallesMultiClientes_Activity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MultiClientes_ViewModel @Inject constructor(private val multiclienteUsecase: MultiCliente_UseCase): ViewModel()  {

    val multiClientesModel= MutableLiveData<List<MultiCliente>>()
    val multiClienteModel= MutableLiveData<MultiCliente?>()

    fun onCreate(){
        viewModelScope.launch(Dispatchers.IO) {
            val resultado= multiclienteUsecase()
            if(resultado.isNotEmpty()) multiClientesModel.postValue(resultado)
        }
    }
    fun onCreateDetalles(multicliente: Int?){
        viewModelScope.launch(Dispatchers.IO) {
            val resultado= multiclienteUsecase(multicliente)
            if (resultado != null) {
                multiClienteModel.postValue(resultado)
            }
        }
    }

    fun codigoFabricante(multiclienteFab: Short?){
        viewModelScope.launch(Dispatchers.IO){
            withContext(Dispatchers.IO){
                val resultado = multiclienteUsecase.codigoFabricante(multiclienteFab)
                if (resultado != null) {
                    multiClienteModel.postValue(resultado)
                }
            }
        }
    }

    fun obtenerMultiClientes(cliente: Cliente, fabricante: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val resultado= multiclienteUsecase.obtenerMultiClientes(cliente,fabricante)
            if (resultado != null) {
                multiClienteModel.postValue(resultado)
            }
        }
    }

    fun btnDetalle(item: MultiCliente?, context: Context) {
        val intent = Intent(context, DetallesMultiClientes_Activity::class.java)
        intent.putExtra("multiCliente", item?.multiCliente)
        context.startActivity(intent)
    }
}