package com.advantys.brsmovilidaderp.Domain.Models

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Subfamilias_Entity

class Subfamilia (
    var familia: Short?= null,
    var subfamilia: Short?=null,
    var nombre: String?= null
)
fun Subfamilias_Entity.toDomain()= Subfamilia(familia, subfamilia, nombre)