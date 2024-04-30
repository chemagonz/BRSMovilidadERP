package com.advantys.brsmovilidaderp.Domain.Models

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.MultiClientes_Entity

class MultiCliente (

    var multiCliente: Int?=null,
    var multiDelegacion: Short?=null,
    var fabricante: Short?=null,
    var clieFabri: Int?=null,
    var tarifa: Short?=null,
    var serieAlbaran: String?=null,
    var facturable: String?=null,
    var tipoOperacion: Short?=null,
    var aPlexCart: Boolean?=null,
    var identicket: String?=null,
    var aplicaMulti: String?=null,
    var blqCaraBo: String?=null
)

fun MultiClientes_Entity.toDomain() = MultiCliente ( multiCliente, multiDelegacion, fabricante, clieFabri, tarifa, serieAlbaran, facturable, tipoOperacion, aPlexCart, identicket, aplicaMulti, blqCaraBo)