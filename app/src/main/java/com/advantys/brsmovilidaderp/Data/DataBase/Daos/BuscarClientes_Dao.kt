package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import android.content.Context
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.BuscarClientes_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.BuscarClientes_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil

class BuscarClientes_Dao (context: Context){
    private val databaseManager: BDUtil = BDUtil(context)

    fun getAll():List<BuscarClientes_Entity?>{
        var sql= "SELECT * FROM ${BuscarClientes_Schema.TABLE_NAME} ORDER BY ${BuscarClientes_Schema.CLIENTE_FIELD} DESC"
        return databaseManager.query(sql){ cursor ->
            BuscarClientes_Entity.fromCursor(cursor)
        }
    }

    fun getFilterNombre(nombre:String?):List <BuscarClientes_Entity?>{
        var sql= "SELECT * FROM ${BuscarClientes_Schema.TABLE_NAME} WHERE ${BuscarClientes_Schema.NOMBRE_FIELD} LIKE '%$nombre%'"
        return databaseManager.query(sql){ cursor ->
            BuscarClientes_Entity.fromCursor(cursor)
        }
    }

    fun getFilterCodigo(codigo: String?):List<BuscarClientes_Entity?>{
        var sql = "SELECT * FROM ${BuscarClientes_Schema.TABLE_NAME} WHERE ${BuscarClientes_Schema.CLIENTE_FIELD} LIKE '%$codigo%'"
        return databaseManager.query(sql){ cursor ->
            BuscarClientes_Entity.fromCursor(cursor)
        }
    }

}