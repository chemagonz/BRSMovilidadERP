package com.advantys.brsmovilidaderp.Data.Repositories

import com.advantys.brsmovilidaderp.Data.DataBase.Daos.BuscarClientes_Dao
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.BuscarClientes_Entity
import com.advantys.brsmovilidaderp.Domain.Models.Cliente
import com.advantys.brsmovilidaderp.Domain.Models.toDomain

class BuscarClientes_Repository (private val buscarClienteDao: BuscarClientes_Dao) {

    suspend fun getAllClientes():List<Cliente>{
        val response: List<BuscarClientes_Entity?> = buscarClienteDao.getAll()
        return response.filterNotNull().map { it.toDomain() }
    }

    suspend fun getClientesNombre(nombre:String?):List<Cliente>{
        val response: List<BuscarClientes_Entity?> = buscarClienteDao.getFilterNombre(nombre)
        return response.filterNotNull().map { it.toDomain() }
    }

    suspend fun getClientesCodigo(codigo:String?):List<Cliente>{
        val response : List<BuscarClientes_Entity?> = buscarClienteDao.getFilterCodigo(codigo)
        return response.filterNotNull().map { it.toDomain() }
    }
}