package com.advantys.brsmovilidaderp.Data.DataBase.Daos

import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Clientes_Schema
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.RutaClientes_Schema
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Rutas_Schema

class RutaClientes_Dao {
    private fun ObtenerWhere(dias: diasSemana,marcado: Boolean, desmarcado:Boolean):String{

        var sql= "WHERE"

//        if(!marcado && desmarcado){
//            sql= "${Clientes_Schema.LMARCADO_FIELD} = 0 AND"
//
//        }
//        if (!desmarcado && marcado){
//            sql= "${Clientes_Schema.LMARCADO_FIELD} = 1 AND"
//        }
//        sql= "${RutaClientes_Schema.RUTA_FIELD} = ${Rutas_Schema.RUTA_FIELD} AND "


//     if(dias!=diasSemana.todos) {
//         sql= "("
//         var haydia:Boolean= false
//         when(dias){
//
//             diasSemana.lunes-> {
//                 sql= "${RutaClientes_Schema.DIASEMANA_FIELD} = 'L' OR "
//                 haydia= true
//             }
//             diasSemana.martes-> {
//                 sql= "${RutaClientes_Schema.DIASEMANA_FIELD} = 'M' OR "
//                 haydia= true
//             }
//             diasSemana.miercoles-> {
//                 sql= "${RutaClientes_Schema.DIASEMANA_FIELD} = 'X' OR "
//                 haydia=true
//             }
//             diasSemana.jueves->{
//                 sql= "${RutaClientes_Schema.DIASEMANA_FIELD} = 'J' OR "
//                 haydia=true
//             }
//             diasSemana.viernes->{
//                 sql= "${RutaClientes_Schema.DIASEMANA_FIELD} = 'V' OR "
//                 haydia=true
//             }
//             diasSemana.sabado-> {
//                 sql= "${RutaClientes_Schema.DIASEMANA_FIELD} = 'S' OR "
//                 haydia= true
//             }
//             diasSemana.domingo->{
//                 sql= "${RutaClientes_Schema.DIASEMANA_FIELD} = 'D' OR "
//                 haydia=true
//             }
//
//             else -> {}
//
//         }
//         if(haydia){
//             sql= sql.substring(0,sql.length-2)
//             sql= ") AND"
//         }else{
//             sql= sql.substring(0, sql.length-1)
//         }
//     }else{
//         sql= sql.substring(0,sql.length-1)
//     }
//        sql= "${Rutas_Schema.LMARCADO_FIELD} = 1 AND"

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
        sql= "${Clientes_Schema.CLIENTE_FIELD} = ${RutaClientes_Schema.RUTACLIENTE_FIELD} AND ${Clientes_Schema.DELEGACION_FIELD} = ${RutaClientes_Schema.DELEGACION_FIELD} AND  (${RutaClientes_Schema.TIPORUTA_FIELD} = 'P' OR ${RutaClientes_Schema.TIPORUTA_FIELD} IS NULL) AND"

//        if(VarGlobales.ModoVenta.equals("R")) Consulta += " CLIENTES.NCLIENTE = CABPEDIDOS.NCLIENTE AND CLIENTES.NDELEGACION = CABPEDIDOS.NDELEGACION AND";
//
//        Consulta = Consulta.substring(0, Consulta.length() -3);
//        return Consulta;
        return sql
    }


    private fun obtenerConsultaClientes(dias: diasSemana,ordenar: ordenarPor,sql: String, marcado:Boolean, desmarcado: Boolean):String{

        var sql=""
        sql= "SELECT DISTINCT ${Clientes_Schema.NOMBRE_FIELD}, ${Clientes_Schema.CLIENTE_FIELD} FROM ${Clientes_Schema.TABLE_NAME}, ${RutaClientes_Schema.TABLE_NAME}, ${Rutas_Schema.TABLE_NAME}"
        ObtenerWhere(dias,marcado, desmarcado)

        sql= " ORDER BY "
        when(ordenar){
            ordenarPor.ruta-> sql= "${Clientes_Schema.TIENEPEDIDO_FIELD}, ${RutaClientes_Schema.RUTA_FIELD}, ${RutaClientes_Schema.SECUENCIA_FIELD}, ${Clientes_Schema.CLIENTE_FIELD}, ${Clientes_Schema.DELEGACION_FIELD}"
            ordenarPor.secuencia-> sql= "${Clientes_Schema.TIENEPEDIDO_FIELD}, ${RutaClientes_Schema.SECUENCIA_FIELD}, ${Clientes_Schema.CLIENTE_FIELD}, ${Clientes_Schema.DELEGACION_FIELD}"
            ordenarPor.cliente-> sql= "${Clientes_Schema.TIENEPEDIDO_FIELD}, ${Clientes_Schema.CLIENTE_FIELD}, ${Clientes_Schema.DELEGACION_FIELD}"
            ordenarPor.nombre->sql= "${Clientes_Schema.TIENEPEDIDO_FIELD}, ${Clientes_Schema.NOMBRE_FIELD}"
            ordenarPor.ordenpersonalizado-> sql= "${Clientes_Schema.TIENEPEDIDO_FIELD}, ${Clientes_Schema.ORDEN_FIELD}"
        }
        return sql
    }

}
//enum class ordenarPor{
//    ruta,
//    cliente,
//    nombre,
//    secuencia,
//    ordenpersonalizado
//}
//
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