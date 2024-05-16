package com.advantys.brsmovilidaderp.Domain.Models

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.RazonesSociales_Entity

class RazonSocial (
    var razonSocial: Int?=null,
    var nombre: String?=null,
    var nif: String?=null,
    var direccion: String?=null,
    var codPostal: String?=null,
    var poblacion: String?=null,
    var provincia: String?=null,
    var telefono1: String?=null,
    var telefono2: String?=null
)

fun RazonesSociales_Entity.toDomain() = RazonSocial (razonSocial, nombre, nif, direccion, codPostal, poblacion, provincia, telefono1, telefono2)