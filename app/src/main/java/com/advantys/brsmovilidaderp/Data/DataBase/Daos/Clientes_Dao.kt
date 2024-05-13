package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Clientes_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.RutaClientes_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Clientes_Schema
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Fabricante_Schema
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.MultiClientes_Schema
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.RutaClientes_Schema
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Rutas_Schema
import com.advantys.brsmovilidaderp.Domain.Models.Cliente
import com.advantys.brsmovilidaderp.Utils.BDUtil
import com.advantys.brsmovilidaderp.Utils.Dias
import com.advantys.brsmovilidaderp.Utils.MostrarPor
import com.advantys.brsmovilidaderp.Utils.OrdenarPor
import com.advantys.brsmovilidaderp.Utils.diasSeleccionados
import com.advantys.brsmovilidaderp.Utils.mostrar
import javax.inject.Inject


class Clientes_Dao @Inject constructor(private val databaseManager: BDUtil) {
    fun getAll(): List<Clientes_Entity?> {
        var sql =
            "SELECT ${Clientes_Schema.CLIENTE_FIELD}, ${Clientes_Schema.NOMBRE_FIELD},${Clientes_Schema.DIRECCION_FIELD}, ${Clientes_Schema.CODIGOPOSTAL_FIELD},${Clientes_Schema.PROVINCIA_FIELD},${Clientes_Schema.POBLACION_FIELD} FROM ${Clientes_Schema.TABLE_NAME} ORDER BY ${Clientes_Schema.CLIENTE_FIELD} DESC"
        return databaseManager.query(sql) { cursor ->
            Clientes_Entity.fromCursor(cursor)
        }
    }

    fun getFilter(columna: columnas, tipoConsulta: String?): List<Clientes_Entity?> {
        var tipoconsulta = tipoConsulta
        val columnas = when (columna) {
            columnas.Nombre -> {
                tipoconsulta = "'%${tipoConsulta}%'"
                Clientes_Schema.NOMBRE_FIELD
            }

            columnas.Codigo -> {
                tipoconsulta = "'${tipoConsulta}%'"
                Clientes_Schema.CLIENTE_FIELD
            }

            columnas.todo -> return getAll()
        }

        var sql =
            "SELECT * FROM ${Clientes_Schema.TABLE_NAME} WHERE ${columnas} LIKE ${tipoconsulta}"
        return databaseManager.query(sql) { cursor ->
            Clientes_Entity.fromCursor(cursor)
        }
    }

    fun getDetalles(cliente: Int?): Clientes_Entity? {
        var sql =
            "SELECT ${Clientes_Schema.CLIENTE_FIELD}, ${Clientes_Schema.NIF_FIELD}, ${Clientes_Schema.NOMBRE_FIELD}, ${Clientes_Schema.RAZON_SOCIAL},${Clientes_Schema.DIRECCION_FIELD},${Clientes_Schema.PROVINCIA_FIELD},${Clientes_Schema.POBLACION_FIELD}, ${Clientes_Schema.CODIGOPOSTAL_FIELD}, ${Clientes_Schema.TELEFONO1_FIELD}, ${Clientes_Schema.TELEFONO2_FIELD} FROM ${Clientes_Schema.TABLE_NAME} WHERE ${Clientes_Schema.CLIENTE_FIELD} = '${cliente}'"
        return databaseManager.queryDetalles(sql) { cursor ->
            Clientes_Entity.fromCursor(cursor)
        }
    }

    fun updateMarcado(cliente: Int?, valor: Boolean?, delegacion: Short?) {
        val valorConvertido = if (valor == true) 1 else 0
        var sql =
            "UPDATE ${Clientes_Schema.TABLE_NAME} SET  ${Clientes_Schema.LMARCADO_FIELD} =$valorConvertido WHERE ${Clientes_Schema.CLIENTE_FIELD} = $cliente AND ${Clientes_Schema.DELEGACION_FIELD} = $delegacion"
        databaseManager.queryUp(sql)
    }

    fun updateDesmarcado() {
        var sql = "UPDATE ${Clientes_Schema.TABLE_NAME} SET ${Clientes_Schema.LMARCADO_FIELD} = 0"
        databaseManager.queryUp(sql)
    }

