package com.advantys.brsmovilidaderp.Domain.Models

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Tarifas_Entity

class Tarifa (
    var numTarifa : Short?=null,
    var nombre: String?=null,
    var tarifaAux: Short? =null,
    var porcCargo: Float? = null,
    var tarblqvta: Boolean? =null
)
fun Tarifas_Entity.toDomain()= Tarifa(numTarifa, nombre, tarifaAux, porcCargo, tarblqvta)