package com.advantys.brsmovilidaderp.Domain.Models

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Rutas_Entity

class Ruta (
    var numRuta:String?= null,
    var nombre:String? = null,
    var lmarcado : Boolean? =null,
    var cmarcado:String?= null
)
fun Rutas_Entity.toDomain() = Ruta(nRuta, nombre, lmarcado, cmarcado)