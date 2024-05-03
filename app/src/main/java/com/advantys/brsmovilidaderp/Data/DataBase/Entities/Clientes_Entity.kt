package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Clientes_Schema
import com.advantys.brsmovilidaderp.Domain.Models.Cliente
import com.advantys.brsmovilidaderp.Utils.getBoolean
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString
import java.time.format.DateTimeFormatter

data class Clientes_Entity(
    var nClientes: Int?=null,
    var delegacion: Short?=null,
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
        fun fromCursor(cursor: Cursor): Clientes_Entity {
            var modelo = Clientes_Entity()
            modelo.nClientes = cursor.getInt(Clientes_Schema.CLIENTE_FIELD,null)
            modelo.delegacion= cursor.getShort(Clientes_Schema.DELEGACION_FIELD,null)
            modelo.nombre = cursor.getString(Clientes_Schema.NOMBRE_FIELD,null)
            modelo.direccion = cursor.getString(Clientes_Schema.DIRECCION_FIELD,null)
            modelo.codPostal = cursor.getString(Clientes_Schema.CODIGOPOSTAL_FIELD,null)
            modelo.provincia = cursor.getString(Clientes_Schema.PROVINCIA_FIELD,null)
            modelo.poblacion = cursor.getString(Clientes_Schema.POBLACION_FIELD,null)
            modelo.lmarcado= cursor.getBoolean(Clientes_Schema.LMARCADO_FIELD, null)
            modelo.orden= cursor.getInt(Clientes_Schema.ORDEN_FIELD,null)
            return modelo
        }
    }
}
fun Cliente.toEntity() = Clientes_Entity(numClientes, delegacion, nombre, razonSocial, direccion, codPostal, poblacion, provincia, NIF, telefono1, telefono2, regimenIva, copiasAlb, copiasFac, categoria, formaPago, tarifa, riesgoConsumo, impoRiesgo, diasRiesgo, docsRiesgo, riesgoImpo, riesgoDias, riesgoDocs, aplicaPromgral, centro, facturaEnvases, aplicaAlcoholes, aplicapVerde, porCDTO, serieAlbaran, facturable, tipoOperacion, facturaInmediata, ultVenta, clienteFiscal, delegacionFiscal, DTOvtacaj, DTOportes, consumoAnt, consumoAct, desviacion, serieFactura, sector, actividad, avisos, estado, lmarcado, cmarcado, aplexcart, tipoEnvaseSemanal, latitud, longitud, pendiente, deuda, ultPedido, pendienteValidar, limiteRiesgo, tiplinenvaut, aplicaManipulacion, correo, aplicaAzucar, orden)