package com.advantys.brsmovilidaderp.UI.ViewModels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Domain.Models.TarifaArticulo
import com.advantys.brsmovilidaderp.Domain.UseCases.TarifaArticulo_UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TarifaArticulo_ViewModel @Inject constructor(private val tarifaArticulo_UseCase: TarifaArticulo_UseCase) : ViewModel(){
    val tarifasArticuloModel= MutableLiveData<List<TarifaArticulo>>()
    @RequiresApi(Build.VERSION_CODES.O)
    fun onCreate(articulo:String?, fabricante:Short?){
        viewModelScope.launch(Dispatchers.Default) {
            val resultado= tarifaArticulo_UseCase(articulo,fabricante)
            if(!resultado.isNullOrEmpty()) tarifasArticuloModel.postValue(resultado)
        }
    }
}