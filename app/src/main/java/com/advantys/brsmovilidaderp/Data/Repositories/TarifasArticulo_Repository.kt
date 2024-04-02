package com.advantys.brsmovilidaderp.Data.Repositories

import android.os.Build
import androidx.annotation.RequiresApi
import com.advantys.brsmovilidaderp.Data.DataBase.Daos.TarifasArticulo_Dao
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.TarifasArticulos_Entity
import com.advantys.brsmovilidaderp.Domain.Models.TarifaArticulo
import com.advantys.brsmovilidaderp.Domain.Models.toDomain
import javax.inject.Inject

class TarifasArticulo_Repository @Inject constructor(private val tarifasArticulos_Dao: TarifasArticulo_Dao) {

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getTarifa(articulo:String?, fabricante: Short?):List<TarifaArticulo>{
        val response: List<TarifasArticulos_Entity?> = tarifasArticulos_Dao.getTarifa(articulo, fabricante)
        return response.filterNotNull().map { it.toDomain() }
    }
}