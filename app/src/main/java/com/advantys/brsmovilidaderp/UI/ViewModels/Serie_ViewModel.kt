package com.advantys.brsmovilidaderp.UI.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Domain.Models.Serie
import com.advantys.brsmovilidaderp.Domain.UseCases.Serie_UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Serie_ViewModel @Inject constructor(private val serieUsecase: Serie_UseCase):ViewModel(){
    val serieModel = MutableLiveData<List<Serie>>()

    fun onCreate(){
        viewModelScope.launch{
            val resultado = serieUsecase()
            if(!resultado.isNullOrEmpty()) serieModel.postValue(resultado)
        }
    }
}