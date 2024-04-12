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

    fun borrarCobros(fecha:String){
        val sql= " DELETE FROM ${Cobros_Schema.TABLE_NAME} "
        val where= " WHERE ${Cobros_Schema.TABLE_NAME}.${Cobros_Schema.FECHA_FIELD} < '${fecha}' "
        databaseManager.delete(sql,where)
    }

    fun borrarCargaCero(){
        val sql= " DELETE FROM ${DetHojas_Schema.TABLE_NAME} "
        val where= " WHERE ${DetHojas_Schema.TABLE_NAME}.${DetHojas_Schema.CARGA1_FIELD} = 0 "
        databaseManager.delete(sql,where)
    }

    fun borrarVisitas(){
        val sql= " DELETE FROM ${Visitas_Schema.TABLE_NAME}"
        databaseManager.delete(sql)
    }

    fun borrarHojaCarga(){
        val sql= " DELETE FROM ${DetHojas_Schema.TABLE_NAME} "
        databaseManager.delete(sql)
    }

    fun borrarVentas(fecha:String){
        val sql1= " DELETE FROM ${DetPedidos_Schema.TABLE_NAME} "
        val where1= " WHERE ${DetPedidos_Schema.TABLE_NAME}.${DetPedidos_Schema.SERIE_FIELD} || ' ' || ${DetPedidos_Schema.TABLE_NAME}.${DetPedidos_Schema.PEDIDO_FIELD} IN (SELECT  ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.SERIE_FIELD} || ' ' || ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.PEDIDO_FIELD} FROM ${CabPedidos_Schema.TABLE_NAME} WHERE ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.DPREVENTA_FIELD} < '${fecha}'"
        val sql2= " DELETE FROM ${CabPedidos_Schema.TABLE_NAME} "
        val where2= " WHERE ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.DPREVENTA_FIELD} < '${fecha}'"
        databaseManager.delete(sql1,where1)
        databaseManager.delete(sql2,where2)
    }

    fun borrarRegistrosSueltos(){
        val sql= " DELETE FROM ${DetPedidos_Schema.TABLE_NAME} "
        val where= " WHERE ${DetPedidos_Schema.TABLE_NAME}.${DetPedidos_Schema.SERIE_FIELD} || ' ' || ${DetPedidos_Schema.TABLE_NAME}.${DetPedidos_Schema.PEDIDO_FIELD} NOT IN (SELECT  ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.SERIE_FIELD} || ' ' || ${CabPedidos_Schema.TABLE_NAME}.${CabPedidos_Schema.PEDIDO_FIELD} FROM ${CabPedidos_Schema.TABLE_NAME} "
        databaseManager.delete(sql,where)
    }

}