package com.advantys.brsmovilidaderp.UI.ViewModels

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Domain.Models.Tarifa
import com.advantys.brsmovilidaderp.Domain.UseCases.Tarifa_UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Tarifa_ViewModel @Inject constructor(private val tarifa_UseCase: Tarifa_UseCase) : ViewModel() {
    val tarifasModel= MutableLiveData<List<Tarifa>>()
    fun onCreate(tarifa:Int?){
        viewModelScope.launch(Dispatchers.Default) {
            val resultado= tarifa_UseCase(tarifa)
            if(!resultado.isNullOrEmpty()) tarifasModel.postValue(resultado)
        }
    }

    fun GetNombre(item: Tarifa?){
        val intent= Intent()
        intent.putExtra("numTarifa", item?.numTarifa)
    }
}