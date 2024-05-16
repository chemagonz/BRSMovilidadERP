package com.advantys.brsmovilidaderp.Domain.Models

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.RutaClientes_Entity

class RutaCliente (
    var cliente: Int?=null,
    var delegacion: Short?=null,
    var ruta: String?=null,
    var secuencia: String?=null,
    var diaSemana: String?=null,
    var tipoRuta: String?=null,
    var tipoReparto: String?=null,
    var rr: String?=null,
    var sr: String?=null,
    var diaRep: String?=null
)

fun RutaClientes_Entity.toDomain() = RutaCliente (cliente, delegacion, ruta, secuencia, diaSemana, tipoRuta, tipoReparto, rr, sr, diaRep)