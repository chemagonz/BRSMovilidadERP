package com.advantys.brsmovilidaderp.Domain.Models

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.DetPedidos_Entity
import java.util.Date

class DetPedido (
    var serie: String? = null,
    var pedido: Int? = null,
    var orden: Int? = null,
    var tipolinea: String? = null,
    var fabricante: Short? = null,
    var articulo: String? = null,
    var concepto: String? = null,
    var cantidad1: Float? = null,
    var cantidad2: Float? = null,
    var unidadcaja: Float? = null,
    var pvp: Float? = null,
    var porciva: Float? = null,
    var porcrec: Float? = null,
    var promocion: String? = null,
    var porcdto1: Float? = null,
    var impdto1uni:Float? = null,
    var impdto1: Float? = null,
    var tipodescuento: String? = null,
    var porcdto2: Float? = null,
    var impdto2uni: Float? = null,
    var impdto2: Float? = null,
    var ordenpromo: Int? = null,
    var ordenenvase: Int? = null,
    var puntoverde: Float? = null,
    var alcohol: Float? = null,
    var ordenescandallo: Int? = null,
    var envase: Boolean? = null,
    var desdobledoc: Short? = null,
    var lote: String? = null,
    var caducidad: Date? = null,
    var bloqueo: Boolean? = null,
    var ordenpromomult: Short? = null,
    var observacion: String? = null,
    var motivo: Int? = null,
    var ejercicio: Int? = null,
    var manipulacion: Float? = null,
    var azucar: Float? = null,
    var cantidad1original: Float? = null,
    var cantidad2original: Float? = null
)
fun DetPedidos_Entity.toDomain()= DetPedido(serie, pedido, orden, tipolinea, fabricante, articulo, concepto, cantidad1, cantidad2, unidadcaja, pvp, porciva, porcrec, promocion, porcdto1, impdto1uni, impdto1, tipodescuento, porcdto2, impdto2uni, impdto2, ordenpromo, ordenenvase, puntoverde,alcohol, ordenescandallo, envase, desdobledoc, lote, caducidad, bloqueo, ordenpromomult, observacion, motivo,ejercicio, manipulacion, azucar, cantidad1original, cantidad2original)