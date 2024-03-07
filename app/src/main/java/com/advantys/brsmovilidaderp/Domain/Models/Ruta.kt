package com.advantys.brsmovilidaderp.Domain.Models

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Rutas_Entity

class Ruta (
    var numRuta:Int?= null,
    var nombre:String? = null,
    var marcado : Int? = null
)

fun Rutas_Entity.toDomain() = Ruta(nRuta, nombre, marcado)