    //dias: diasSemana,marcado: Boolean, desmarcado:Boolean PONER EN OBTENER WHERE
    private fun ObtenerWhere(): String {
        var sql = "WHERE "
        try {
            when (mostrar) {
                MostrarPor.desmarcado -> {
                    sql += "${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.LMARCADO_FIELD} = 0 AND "
                }

                MostrarPor.marcado -> {
                    sql += "${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.LMARCADO_FIELD} = 1 AND "
                }

                else -> false
            }
//        if(mostrarPor.marcado && Utils.desmarcado){
//            sql+= "${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.LMARCADO_FIELD} = 0 AND"
//
//       }
//        if (!Utils.desmarcado && Utils.marcado){
//            sql += "${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.LMARCADO_FIELD} = 1 AND"
//        }
            sql += "${RutaClientes_Schema.TABLE_NAME}.${RutaClientes_Schema.RUTA_FIELD} = ${Rutas_Schema.TABLE_NAME}.${Rutas_Schema.RUTA_FIELD} AND "

            if (diasSeleccionados[Dias.todos] != true) {
                sql += " ("
                var haydia = false
                if (diasSeleccionados[Dias.lunes] == true) {
                    sql += "${RutaClientes_Schema.DIASEMANA_FIELD} = 'L' OR "
                    haydia = true
                }
                if (diasSeleccionados[Dias.martes] == true) {
                    sql += "${RutaClientes_Schema.DIASEMANA_FIELD} = 'M' OR "
                    haydia = true
                }
                if (diasSeleccionados[Dias.miercoles] == true) {
                    sql += "${RutaClientes_Schema.DIASEMANA_FIELD} = 'X' OR "
                    haydia = true
                }
                if (diasSeleccionados[Dias.jueves] == true) {
                    sql += "${RutaClientes_Schema.DIASEMANA_FIELD} = 'J' OR "
                    haydia = true
                }
                if (diasSeleccionados[Dias.viernes] == true) {
                    sql += "${RutaClientes_Schema.DIASEMANA_FIELD} = 'V' OR "
                    haydia = true
                }
                if (diasSeleccionados[Dias.sabado] == true) {
                    sql += "${RutaClientes_Schema.DIASEMANA_FIELD} = 'S' OR "
                    haydia = true
                }
                if (diasSeleccionados[Dias.domingo] == true) {
                    sql += "${RutaClientes_Schema.DIASEMANA_FIELD} = 'D' OR "
                    haydia = true
                }
                if (haydia) {
                    sql = sql.substring(0, sql.length - 3)
                    sql += ") AND"
                } else {
                    sql = sql.substring(0, sql.length - 1)
                }
            } else {
                sql = sql.substring(0, sql.length - 1)
            }
            sql += " ${Rutas_Schema.TABLE_NAME}.${Rutas_Schema.LMARCADO_FIELD} = 1 AND "

//        // Condiciones por tipo de ruta
//        if(!VarGlobales.esNuloVacio(VarGlobales.ModoVenta) && VarGlobales.ModoVenta.equals("A"))
//        {
//            //Verifica si se está usando el rutero extendido, en cuyo caso el tipo de ruta es "P" aunque estemos en autoventa
//            if (VarGlobales.lEsRuteroExtendido)
//            {
//                Consulta += " CLIENTES.NCLIENTE = RUTACLIENTES.NCLIENTE AND CLIENTES.NDELEGACION = RUTACLIENTES.NDELEGACION AND (RUTACLIENTES.CTIPORUTA = 'P' OR RUTACLIENTES.CTIPORUTA IS NULL) AND";
//            }
//            else
//            {
//                Consulta += " CLIENTES.NCLIENTE = RUTACLIENTES.NCLIENTE AND CLIENTES.NDELEGACION = RUTACLIENTES.NDELEGACION AND (RUTACLIENTES.CTIPORUTA = 'R' OR RUTACLIENTES.CTIPORUTA IS NULL) AND";
//            }
//        }

            sql += " ${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.CLIENTE_FIELD} = ${RutaClientes_Schema.TABLE_NAME}.${Clientes_Schema.CLIENTE_FIELD} AND ${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.DELEGACION_FIELD} = ${RutaClientes_Schema.TABLE_NAME}.${RutaClientes_Schema.DELEGACION_FIELD} AND  (${RutaClientes_Schema.TABLE_NAME}.${RutaClientes_Schema.TIPORUTA_FIELD} = 'P' OR ${RutaClientes_Schema.TIPORUTA_FIELD} IS NULL)"
        } catch (e: Exception) {
            e.printStackTrace()
        }
//        if(VarGlobales.ModoVenta.equals("R")) Consulta += " CLIENTES.NCLIENTE = CABPEDIDOS.NCLIENTE AND CLIENTES.NDELEGACION = CABPEDIDOS.NDELEGACION AND";
//
        //Consulta = Consulta.substring(0, Consulta.length() -3);

//        return Consulta;
        return sql
    }

