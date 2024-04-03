package com.advantys.brsmovilidaderp.Domain.Models

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Familias_Entity

class Familia (
    var familia: Short?= null,
    var nombre: String?=null
)
fun Familias_Entity.toDomain()= Familia(familia, nombre)