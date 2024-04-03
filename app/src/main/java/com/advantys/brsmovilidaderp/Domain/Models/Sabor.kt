package com.advantys.brsmovilidaderp.Domain.Models

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Sabor_Entity

class Sabor (
    var sabor: String?= null,
    var nombre:String?= null
)
fun Sabor_Entity.toDomain()= Sabor(sabor, nombre)