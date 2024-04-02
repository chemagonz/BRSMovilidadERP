package com.advantys.brsmovilidaderp.Domain.Models

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.TiposIVA_Entity

class TipoIVA(
    var tipoIVA: Short?= null,
    var nombre: String?=null,
    var porcIVA: Float?=null,
    var porCREC: Float?= null
)

fun TiposIVA_Entity.toDomain() = TipoIVA(tipoIVA, nombre,porcIVA, porCREC)