    // dias: diasSemana,marcado:Boolean, desmarcado: Boolean PONER EN OBTENER CONSULTA CUANDO SE ASGINE
    fun obtenerConsultaClientes(
        ordenar: OrdenarPor,
        mostrarPor: MostrarPor
    ): List<Clientes_Entity?> {

        //var  sql=  "SELECT DISTINCT ${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.NOMBRE_FIELD},${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.CLIENTE_FIELD}, ${RutaClientes_Schema.TABLE_NAME}.${RutaClientes_Schema.RUTA_FIELD}, ${RutaClientes_Schema.TABLE_NAME}.${RutaClientes_Schema.DIASEMANA_FIELD}, ${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.LMARCADO_FIELD} FROM ${Clientes_Schema.TABLE_NAME},${RutaClientes_Schema.TABLE_NAME},${Rutas_Schema.TABLE_NAME} "
        var sql =
            "SELECT DISTINCT ${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.NOMBRE_FIELD},${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.CLIENTE_FIELD},${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.LMARCADO_FIELD}, ${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.DELEGACION_FIELD}, ${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.TIENEPEDIDO_FIELD}, ${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.ORDEN_FIELD}, ${RutaClientes_Schema.TABLE_NAME}.${RutaClientes_Schema.RUTA_FIELD}, ${RutaClientes_Schema.TABLE_NAME}.${RutaClientes_Schema.SECUENCIA_FIELD} FROM ${Clientes_Schema.TABLE_NAME},${RutaClientes_Schema.TABLE_NAME},${Rutas_Schema.TABLE_NAME} "
        sql += ObtenerWhere()
        sql += " ORDER BY "
        when (ordenar) {
            OrdenarPor.ruta -> sql += "${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.TIENEPEDIDO_FIELD}, ${RutaClientes_Schema.TABLE_NAME}.${RutaClientes_Schema.RUTA_FIELD}, ${RutaClientes_Schema.TABLE_NAME}.${RutaClientes_Schema.SECUENCIA_FIELD}, ${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.CLIENTE_FIELD}, ${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.DELEGACION_FIELD}"
            OrdenarPor.secuencia -> sql += "${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.TIENEPEDIDO_FIELD}, ${RutaClientes_Schema.TABLE_NAME}.${RutaClientes_Schema.SECUENCIA_FIELD}, ${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.CLIENTE_FIELD}, ${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.DELEGACION_FIELD}"
            OrdenarPor.cliente -> sql += "${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.TIENEPEDIDO_FIELD}, ${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.CLIENTE_FIELD}, ${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.DELEGACION_FIELD}"
            OrdenarPor.nombre -> sql += "${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.TIENEPEDIDO_FIELD}, ${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.NOMBRE_FIELD}"
            OrdenarPor.ordenpersonalizado -> sql += "${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.TIENEPEDIDO_FIELD}, ${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.ORDEN_FIELD}"
        }
        return databaseManager.query(sql) { cursor ->
            Clientes_Entity.fromCursor(cursor)
        }
    }

    fun getNombre(cliente: Int?): Clientes_Entity? {
        var sql =
            "SELECT   ${Clientes_Schema.NOMBRE_FIELD}  FROM ${Clientes_Schema.TABLE_NAME} WHERE ${Clientes_Schema.CLIENTE_FIELD} = '${cliente}'"
        return databaseManager.queryDetalles(sql) { cursor ->
            Clientes_Entity.fromCursor(cursor)
        }
    }

