package com.advantys.brsmovilidaderp.Domain.Models

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.TarifasArticulos_Entity
import java.time.LocalDateTime

class TarifaArticulo (
    var numTarifa: Short?= null,
    var numFabricante: Short?=null,
    var articulo: String?=null,
    var pvp: Float?= null,
    var desde: LocalDateTime?= null,
    var hasta: LocalDateTime?=null,
    var porcdto1: Float?=null,
    var impodto1: Float?= null,
    var porcdto2: Float?= null,
    var impodto2: Float?=null
)
fun TarifasArticulos_Entity.toDomain()= TarifaArticulo(numTarifa, numFabricante, articulo, pvp, desde, hasta, porcdto1, impodto1, porcdto2, impodto2)
