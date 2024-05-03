package com.advantys.brsmovilidaderp.UI.ViewModels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Domain.UseCases.Configuracion_UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Configuracion_ViewModel @Inject constructor(private var configUsecase: Configuracion_UseCase): ViewModel() {
    private val _terminal = MutableLiveData<Int>()
    val terminal: LiveData<Int> = _terminal
    @RequiresApi(Build.VERSION_CODES.O)
    fun getTerminal() {
        viewModelScope.launch(Dispatchers.IO) {
            val terminal = configUsecase.getTerminal()
            _terminal.postValue(terminal)
        }
    }
}