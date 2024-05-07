package com.advantys.brsmovilidaderp.Domain.Models

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Promociones_Entity
import java.util.Date

class Promocion  (
    var promocion: String?=null,
    var fabricante: Short?=null,
    var articulo: String?=null,
    var formato: Int?=null,
    var familia: Short?=null,
    var subfamilia: Short?=null,
    var marca: String?=null,
    var sabor: String?=null,
    var porcDto1: Float?=null,
    var impDto1Uni: Float?=null,
    var tipoDescuento: String?=null,
    var porcDto2: Float?=null,
    var impDto2Uni: Float?=null,
    var pvp: Float?=null,
    var tipoSumatoria: String?=null,
    var base: Short?=null,
    var regalo: Short?=null,
    var fabricante2: Short?=null,
    var articulo2: String?=null,
    var desde: Date?=null,
    var hasta: Date?=null,
    var tipoProm: String?=null,
    var sector: Short?=null,
    var actividad: Short?=null,
    var tarifa: Short?=null,
    var minimo: Float?=null,
    var descripcion: String?=null,
    var multiple: String?=null,
    var centro: Short?=null,
    var grupo: String?=null,
    var tipoUnidad: String?=null,
    var obligatoria: Boolean?=null,
    var aplrap: Boolean?=null,
    var estado: String?=null,
    var sugMov: Boolean?=null
)

fun Promociones_Entity.toDomain() = Promocion (promocion, fabricante, articulo, formato, familia, subfamilia, marca, sabor, porcDto1, impDto1Uni, tipoDescuento, porcDto2, impDto2Uni, pvp, tipoSumatoria, base, regalo, fabricante2, articulo2, desde, hasta, tipoProm, sector, actividad, tarifa, minimo, descripcion, multiple, centro, grupo, tipoUnidad, obligatoria, aplrap, estado, sugMov)