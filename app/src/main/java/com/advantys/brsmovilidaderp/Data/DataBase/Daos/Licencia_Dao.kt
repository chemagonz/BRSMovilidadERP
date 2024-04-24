package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Licencia_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Licencia_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil
import javax.inject.Inject

class Licencia_Dao  @Inject constructor(private val databaseManager: BDUtil){

    //INSERT LICENCIA
    fun insertLicencia(licencia: Licencia_Entity): Boolean{
        var ok = false

        try {
            databaseManager.insert(Licencia_Schema.TABLE_NAME, licencia.toContentValues())
            ok = true
        }catch(e: Exception){
            e.printStackTrace()
        }

        return ok
    }
    //DELETE LICENCIA YA EXISTENTE
    fun borrarLicenciaSiexiste(){
        val tabla = Licencia_Schema.TABLE_NAME
        databaseManager.delete(tabla)
    }

    //OBTENER LICENCIA
//    fun getLicencia(): Licencia_Entity?{
//        val sql= "SELECT * FROM ${Licencia_Schema.TABLE_NAME} WHERE ${Licencia_Schema.IDENPROG_FIELD} = ? "
//        return databaseManager.queryDetalles(sql) { cursor ->
//            Licencia_Entity.fromCursor(cursor)
//        }
//    }
}