    fun guardarOrdenClientes(cliente: Int?, delegacion: Short?, orden: Int?) {
        val sql =
            "UPDATE ${Clientes_Schema.TABLE_NAME} SET ${Clientes_Schema.ORDEN_FIELD} = $orden  WHERE ${Clientes_Schema.CLIENTE_FIELD} = $cliente AND ${Clientes_Schema.DELEGACION_FIELD} = $delegacion"
        databaseManager.queryUp(sql)
    }

    fun guardarNullOrdenClientes() {
        var sql = "UPDATE ${Clientes_Schema.TABLE_NAME} SET ${Clientes_Schema.ORDEN_FIELD} = NULL"
        databaseManager.queryUp(sql)
    }

    fun obtenerEstadoCliente(cliente: Cliente): Int {
        val sql =
            "SELECT ${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.ESTADO_FIELD} FROM ${Clientes_Schema.TABLE_NAME} WHERE ${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.CLIENTE_FIELD} = ${cliente.numClientes} AND ${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.DELEGACION_FIELD} = ${cliente.delegacion}"
        return databaseManager.getSelectScalarInt(sql)
    }


    //RUTA CLIENTES SQL

    //Obtiene la Ruta del cliente por dia.
    fun rutaPorDia(cliente: Cliente, dia: String): RutaClientes_Entity? {
        val sql =
            "SELECT ${RutaClientes_Schema.TABLE_NAME}.${RutaClientes_Schema.RUTA_FIELD}, ${RutaClientes_Schema.TABLE_NAME}.${RutaClientes_Schema.SECUENCIA_FIELD} FROM ${RutaClientes_Schema.TABLE_NAME} WHERE ${RutaClientes_Schema.TABLE_NAME}.${RutaClientes_Schema.RUTACLIENTE_FIELD} = ${cliente.numClientes} AND ${RutaClientes_Schema.TABLE_NAME}.${RutaClientes_Schema.DELEGACION_FIELD} = ${cliente.delegacion} AND (${RutaClientes_Schema.TABLE_NAME}.${RutaClientes_Schema.TIPORUTA_FIELD} = 'P' OR ${RutaClientes_Schema.TABLE_NAME}.${RutaClientes_Schema.TIPORUTA_FIELD} IS NULL) AND ${RutaClientes_Schema.TABLE_NAME}.${RutaClientes_Schema.DIASEMANA_FIELD} = '$dia' "
        return databaseManager.queryDetalles(sql) { cursor ->
            RutaClientes_Entity.fromCursor(cursor)
        }
    }

    // Obtiene la Ruta del cliente sin tener en cuenta el dia.

    fun rutaSinDia(cliente: Cliente): RutaClientes_Entity? {
        val sql =
            "SELECT ${RutaClientes_Schema.TABLE_NAME}.${RutaClientes_Schema.RUTA_FIELD}, ${RutaClientes_Schema.TABLE_NAME}.${RutaClientes_Schema.SECUENCIA_FIELD} FROM ${RutaClientes_Schema.TABLE_NAME} WHERE ${RutaClientes_Schema.TABLE_NAME}.${RutaClientes_Schema.RUTACLIENTE_FIELD} = ${cliente.numClientes} AND ${RutaClientes_Schema.TABLE_NAME}.${RutaClientes_Schema.DELEGACION_FIELD} = ${cliente.delegacion} AND (${RutaClientes_Schema.TABLE_NAME}.${RutaClientes_Schema.TIPORUTA_FIELD} = 'P' OR ${RutaClientes_Schema.TABLE_NAME}.${RutaClientes_Schema.TIPORUTA_FIELD} IS NULL) AND ${RutaClientes_Schema.TABLE_NAME}.${RutaClientes_Schema.DIASEMANA_FIELD} IS NULL "
        return databaseManager.queryDetalles(sql) { cursor ->
            RutaClientes_Entity.fromCursor(cursor)
        }
    }

