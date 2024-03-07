package com.advantys.brsmovilidaderp.UI.ViewModels

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Domain.Models.Serie
import com.advantys.brsmovilidaderp.Domain.UseCases.Serie_UseCase
import com.advantys.brsmovilidaderp.UI.Views.Series.DetallesSerie_Activity
import kotlinx.coroutines.launch

class Serie_ViewModel (private val serieUsecase: Serie_UseCase):ViewModel(){
    val serieModel = MutableLiveData<List<Serie>>()

    fun onCreate(){
        viewModelScope.launch{
            val resultado = serieUsecase()
            if(!resultado.isNullOrEmpty()) serieModel.postValue(resultado)
        }
    }


    fun btnDetalle(item: Serie?, context: Context){
        val intent = Intent(context, DetallesSerie_Activity::class.java)
        context.startActivity(intent)
    }

}