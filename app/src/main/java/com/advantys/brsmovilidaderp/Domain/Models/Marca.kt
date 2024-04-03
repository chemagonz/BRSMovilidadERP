package com.advantys.brsmovilidaderp.Domain.Models

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Marca_Entity

class Marca (
    var marca: String?= null,
    var nombre: String?= null
)
fun Marca_Entity.toDomain()= Marca(marca, nombre)