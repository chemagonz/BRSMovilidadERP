package com.advantys.brsmovilidaderp.Data.Repositories

import com.advantys.brsmovilidaderp.Data.DataBase.Daos.Articulos_Dao
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Articulos_Entity
import com.advantys.brsmovilidaderp.Domain.Models.Articulo
import com.advantys.brsmovilidaderp.Domain.Models.toDomain
import com.advantys.brsmovilidaderp.Utils.BuscarArticulosPor
import javax.inject.Inject

class Articulos_Repository @Inject constructor (private val articulosDao: Articulos_Dao) {

    suspend fun obtenerTodosArticulos():List<Articulo>{
        val response: List<Articulos_Entity?> = articulosDao.obtenerTodosArticulos()
        return response.filterNotNull().map { it.toDomain() }
    }

    suspend fun obtenerDetalles(articulo: String?): Articulo? {
        val response : Articulos_Entity? = articulosDao.obtenerDetalles(articulo)
        return response?.toDomain()
    }

    suspend fun obtenerArticulos(buscarArticulosPor: BuscarArticulosPor, codfamilia: Short?, codsubfamilia:Short?, codformato:Int?, codmarca:String?, codsabor:String?, tipoConsulta: String?):List<Articulo> {
        val response : List<Articulos_Entity?> = articulosDao.obtenerArticulos(buscarArticulosPor,codfamilia,codsubfamilia,codformato,codmarca,codsabor,tipoConsulta)
        return response.filterNotNull().map { it.toDomain() }
    }
}