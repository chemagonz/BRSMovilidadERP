package com.advantys.brsmovilidaderp.Domain.Models

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Fabricante_Entity

class Fabricante (
    var numfabricante: Short? = null,
    var nombre: String? = null,
    var tipodescuento: String? = null,
    var tipolinea: String? = null,
    var esmulcli: Boolean? = null,
    var razonsocial: String? = null,
    var direccion: String? = null,
    var codpostal: String? = null,
    var poblacion: String? = null,
    var provincia: String? = null,
    var nif: String? = null,
    var telefono1: String? = null,
    var telefono2: String ? = null,
    var fax: String? = null,
    var registromercantil: String? = null,
    var pie: String? = null,
    var codigocentro: String?=null
)

    fun Fabricante_Entity.toDomain() = Fabricante (numfabricante, nombre, tipodescuento, tipolinea, esmulcli, razonsocial, direccion, codpostal, poblacion, provincia, nif, telefono1, telefono2, fax, registromercantil, pie, codigocentro)