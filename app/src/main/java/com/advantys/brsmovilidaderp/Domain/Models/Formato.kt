package com.advantys.brsmovilidaderp.Domain.Models

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Formato_Entity

class Formato (
    var formato: Int?= null,
    var nombre: String?=null
)
fun Formato_Entity.toDomain()= Formato(formato, nombre)