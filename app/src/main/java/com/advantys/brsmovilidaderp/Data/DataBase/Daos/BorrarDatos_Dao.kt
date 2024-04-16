package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.CabPedidos_Schema
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Cobros_Schema
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.DetHojas_Schema
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.DetPedidos_Schema
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Visitas_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil
import javax.inject.Inject

class BorrarDatos_Dao @Inject constructor(private val databaseManager: BDUtil){

    // ESTA CLASE SE VA A COMPONER POR LAS DIFERENTES CONSULTAS DE: CABPEDIDO, COBROS, DETHOJAS, DETPEDIDOS, VISITAS

    fun borrarCobros(fecha:String):Boolean{
        val sql= " DELETE FROM ${Cobros_Schema.TABLE_NAME} "
        val where= " WHERE ${Cobros_Schema.TABLE_NAME}.${Cobros_Schema.FECHA_FIELD} < '${fecha}' "
       return  databaseManager.delete(sql,where)
    }

    fun borrarCargaCero():Boolean{
        val sql= " DELETE FROM ${DetHojas_Schema.TABLE_NAME} "
        val where= " WHERE ${DetHojas_Schema.TABLE_NAME}.${DetHojas_Schema.CARGA1_FIELD} = 0 "
        return databaseManager.delete(sql,where)
    }

    fun borrarVisitas():Boolean{
        val sql= " DELETE FROM ${Visitas_Schema.TABLE_NAME}"
        return databaseManager.delete(sql)
    }

    fun borrarHojaCarga():Boolean{
        val sql= " DELETE FROM ${DetHojas_Schema.TABLE_NAME} "
        return databaseManager.delete(sql)
    }

    fun borrarVentasDetPedidos(fecha:String):Boolean{
        val sql= " DELETE FROM ${DetPedidos_Schema.TABLE_NAME} "
        val where= " WHERE ${DetPedidos_Schema.TABLE_NAME}.${DetPedidos_Schema.SERIE_FIELD} || ' ' || ${DetPedidos_Schema.TABLE_NAME}.${DetPedidos_Schema.PEDIDO_FIELD} IN (SELECT  ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.SERIE_FIELD} || ' ' || ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.PEDIDO_FIELD} FROM ${CabPedidos_Schema.TABLE_NAME} WHERE ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.DPREVENTA_FIELD} < '${fecha}'"
        return  databaseManager.delete(sql,where)
    }
    fun borrarVentasCabPedidos(fecha:String):Boolean{
        val sql= " DELETE FROM ${CabPedidos_Schema.TABLE_NAME} "
        val where= " WHERE ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.DPREVENTA_FIELD} < '${fecha}'"
        return databaseManager.delete(sql,where)
    }
    fun borrarRegistrosSueltos():Boolean{
        val sql= " DELETE FROM ${DetPedidos_Schema.TABLE_NAME} "
        val where= " WHERE ${DetPedidos_Schema.TABLE_NAME}.${DetPedidos_Schema.SERIE_FIELD} || ' ' || ${DetPedidos_Schema.TABLE_NAME}.${DetPedidos_Schema.PEDIDO_FIELD} NOT IN (SELECT  ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.SERIE_FIELD} || ' ' || ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.PEDIDO_FIELD} FROM ${CabPedidos_Schema.TABLE_NAME} "
       return databaseManager.delete(sql,where)
    }

    fun comprobarDatosPendientesCabPedidos(fecha: String): Int{
       val sql= CabPedidos_Schema.TABLE_NAME
       val where= " ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.ENVIADO_FIELD} = 0 AND ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.IMPORTADO_FIELD} = 0 AND ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.DPREVENTA_FIELD} < '$fecha'"
       return databaseManager.existeInt(sql,where)

    }
    fun comprobarDatosPendientesVisitas(fecha:String): Int {
        val sql=Visitas_Schema.TABLE_NAME
        val where= " ${Visitas_Schema.ENVIADO_FIELD} = 0"
        return databaseManager.existeInt(sql, where)
    }
    fun comprobarDatosPendientesCobros(fecha:String):Int{
        val sql=Cobros_Schema.TABLE_NAME
        val where= "  ${Cobros_Schema.ENVIADO_FIELD} = 0 AND ${Cobros_Schema.FECHA_FIELD} < '$fecha'"
        return databaseManager.existeInt(sql, where)
    }
    fun compactarBD(){
        val sql= "VACUUM"
        databaseManager.queryInsert(sql)
    }
}