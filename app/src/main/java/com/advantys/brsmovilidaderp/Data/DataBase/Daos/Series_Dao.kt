package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Series_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Series_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil
import com.advantys.brsmovilidaderp.Utils.TipoVenta
import javax.inject.Inject


class Series_Dao @Inject constructor( private val databaseManager:BDUtil) {

    //Se tiene que inyectar clase CabPedidos_Dao para la llamada de la función comprobarExistencia


    fun getAll():List<Series_Entity?>{
        var sql= "SELECT * FROM ${Series_Schema.TABLE_NAME} ORDER BY ${Series_Schema.SERIE_FIELD} DESC"
        return databaseManager.query(sql){ cursor ->
            Series_Entity.fromCursor(cursor)
        }
    }
    fun getSerie(serie:String?):Series_Entity? {
        var sql = "SELECT ${Series_Schema.NOMBRE_FIELD} FROM ${Series_Schema.TABLE_NAME} WHERE ${Series_Schema.SERIE_FIELD} = '${serie}'"
        return databaseManager.queryDetalles(sql){ cursor ->
            Series_Entity.fromCursor(cursor)
        }
    }
    fun getAllDetalles(serie: String?):Series_Entity?{
        var sql= "SELECT * FROM ${Series_Schema.TABLE_NAME} WHERE ${Series_Schema.SERIE_FIELD} ='${serie}'"
    return databaseManager.queryDetalles(sql) { cursor ->
        Series_Entity.fromCursor(cursor)
        }
    }

    fun getNombreSerie(serie:String?):Series_Entity? {
        var sql = "SELECT ${Series_Schema.NOMBRE_FIELD},${Series_Schema.SERIE_FIELD} FROM ${Series_Schema.TABLE_NAME} WHERE ${Series_Schema.SERIE_FIELD} = '${serie}'"
        return databaseManager.queryDetalles(sql){ cursor ->
            Series_Entity.fromCursor(cursor)
        }
    }

    fun serieValida(serie: String): Boolean {
        val sql = "SELECT ${Series_Schema.TABLE_NAME}.${Series_Schema.SERIE_FIELD} FROM ${Series_Schema.TABLE_NAME} WHERE ${Series_Schema.TABLE_NAME}.${Series_Schema.SERIE_FIELD} = '$serie' "
        return databaseManager.getSelectScalarBoolean(sql)
    }

    fun primeraSerieVentas(): String {
        val sql = " SELECT ${Series_Schema.TABLE_NAME}.${Series_Schema.SERIE_FIELD} FROM ${Series_Schema.TABLE_NAME} ORDER BY ${Series_Schema.TABLE_NAME}.${Series_Schema.SERIE_FIELD} "
        return databaseManager.getSelectScalarString(sql)
    }

    fun obtenerSiguienteDocumento (tipo: TipoVenta, serie: String): Int {
        var siguiente = -1

        val sql = "SELECT " + campoSegunTipo(tipo) + " FROM ${Series_Schema.TABLE_NAME} WHERE ${Series_Schema.TABLE_NAME}.${Series_Schema.SERIE_FIELD} = '$serie'"
        return databaseManager.getSelectScalarInt(sql)

        siguiente++
//        siguiente = cabpedidosDao.comprobarExistencia(tipo, serie, siguiente)
        actualizarUltimoDocumento(tipo, serie, siguiente)
    }
    private fun campoSegunTipo(tipo: TipoVenta): String {
        var campo = ""
        when (tipo) {
            TipoVenta.pedido -> campo = "${Series_Schema.TABLE_NAME}.${Series_Schema.ULTPEDIDO_FIELD}"
            TipoVenta.albaran -> campo = "${Series_Schema.TABLE_NAME}.${Series_Schema.ULTALBARAN_FIELD}"
            TipoVenta.factura -> campo = "${Series_Schema.TABLE_NAME}.${Series_Schema.ULTFACTURA_FIELD}"
            else -> ""
        }
        return campo
    }

    fun actualizarUltimoDocumento(tipo: TipoVenta, serie: String, siguiente: Int) {
        val sql = "UPDATE SERIES SET " + campoSegunTipo(tipo) + " = " + siguiente + " WHERE ${Series_Schema.TABLE_NAME}.${Series_Schema.SERIE_FIELD} = '$serie'"
        databaseManager.queryUp(sql)
    }




}