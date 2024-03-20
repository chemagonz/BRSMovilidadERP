package com.advantys.brsmovilidaderp.Data.Repositories

import com.advantys.brsmovilidaderp.Data.DataBase.Daos.Clientes_Dao
import com.advantys.brsmovilidaderp.Data.DataBase.Daos.columnas
import com.advantys.brsmovilidaderp.Data.DataBase.Daos.ordenarPor
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Clientes_Entity
import com.advantys.brsmovilidaderp.Domain.Models.Cliente
import com.advantys.brsmovilidaderp.Domain.Models.toDomain
import javax.inject.Inject

class Clientes_Repository @Inject constructor(private val ClienteDao: Clientes_Dao) {
    suspend fun getAllClientes():List<Cliente>{
        val response: List<Clientes_Entity?> = ClienteDao.getAll()
        return response.filterNotNull().map { it.toDomain() }
    }
    suspend fun getFilter(columna: columnas, tipoConsulta: String?):List<Cliente>{
        val response :List<Clientes_Entity?> =ClienteDao.getFilter(columna,tipoConsulta)
        return response.filterNotNull().map { it.toDomain() }
    }
    suspend fun obtenerConsultaCliente(ordenar: ordenarPor):List<Cliente>{
        val response : List<Clientes_Entity?> = ClienteDao.obtenerConsultaClientes(ordenar)
        if (response.isNullOrEmpty()){
            return emptyList()
        }else{
            return response.filterNotNull().map { it.toDomain() }
        }

    }
}