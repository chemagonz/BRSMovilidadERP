package com.advantys.brsmovilidaderp.UI.ViewModels

import androidx.lifecycle.ViewModel
import com.advantys.brsmovilidaderp.Domain.UseCases.CabPedido_UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CabPedidos_ViewModel  @Inject constructor (private var cabPedidoUseCase: CabPedido_UseCase): ViewModel() {

    //Campos
//    private val opcion
//        get() = Opcion
//
//
//    private val tipo : TipoVenta = TODO()
//
//
//    fun getOpcion(): Opcion {
//         return opcion
//    }
//
//    fun getTipo(): TipoVenta {
//        return tipo
//    }

}