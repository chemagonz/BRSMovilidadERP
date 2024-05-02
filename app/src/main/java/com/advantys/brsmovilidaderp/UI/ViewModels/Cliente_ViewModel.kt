package com.advantys.brsmovilidaderp.UI.ViewModels

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Data.DataBase.Daos.columnas
import com.advantys.brsmovilidaderp.Domain.Models.Cliente
import com.advantys.brsmovilidaderp.Domain.UseCases.Cliente_UseCase
import com.advantys.brsmovilidaderp.UI.Views.Clientes.DetallesClientes_Activity
import com.advantys.brsmovilidaderp.Utils.MostrarPor
import com.advantys.brsmovilidaderp.Utils.OrdenarPor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Cliente_ViewModel @Inject constructor(private val ClienteUsecase: Cliente_UseCase): ViewModel() {
    val ClientesModel= MutableLiveData<List<Cliente>>()
    val clienteModel= MutableLiveData<Cliente?>()
    fun onCreate(){
        viewModelScope.launch(Dispatchers.Default) {
        val resultado= ClienteUsecase()
        if(!resultado.isNullOrEmpty()) ClientesModel.postValue(resultado)
        }
    }
    fun onCreateDetalles(cliente: Int?){

        viewModelScope.launch(Dispatchers.Default) {
        val resultado= ClienteUsecase(cliente)
        if (resultado != null) {
            clienteModel.postValue(resultado)
        }
    }}

    @SuppressLint("SuspiciousIndentation")
    fun onCreateNombre(cliente: Int?){
        viewModelScope.launch(Dispatchers.Default) {
        val resultado= ClienteUsecase.getNombre(cliente)
            clienteModel.postValue(resultado)
    }}
    @SuppressLint("SuspiciousIndentation")
    fun buscarClientes(tipo:columnas=columnas.Nombre, query:String){ viewModelScope.launch(Dispatchers.Default) {
        viewModelScope.launch(Dispatchers.Default) {
            val resultado = ClienteUsecase(tipo, query)
                if (resultado.isEmpty() && query.isNotEmpty()) { // Verificar si la lista está vacía y la consulta no lo está
                    ClientesModel.postValue(emptyList()) // Asignar una lista vacía a ClientesModel
                } else {
                    ClientesModel.postValue(resultado)
                }
            }
        }
    }
    //Se inicializa por ordenar ruta, valor predeterminado
    fun obtenerConsultaClientes(ordenar: OrdenarPor, mostrarPor: MostrarPor){ viewModelScope.launch(Dispatchers.Default) {
            val resultado= ClienteUsecase(ordenar,mostrarPor)
            //if(!resultado.isNullOrEmpty()) ClientesModel.postValue(resultado)
            ClientesModel.postValue(resultado)
        }
    }
    fun updateMarcado(cliente:Int?,valor:Boolean?, delegacion: Int?) {
        viewModelScope.launch(Dispatchers.Default) {
            ClienteUsecase(cliente,valor,delegacion)
        }
    }
    fun updateDesmarcado() {
        viewModelScope.launch(Dispatchers.Default) {
            ClienteUsecase.invokeDesmarcado()
        }
    }
    fun btnDetalle(item: Cliente?, context: Context) {
        val intent = Intent(context, DetallesClientes_Activity::class.java)
        intent.putExtra("numClientes", item?.numClientes)
        context.startActivity(intent)
    }
}