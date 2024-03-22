package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Clientes_Entity
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Clientes_Schema
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.RutaClientes_Schema
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Rutas_Schema
import com.advantys.brsmovilidaderp.Utils.BDUtil
import com.advantys.brsmovilidaderp.Utils.Utils
import com.advantys.brsmovilidaderp.Utils.dias
import com.advantys.brsmovilidaderp.Utils.mostrarPor
import com.advantys.brsmovilidaderp.Utils.ordenarPor
import javax.inject.Inject

class Clientes_Dao @Inject constructor(private val databaseManager: BDUtil){
    fun getAll():List<Clientes_Entity?>{
        var sql= "SELECT ${Clientes_Schema.CLIENTE_FIELD}, ${Clientes_Schema.NOMBRE_FIELD},${Clientes_Schema.DIRECCION_FIELD}, ${Clientes_Schema.CODIGOPOSTAL_FIELD},${Clientes_Schema.PROVINCIA_FIELD},${Clientes_Schema.POBLACION_FIELD} FROM ${Clientes_Schema.TABLE_NAME} ORDER BY ${Clientes_Schema.CLIENTE_FIELD} DESC"
        return databaseManager.query(sql){ cursor ->
            Clientes_Entity.fromCursor(cursor)
        }
    }
    fun getFilter(columna: columnas,tipoConsulta:String?):List<Clientes_Entity?>{
        var tipoconsulta = tipoConsulta
        val columnas= when(columna){
            columnas.Nombre-> {
                tipoconsulta= "'%${tipoConsulta}%'"
                Clientes_Schema.NOMBRE_FIELD}
            columnas.Codigo-> {
                tipoconsulta= "'${tipoConsulta}%'"
                Clientes_Schema.CLIENTE_FIELD}
            columnas.todo-> return  getAll()
        }

        var sql= "SELECT * FROM ${Clientes_Schema.TABLE_NAME} WHERE ${columnas} LIKE ${tipoconsulta}"
        return databaseManager.query(sql){cursor ->
            Clientes_Entity.fromCursor(cursor)
        }
    }

    fun getDetalles(cliente: Int?): Clientes_Entity?{
        var sql= "SELECT ${Clientes_Schema.CLIENTE_FIELD}, ${Clientes_Schema.NIF_FIELD}, ${Clientes_Schema.NOMBRE_FIELD}, ${Clientes_Schema.RAZON_SOCIAL},${Clientes_Schema.DIRECCION_FIELD},${Clientes_Schema.PROVINCIA_FIELD},${Clientes_Schema.POBLACION_FIELD}, ${Clientes_Schema.CODIGOPOSTAL_FIELD}, ${Clientes_Schema.TELEFONO1_FIELD}, ${Clientes_Schema.TELEFONO2_FIELD} FROM ${Clientes_Schema.TABLE_NAME} WHERE ${Clientes_Schema.CLIENTE_FIELD} = '${cliente}'"
        return databaseManager.queryDetalles(sql){ cursor ->
            Clientes_Entity.fromCursor(cursor)
        }
    }

    fun updateMarcado(cliente: Int?, valor:Boolean?, delegacion:Int?){
        val valorConvertido = if (valor == true) 1 else 0
        var sql= "UPDATE ${Clientes_Schema.TABLE_NAME} SET  ${Clientes_Schema.LMARCADO_FIELD} ='${valorConvertido}' WHERE ${Clientes_Schema.CLIENTE_FIELD} = '${cliente}' AND ${Clientes_Schema.DELEGACION_FIELD} = '${delegacion}'"
        databaseManager.queryUp(sql)
    }