    // Obtiene la ruta y secuencia de reparto por dia.

//    fun RutaRepartoPorDia(Cliente: PUNTOSVENTA, dia: String): Bundle {
//        val respuesta = Bundle()
//        respuesta.putString("Ruta", "")
//        respuesta.putString("Secuencia", "")
//        var Consulta = ""
//        Consulta =
//            if (VarGlobales.lEsRuteroExtendido) "SELECT CRR, CSR FROM RUTACLIENTES WHERE NCLIENTE = " + java.lang.String.valueOf(
//                Cliente.getNCLIENTE()
//            ) + " AND NDELEGACION = " + java.lang.String.valueOf(Cliente.getNDELEGACION()) + " AND CTIPORUTA = 'P' AND CDIAREP = '" + dia + "'" else "SELECT CRUTA, CSECUENCIA FROM RUTACLIENTES WHERE NCLIENTE = " + java.lang.String.valueOf(
//                Cliente.getNCLIENTE()
//            ) + " AND NDELEGACION = " + java.lang.String.valueOf(Cliente.getNDELEGACION()) + " AND CTIPORUTA = 'R' AND CDIASEMANA = '" + dia + "'"
//        var c: Cursor? = null
//        try {
//            db = ManBD.openDataBase()
//            c = db.rawQuery(Consulta, null)
//            if (c != null) {
//                if (c.moveToFirst()) {
//                    respuesta.remove("Ruta")
//                    respuesta.remove("Secuencia")
//                    if (c.getString(0) == null) respuesta.putString(
//                        "Ruta",
//                        ""
//                    ) else respuesta.putString("Ruta", c.getString(0))
//                    if (c.getString(1) == null) respuesta.putString(
//                        "Secuencia",
//                        "0000"
//                    ) else respuesta.putString("Secuencia", c.getString(1))
//                }
//            }
//        } catch (ex: java.lang.Exception) {
//            ErrorListener.OnErrorBD(ex.toString(), "RutaRepartoPorDia")
//            respuesta.remove("Ruta")
//            respuesta.remove("Secuencia")
//            respuesta.putString("Ruta", "")
//            respuesta.putString("Secuencia", "")
//        } finally {
//            if (c != null) c.close()
//            if (db.isOpen()) db.close()
//        }
//        return respuesta
//    }

    // Obtiene la ruta y secuencia de reparto sin tener en cuenta el dia.

//    fun RutaRepartoSinDia(Cliente: PUNTOSVENTA): Bundle {
//        val respuesta = Bundle()
//        respuesta.putString("Ruta", "")
//        respuesta.putString("Secuencia", "")
//        var Consulta = ""
//        Consulta =
//            if (VarGlobales.lEsRuteroExtendido) "SELECT CRR, CSR FROM RUTACLIENTES WHERE NCLIENTE = " + java.lang.String.valueOf(
//                Cliente.getNCLIENTE()
//            ) + " AND NDELEGACION = " + java.lang.String.valueOf(Cliente.getNDELEGACION()) + " AND CTIPORUTA = 'P' AND CDIASEMANA IS NULL" else "SELECT CRUTA, CSECUENCIA FROM RUTACLIENTES WHERE NCLIENTE = " + java.lang.String.valueOf(
//                Cliente.getNCLIENTE()
//            ) + " AND NDELEGACION = " + java.lang.String.valueOf(Cliente.getNDELEGACION()) + " AND CTIPORUTA = 'R' AND CDIASEMANA IS NULL"
//        var c: Cursor? = null
//        try {
//            db = ManBD.openDataBase()
//            c = db.rawQuery(Consulta, null)
//            if (c != null) {
//                if (c.moveToFirst()) {
//                    respuesta.remove("Ruta")
//                    respuesta.remove("Secuencia")
//                    if (c.getString(0) == null) respuesta.putString(
//                        "Ruta",
//                        ""
//                    ) else respuesta.putString("Ruta", c.getString(0))
//                    if (c.getString(1) == null) respuesta.putString(
//                        "Secuencia",
//                        "0000"
//                    ) else respuesta.putString("Secuencia", c.getString(1))
//                }
//            }
//        } catch (ex: java.lang.Exception) {
//            ErrorListener.OnErrorBD(ex.toString(), "RutaRepartoSinDia")
//            respuesta.remove("Ruta")
//            respuesta.remove("Secuencia")
//            respuesta.putString("Ruta", "")
//            respuesta.putString("Secuencia", "")
//        } finally {
//            if (c != null) c.close()
//            if (db.isOpen()) db.close()
//        }
//        return respuesta
//    }

