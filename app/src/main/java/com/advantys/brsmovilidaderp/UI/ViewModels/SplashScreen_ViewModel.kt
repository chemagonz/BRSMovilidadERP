package com.advantys.brsmovilidaderp.UI.ViewModels

import androidx.lifecycle.ViewModel
import com.advantys.brsmovilidaderp.Domain.Models.Licencia
import com.advantys.brsmovilidaderp.Domain.UseCases.Licencia_UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreen_ViewModel  @Inject constructor(private val licenciaUsecase: Licencia_UseCase): ViewModel() {

    suspend fun validarLicencia(licencia: Licencia): Boolean {
        var ok = false
        licenciaUsecase.verificarLicencia(licencia)
        ok = true
        return ok
    }

    suspend fun comprobarLicencia(codigo: String): Boolean {
        val licenciaCodigo = licenciaUsecase.ObtenerLicencia(codigo)
        val licencia = Licencia(licencia = licenciaCodigo)
        return validarLicencia(licencia)
    }

}