    fun updateDesmarcado(){
        var sql= "UPDATE ${Clientes_Schema.TABLE_NAME} SET ${Clientes_Schema.LMARCADO_FIELD} = 0"
        databaseManager.queryUp(sql)
    }
    //dias: diasSemana,marcado: Boolean, desmarcado:Boolean PONER EN OBTENER WHERE
    private fun ObtenerWhere():String{
        var sql = "WHERE "
        try {
            when(Utils.mostrar){
                mostrarPor.desmarcado->{
                    sql+= "${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.LMARCADO_FIELD} = 0 AND "
                }
                mostrarPor.marcado->{
                    sql+= "${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.LMARCADO_FIELD} = 1 AND "
                }
                else->false
            }
//        if(mostrarPor.marcado && Utils.desmarcado){
//            sql+= "${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.LMARCADO_FIELD} = 0 AND"
//
//       }
//        if (!Utils.desmarcado && Utils.marcado){
//            sql += "${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.LMARCADO_FIELD} = 1 AND"
//        }
            sql += "${RutaClientes_Schema.TABLE_NAME}.${RutaClientes_Schema.RUTA_FIELD} = ${Rutas_Schema.TABLE_NAME}.${Rutas_Schema.RUTA_FIELD} AND "

            if (Utils.diasSeleccionados[dias.todos] != true) {
                sql += " ("
                var haydia = false
                if (Utils.diasSeleccionados[dias.lunes] == true) {
                    sql += "${RutaClientes_Schema.DIASEMANA_FIELD} = 'L' OR "
                    haydia = true
                }
                if (Utils.diasSeleccionados[dias.martes] == true) {
                    sql += "${RutaClientes_Schema.DIASEMANA_FIELD} = 'M' OR "
                    haydia = true
                }
                if (Utils.diasSeleccionados[dias.miercoles] == true) {
                    sql += "${RutaClientes_Schema.DIASEMANA_FIELD} = 'X' OR "
                    haydia = true
                }
                if (Utils.diasSeleccionados[dias.jueves] == true) {
                    sql += "${RutaClientes_Schema.DIASEMANA_FIELD} = 'J' OR "
                    haydia = true
                }
                if (Utils.diasSeleccionados[dias.viernes] == true) {
                    sql += "${RutaClientes_Schema.DIASEMANA_FIELD} = 'V' OR "
                    haydia = true
                }
                if (Utils.diasSeleccionados[dias.sabado] == true) {
                    sql += "${RutaClientes_Schema.DIASEMANA_FIELD} = 'S' OR "
                    haydia = true
                }
                if (Utils.diasSeleccionados[dias.domingo] == true) {
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
        sql+= " ${Rutas_Schema.TABLE_NAME}.${Rutas_Schema.LMARCADO_FIELD} = 1 AND "

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
        }catch (e: Exception){
            e.printStackTrace()
        }
//        if(VarGlobales.ModoVenta.equals("R")) Consulta += " CLIENTES.NCLIENTE = CABPEDIDOS.NCLIENTE AND CLIENTES.NDELEGACION = CABPEDIDOS.NDELEGACION AND";
//
       //Consulta = Consulta.substring(0, Consulta.length() -3);

//        return Consulta;
        return sql
    }

   // dias: diasSemana,marcado:Boolean, desmarcado: Boolean PONER EN OBTENER CONSULTA CUANDO SE ASGINE
     fun obtenerConsultaClientes(ordenar: ordenarPor, mostrarPor: mostrarPor):List<Clientes_Entity?>{

       //var  sql=  "SELECT DISTINCT ${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.NOMBRE_FIELD},${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.CLIENTE_FIELD}, ${RutaClientes_Schema.TABLE_NAME}.${RutaClientes_Schema.RUTA_FIELD}, ${RutaClientes_Schema.TABLE_NAME}.${RutaClientes_Schema.DIASEMANA_FIELD}, ${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.LMARCADO_FIELD} FROM ${Clientes_Schema.TABLE_NAME},${RutaClientes_Schema.TABLE_NAME},${Rutas_Schema.TABLE_NAME} "
       var  sql=  "SELECT DISTINCT ${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.NOMBRE_FIELD},${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.CLIENTE_FIELD},${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.LMARCADO_FIELD}, ${Clientes_Schema.TABLE_NAME}.${Clientes_Schema.DELEGACION_FIELD} FROM ${Clientes_Schema.TABLE_NAME},${RutaClientes_Schema.TABLE_NAME},${Rutas_Schema.TABLE_NAME} "
        sql += ObtenerWhere()
//        sql= " ORDER BY "
//        when(ordenar){
//            ordenarPor.ruta-> sql= "${Clientes_Schema.TIENEPEDIDO_FIELD}, ${RutaClientes_Schema.RUTA_FIELD}, ${RutaClientes_Schema.SECUENCIA_FIELD}, ${Clientes_Schema.CLIENTE_FIELD}, ${Clientes_Schema.DELEGACION_FIELD}"
//            ordenarPor.secuencia-> sql= "${Clientes_Schema.TIENEPEDIDO_FIELD}, ${RutaClientes_Schema.SECUENCIA_FIELD}, ${Clientes_Schema.CLIENTE_FIELD}, ${Clientes_Schema.DELEGACION_FIELD}"
//            ordenarPor.cliente-> sql= "${Clientes_Schema.TIENEPEDIDO_FIELD}, ${Clientes_Schema.CLIENTE_FIELD}, ${Clientes_Schema.DELEGACION_FIELD}"
//            ordenarPor.nombre->sql= "${Clientes_Schema.TIENEPEDIDO_FIELD}, ${Clientes_Schema.NOMBRE_FIELD}"
//            ordenarPor.ordenpersonalizado-> sql= "${Clientes_Schema.TIENEPEDIDO_FIELD}, ${Clientes_Schema.ORDEN_FIELD}"
//        }
        return databaseManager.query(sql){ cursor ->
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

//enum class diasSemana{
//    lunes,
//    martes,
//    miercoles,
//    jueves,
//    viernes,
//    sabado,
//    domingo,
//    todos
//}