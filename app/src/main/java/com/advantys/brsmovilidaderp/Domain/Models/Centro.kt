package com.advantys.brsmovilidaderp.Domain.Models

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Centros_Entity

class Centro (
    var centro: Int? = null,
    var nombre: String? = null,
    var codigo: Int? = null,
    var nif: String? = null,
    var direccion: String? = null,
    var codigoPostal: Int? = null,
    var poblacion: String? = null,
    var provincia: String? = null,
    var telefono: Int? = null,
    var serie: Int? = null,
    var aplicaCargo: Boolean? = null,
    var ventaMenorA: Boolean? = null
)

fun Centros_Entity.toDomain() = Centro(nCentro, nombre, codigo, NIF, direccion, codigoPostal, poblacion, provincia, telefono, serie, aplicaCargo, ventaMenorA)
