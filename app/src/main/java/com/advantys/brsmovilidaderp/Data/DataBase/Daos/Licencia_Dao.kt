package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import android.content.ContentValues
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Licencia_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Licencia_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil
import javax.inject.Inject

class Licencia_Dao  @Inject constructor(private val databaseManager: BDUtil){

    //INSERT LICENCIA
    fun insertLicencia(licencia: Licencia_Entity): Boolean{
        var ok= false
        borrarLicenciaSiexiste()
        val tabla= Licencia_Schema.TABLE_NAME
        val values= ContentValues()
        values.put(Licencia_Schema.IDENDISP_FIELD, licencia.idenDisp)
        values.put(Licencia_Schema.IDENPROG_FIELD, licencia.idenProg)
        values.put(Licencia_Schema.LICENCIA_FIELD, licencia.licencia)
        values.put(Licencia_Schema.CLIENTE_FIELD, licencia.cliente)
        values.put(Licencia_Schema.NUMLICENCIA_FIELD, licencia.numLicencia)
        databaseManager.insert(tabla,values)
        ok= true
        return ok
    }
    //DELETE LICENCIA YA EXISTENTE
    fun borrarLicenciaSiexiste(){
        val tabla= Licencia_Schema.TABLE_NAME
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