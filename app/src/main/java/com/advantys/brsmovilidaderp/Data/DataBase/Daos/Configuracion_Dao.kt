package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import android.os.Build
import androidx.annotation.RequiresApi
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Configuracion_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil
import javax.inject.Inject

class Configuracion_Dao @Inject constructor(private val databaseManager: BDUtil){

    @RequiresApi(Build.VERSION_CODES.O)
    fun getTerminal() : Int {
        val sql = "SELECT ${Configuracion_Schema.TERMINAL_FIELD} FROM ${Configuracion_Schema.TABLE_NAME}"
        return databaseManager.getScalInt(sql)
    }

}