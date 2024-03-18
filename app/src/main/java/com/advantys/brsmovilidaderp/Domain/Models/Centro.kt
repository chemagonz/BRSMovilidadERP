package com.advantys.brsmovilidaderp.Domain.Models

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Centros_Entity

class Centro (
    var numCentro: Int? = null,
    var nombre: String? = null,
    var nif: String? = null,
    var direccion: String? = null,
    var codigoPostal: Int? = null,
    var poblacion: String? = null,
    var provincia: String? = null,
    var telefono: Int? = null,
    var serie: String? = null,
    var aplicaCargo: Boolean,
    var ventaMenorA: Int? = null
)
fun Centros_Entity.toDomain() = Centro(nCentro, nombre, NIF, direccion, codigoPostal, poblacion, provincia, telefono, serie, aplicaCargo, ventaMenorA)
