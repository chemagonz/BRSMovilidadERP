package com.advantys.brsmovilidaderp.Domain.UseCases

import android.os.Build
import androidx.annotation.RequiresApi
import com.advantys.brsmovilidaderp.Data.Repositories.TarifasArticulo_Repository
import com.advantys.brsmovilidaderp.Domain.Models.TarifaArticulo
import javax.inject.Inject

class TarifaArticulo_UseCase @Inject constructor(private val repository: TarifasArticulo_Repository) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(articulo:String?): List<TarifaArticulo>{
        val articulo = repository.getTarifa(articulo)
        return if(articulo.isNullOrEmpty())
            listOf<TarifaArticulo>()
        else articulo
    }
}