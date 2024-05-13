package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.MultiClientes_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Fabricante_Schema
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.MultiClientes_Schema
import com.advantys.brsmovilidaderp.Domain.Models.Cliente
import com.advantys.brsmovilidaderp.Utils.BDUtil
import javax.inject.Inject

class MultiClientes_Dao @Inject constructor(private val databaseManager: BDUtil) {

    fun getAll():List<MultiClientes_Entity?>{
        val sql = "SELECT DISTINCT ${MultiClientes_Schema.TABLE_NAME}.${MultiClientes_Schema.MULTICLIENTE_FIELD}, ${MultiClientes_Schema.TABLE_NAME}.${MultiClientes_Schema.MULTIDELEGACION_FIELD} FROM ${MultiClientes_Schema.TABLE_NAME} "
        return databaseManager.query(sql){ cursor ->
            MultiClientes_Entity.fromCursor(cursor)
        }
    }

    fun getDetalles(multicliente: Int?): MultiClientes_Entity?{
        val sql = "SELECT * FROM ${MultiClientes_Schema.TABLE_NAME} WHERE ${MultiClientes_Schema.MULTICLIENTE_FIELD} = $multicliente "
        return databaseManager.queryDetalles(sql) { cursor ->
            MultiClientes_Entity.fromCursor(cursor)
        }
    }

    fun codigoFabricante(multiClienteFab: Short?) : MultiClientes_Entity?{

        val sql = "SELECT ${MultiClientes_Schema.TABLE_NAME}.${MultiClientes_Schema.FABRICANTE_FIELD},${Fabricante_Schema.TABLE_NAME}.${Fabricante_Schema.FABRICANTE_FIELD}  FROM ${MultiClientes_Schema.TABLE_NAME},${Fabricante_Schema.TABLE_NAME}  WHERE ${MultiClientes_Schema.TABLE_NAME}.${MultiClientes_Schema.FABRICANTE_FIELD} = ${Fabricante_Schema.TABLE_NAME}.${Fabricante_Schema.FABRICANTE_FIELD}"
        return databaseManager.queryDetalles(sql){ cursor ->
            MultiClientes_Entity.fromCursor(cursor)
        }
    }


    //Obtiene datos de Multiclientes de un Cliente en relacion a un Fabricante.
    fun obtenerMultiCliente(cliente: Cliente, fabricante: Int): List<MultiClientes_Entity?> {
        val sql = "SELECT ${MultiClientes_Schema.TABLE_NAME}*, ${Fabricante_Schema.TABLE_NAME}.${Fabricante_Schema.FABRICANTE_FIELD} WHERE ${MultiClientes_Schema.MULTIDELEGACION_FIELD} = ${cliente.numClientes} AND ${MultiClientes_Schema.MULTIDELEGACION_FIELD} = ${cliente.delegacion} AND ${MultiClientes_Schema.FABRICANTE_FIELD} = $fabricante  AND ${Fabricante_Schema.TABLE_NAME}.${Fabricante_Schema.FABRICANTE_FIELD} = ${MultiClientes_Schema.TABLE_NAME}.${MultiClientes_Schema.FABRICANTE_FIELD}"
        return databaseManager.query(sql) { cursor ->
            MultiClientes_Entity.fromCursor(cursor)
        }
    }


}