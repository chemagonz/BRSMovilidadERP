package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Clientes_Schema
import java.time.format.DateTimeFormatter

data class Clientes_Entity(
    var nClientes: Int?=null,
    var delegacion: Int?=null,
    var nombre: String?=null,
    var razonSocial: String?=null,
    var direccion: String?=null,
    var codPostal: String?=null,
    var poblacion: String?=null,
    var provincia: String?=null,
    var NIF: String?=null,
    var telefono1: String?=null,
    var telefono2: String?=null,
    var regimenIva: String?=null,
    var copiasAlb: Int?=null,
    var copiasFac: Int?=null,
    var categoria: Int?=null,
    var formaPago: Int?=null,
    var tarifa: Int?=null,
    var riesgoConsumo: Float?=null,
    var impoRiesgo: Float?=null,
    var diasRiesgo: Int?=null,
    var docsRiesgo: Int?=null,
    var riesgoImpo: String?=null,
    var riesgoDias: String?=null,
    var riesgoDocs: String?=null,
    var aplicaPromgral: String?=null,
    var centro: Int?=null,
    var facturaEnvases: String?=null,
    var aplicaAlcoholes: String?=null,
    var aplicapVerde: String?=null,
    var porCDTO: Float?=null,
    var serieAlbaran: String?=null,
    var facturable: String?=null,
    var tipoOperacion: Int?=null,
    var facturaInmediata: String?=null,
    var ultVenta: DateTimeFormatter?=null,
    var clienteFiscal: Int?=null,
    var delegacionFiscal: Int?=null,
    var DTOvtacaj: Float?=null,
    var DTOportes: Float?=null,
    var consumoAnt: Float?=null,
    var consumoAct: Float?=null,
    var desviacion: Float?=null,
    var serieFactura: String?=null,
    var sector: Int?=null,
    var actividad: Int?=null,
    var avisos: String?=null,
    var estado: Int?=null,
    var lmarcado: Boolean?=null,
    var cmarcado: String?=null,
    var aplexcart: Boolean?=null,
    var tipoEnvaseSemanal: String?=null,
    var latitud: String?=null,
    var longitud: String?=null,
    var pendiente: Int?=null,
    var deuda: DateTimeFormatter?=null,
    var ultPedido: Boolean?=null,
    var pendienteValidar: String?=null,
    var limiteRiesgo: DateTimeFormatter?=null,
    var tiplinenvaut: String?=null,
    var aplicaManipulacion: String?=null,
    var correo:String?=null,
    var aplicaAzucar:String?= null,
    var orden: Int?=null) {

    companion object{
        //Completar cuando se pueda

        fun fromCursor(cursor: Cursor): Clientes_Entity {
            var modelo = Clientes_Entity()

            if(cursor.isNull(cursor.getColumnIndexOrThrow(Clientes_Schema.CLIENTE_FIELD))){
                    modelo.nClientes = cursor.getInt(cursor.getColumnIndexOrThrow(Clientes_Schema.CLIENTE_FIELD))
                }
            else {
                    modelo.nClientes=null
            }
            if(cursor.isNull(cursor.getColumnIndexOrThrow(Clientes_Schema.NOMBRE_FIELD))){
                    modelo.nombre = cursor.getString(cursor.getColumnIndexOrThrow(Clientes_Schema.NOMBRE_FIELD))
                }else{
                    modelo.nombre=null
            }
            if(cursor.isNull(cursor.getColumnIndexOrThrow(Clientes_Schema.DIRECCION_FIELD))){
                    modelo.direccion = cursor.getString(cursor.getColumnIndexOrThrow(Clientes_Schema.DIRECCION_FIELD))
                }
            modelo.codPostal = cursor.getString((cursor.getColumnIndexOrThrow(Clientes_Schema.CODIGOPOSTAL_FIELD)))
            modelo.provincia = cursor.getString((cursor.getColumnIndexOrThrow(Clientes_Schema.PROVINCIA_FIELD)))
            modelo.poblacion = cursor.getString((cursor.getColumnIndexOrThrow(Clientes_Schema.POBLACION_FIELD)))
            return modelo
        }

    }

}