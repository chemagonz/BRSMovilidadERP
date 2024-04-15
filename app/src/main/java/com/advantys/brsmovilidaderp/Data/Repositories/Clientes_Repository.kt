package com.advantys.brsmovilidaderp.Data.Repositories

import com.advantys.brsmovilidaderp.Data.DataBase.Daos.Clientes_Dao
import com.advantys.brsmovilidaderp.Data.DataBase.Daos.columnas
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Clientes_Entity
import com.advantys.brsmovilidaderp.Domain.Models.Cliente
import com.advantys.brsmovilidaderp.Domain.Models.toDomain
import com.advantys.brsmovilidaderp.Utils.MostrarPor
import com.advantys.brsmovilidaderp.Utils.OrdenarPor

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
    suspend fun obtenerConsultaCliente(ordenar: OrdenarPor, mostrarPor: MostrarPor):List<Cliente>{
        val response : List<Clientes_Entity?> = ClienteDao.obtenerConsultaClientes(ordenar,mostrarPor)
        if (response.isNullOrEmpty()){
            return emptyList()
        }else{
            return response.filterNotNull().map { it.toDomain() }
        }
    }
    suspend fun getDetalles(cliente:Int?):Cliente?{
        val response: Clientes_Entity? = ClienteDao.getDetalles(cliente)
        return response?.toDomain()
    }
    suspend fun updateMarcado(cliente:Int?,valor:Boolean?, delegacion: Int?){
            ClienteDao.updateMarcado(cliente,valor,delegacion)
    }
    suspend fun updateDesmarcado(){
         ClienteDao.updateDesmarcado()
    }
}