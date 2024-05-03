package com.advantys.brsmovilidaderp.Domain.UseCases

import android.os.Build
import androidx.annotation.RequiresApi
import com.advantys.brsmovilidaderp.Data.Repositories.Configuracion_Repository
import javax.inject.Inject

class Configuracion_UseCase @Inject constructor(private val repository: Configuracion_Repository)  {

   @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getTerminal(): Int {
       return repository.getTerminal()
    }
}