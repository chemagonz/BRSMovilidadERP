package com.advantys.brsmovilidaderp.Domain.Models

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.FormasPago_Entity

class FormasPago (
    var formaPago: Short?=null,
    var nombre: String?=null,
    var emiteRecibo: String?=null,
    var tipoPago: String?=null,
    var dtoVtaCaj: Boolean?=null,
    var medioPago: Short?=null
)

fun FormasPago_Entity.toDomain() = FormasPago(formaPago, nombre, emiteRecibo, tipoPago, dtoVtaCaj, medioPago)