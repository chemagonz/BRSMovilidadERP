package com.advantys.brsmovilidaderp.UI.ViewModels

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Domain.Models.Articulo
import com.advantys.brsmovilidaderp.Domain.UseCases.Articulo_UseCase
import com.advantys.brsmovilidaderp.UI.Views.Articulos.DetallesArticulos_Activity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Articulo_ViewModel @Inject constructor(private var articuloUsecase: Articulo_UseCase): ViewModel() {
    val articulosModel = MutableLiveData<List<Articulo>>()

    fun onCreate(){
        viewModelScope.launch(Dispatchers.Default) {
            val resultado= articuloUsecase()
            if(!resultado.isNullOrEmpty()) articulosModel.postValue(resultado)
        }
    }
    fun btnDetalles(item: Articulo?, context: Context){
        val intent = Intent(context, DetallesArticulos_Activity::class.java)
        intent.putExtra("articulo", item?.articulo)
        context.startActivity(intent)
    }
}