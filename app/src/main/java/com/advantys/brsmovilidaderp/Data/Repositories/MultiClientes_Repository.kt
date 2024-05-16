package com.advantys.brsmovilidaderp.Data.Repositories

import com.advantys.brsmovilidaderp.Data.DataBase.Daos.MultiClientes_Dao
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Clientes_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.MultiClientes_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.toEntity
import com.advantys.brsmovilidaderp.Domain.Models.Cliente
import com.advantys.brsmovilidaderp.Domain.Models.MultiCliente
import com.advantys.brsmovilidaderp.Domain.Models.toDomain
import javax.inject.Inject

class MultiClientes_Repository @Inject constructor(private val multiClienteDao: MultiClientes_Dao)  {

    suspend fun getAllmultiClientes():List<MultiCliente>{
        val response: List<MultiClientes_Entity?> = multiClienteDao.getAll()
        return response.filterNotNull().map { it.toDomain() }
    }

    suspend fun getDetallesMulticlientes(multiCliente: Int?):MultiCliente?{
        val response: MultiClientes_Entity? = multiClienteDao.getDetalles(multiCliente)
        return response?.toDomain()
    }

    suspend fun codigoFabricante(multiClienteFab: Short?): MultiCliente? {
        val response: MultiClientes_Entity? = multiClienteDao.codigoFabricante(multiClienteFab)
        return response?.toDomain()
    }

    suspend fun obtenerMultiClientes(cliente: Cliente, fabricante: Int): MultiCliente? {
        val response: MultiClientes_Entity?  = multiClienteDao.obtenerMultiCliente(cliente.toEntity(),fabricante)
        return response?.toDomain()
    }

}