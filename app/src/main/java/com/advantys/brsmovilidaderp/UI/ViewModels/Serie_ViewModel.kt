package com.advantys.brsmovilidaderp.UI.ViewModels

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Domain.Models.Serie
import com.advantys.brsmovilidaderp.Domain.UseCases.Serie_UseCase
import com.advantys.brsmovilidaderp.UI.Views.Series.DetallesSerie_Activity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Serie_ViewModel @Inject constructor(private val serieUsecase: Serie_UseCase):ViewModel(){
    val seriesModel = MutableLiveData<List<Serie>>()
    val serieModel= MutableLiveData<Serie?>()

    fun onCreate(){ viewModelScope.launch{
            val resultado = serieUsecase()
            if(!resultado.isNullOrEmpty()) seriesModel.postValue(resultado)
        }
    }
    fun onCreateDetalles(serie:String?){ viewModelScope.launch (Dispatchers.Default){
            val resultado= serieUsecase(serie)
            if(resultado!=null){
                serieModel.postValue(resultado)
            }
        }
    }

    fun onCreateNombreSerie(serie:String?){
        viewModelScope.launch (Dispatchers.Default){
        val resultado= serieUsecase.getNombreSerie(serie)
        if(resultado!=null){
            serieModel.postValue(resultado)
        }
    }
    }
    fun btnDetalle(item: Serie?, context:Context){
        val intent = Intent(context, DetallesSerie_Activity::class.java)
        intent.putExtra("cSeries", item?.cSeries)
        context.startActivity(intent)
    }
}
