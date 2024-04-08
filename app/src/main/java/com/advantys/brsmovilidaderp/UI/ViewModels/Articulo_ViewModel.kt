package com.advantys.brsmovilidaderp.UI.ViewModels

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Domain.Models.Articulo
import com.advantys.brsmovilidaderp.Domain.UseCases.Articulo_UseCase
import com.advantys.brsmovilidaderp.UI.Views.Articulos.DetallesArticulos_Activity
import com.advantys.brsmovilidaderp.Utils.buscarArticulosPor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Articulo_ViewModel @Inject constructor(private var articuloUsecase: Articulo_UseCase): ViewModel() {
    val articulosModel = MutableLiveData<List<Articulo>>()
    val articuloModel = MutableLiveData<Articulo>()


    fun onCreate(){
        viewModelScope.launch(Dispatchers.Default) {
            val resultado= articuloUsecase()
            if(!resultado.isNullOrEmpty()) articulosModel.postValue(resultado)
        }
    }
    fun onCreateDetalles(articulo: String?, fabricante:Short?) {
        viewModelScope.launch(Dispatchers.Default) {
            val resultado = articuloUsecase.detalles(articulo,fabricante)
            if (resultado != null) {
                articuloModel.postValue(resultado)
            }
        }
    }
    fun btnDetalles(item: Articulo?, context: Context){
        val intent = Intent(context, DetallesArticulos_Activity::class.java)
        intent.putExtra("articulo", item?.articulo)
        intent.putExtra("fabricante", item?.fabricante)
        context.startActivity(intent)
    }
    fun buscarArticulos(columnas: buscarArticulosPor,query:String){ viewModelScope.launch(Dispatchers.Default) {
        viewModelScope.launch(Dispatchers.Default) {
            val resultado = articuloUsecase(columnas,query)
            if (resultado.isEmpty() && query.isNotEmpty()) { // Verificar si la lista está vacía y la consulta no lo está
                articulosModel.postValue(emptyList()) // Asignar una lista vacía a ClientesModel
            } else {
                articulosModel.postValue(resultado)
            }
        }
    }
    }
    fun buscarArticulosFiltro(buscarArticulosPor: buscarArticulosPor, codfamilia: Short?=null, codsubfamilia:Short?=null, codformato:Int?=null, codmarca:String?=null, codsabor:String?=null, tipoConsulta: String?=null){
        viewModelScope.launch(Dispatchers.Default) {
            val resultado= articuloUsecase(buscarArticulosPor, codfamilia, codsubfamilia, codformato, codmarca, codsabor, tipoConsulta)
            if(!resultado.isNullOrEmpty()){
                articulosModel.postValue(resultado)
            }else{
                articulosModel.postValue(emptyList())
            }
        }
    }

}