    // Obtiene la primera ruta y secuencia de reparto para el cliente.

//    fun RutaRepartoPrimera(Cliente: PUNTOSVENTA): Bundle {
//        val respuesta = Bundle()
//        respuesta.putString("Ruta", "")
//        respuesta.putString("Secuencia", "")
//        var Consulta = ""
//        Consulta =
//            if (VarGlobales.lEsRuteroExtendido) "SELECT CRR, CSR FROM RUTACLIENTES WHERE NCLIENTE = " + java.lang.String.valueOf(
//                Cliente.getNCLIENTE()
//            ) + " AND NDELEGACION = " + java.lang.String.valueOf(Cliente.getNDELEGACION()) + " AND CTIPORUTA = 'P'" else "SELECT CRUTA, CSECUENCIA FROM RUTACLIENTES WHERE NCLIENTE = " + java.lang.String.valueOf(
//                Cliente.getNCLIENTE()
//            ) + " AND NDELEGACION = " + java.lang.String.valueOf(Cliente.getNDELEGACION()) + " AND CTIPORUTA = 'R'"
//        var c: Cursor? = null
//        try {
//            db = ManBD.openDataBase()
//            c = db.rawQuery(Consulta, null)
//            if (c != null) {
//                if (c.moveToFirst()) {
//                    respuesta.remove("Ruta")
//                    respuesta.remove("Secuencia")
//                    if (c.getString(0) == null) respuesta.putString(
//                        "Ruta",
//                        ""
//                    ) else respuesta.putString("Ruta", c.getString(0))
//                    if (c.getString(1) == null) respuesta.putString(
//                        "Secuencia",
//                        "0000"
//                    ) else respuesta.putString("Secuencia", c.getString(1))
//                }
//            }
//        } catch (ex: java.lang.Exception) {
//            ErrorListener.OnErrorBD(ex.toString(), "RutaRepartoPrimera")
//            respuesta.remove("Ruta")
//            respuesta.remove("Secuencia")
//            respuesta.putString("Ruta", "")
//            respuesta.putString("Secuencia", "")
//        } finally {
//            if (c != null) c.close()
//            if (db.isOpen()) db.close()
//        }
//        return respuesta
//    }

   //Devuelve si se trata de una venta por fabricante.

   fun ventaPorFabricante(cliente: Cliente): Boolean {
       val sql = " SELECT ${MultiClientes_Schema.TABLE_NAME}.${MultiClientes_Schema.FABRICANTE_FIELD} FROM ${MultiClientes_Schema.TABLE_NAME}, ${Fabricante_Schema.TABLE_NAME} WHERE ${MultiClientes_Schema.TABLE_NAME}.${MultiClientes_Schema.MULTICLIENTE_FIELD} = ${cliente.numClientes} AND ${MultiClientes_Schema.TABLE_NAME}.${MultiClientes_Schema.MULTIDELEGACION_FIELD} = ${cliente.delegacion} AND ${MultiClientes_Schema.TABLE_NAME}.${MultiClientes_Schema.FABRICANTE_FIELD} = ${MultiClientes_Schema.FABRICANTE_FIELD}.${Fabricante_Schema.FABRICANTE_FIELD} AND ${Fabricante_Schema.SEL_MUL_CLI_FIELD} = 1 "
       return databaseManager.getSelectScalarBoolean(sql)
   }

    //Obtiene los datos de un cliente Fiscal.

    fun obtenerClienteFiscal(cliente: Cliente): List<Clientes_Entity> {
        val sql = "SELECT * FROM ${Clientes_Schema.TABLE_NAME} WHERE  ${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.CLIENTE_FIELD} = ${cliente.clienteFiscal} AND  ${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.DELEGACION_FIELD} = ${cliente.delegacionFiscal}"
        return databaseManager.query(sql) { cursor ->
            Clientes_Entity.fromCursor(cursor)
        }
    }



}
//Se implementa una enum class para simplificar mejor la funcion, ya que guardo en una variable dos posibles columnas, asi no tengo que hacer dos veces lo mismo
enum class columnas{
    Nombre,
    Codigo,
    todo
}

