package com.advantys.brsmovilidaderp.Domain.Models

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Series_Entity

class Serie(
    var cSeries: Int?= null,
    var nombre: String?= null,
    var ultPedido: Int?= null,
    var ultAlbaran: Int?= null,
    var ultFactura: Int?= null,
    var centro: Int?= null,
    var aplicaIva: String?= null,
    var pedido: Boolean?= null,
    var albaran: Boolean?= null,
    var factura: Boolean?= null,
    var fabServicio: Int?= null,
    var artServicio: Int? =null,
    var tarifa: Int?= null,
    var tipoLinea: String?= null
)
fun Series_Entity.toDomain() = Serie(cSeries, nombre, ultPedido, ultAlbaran, ultFactura, centro, aplicaIva, pedido, albaran, factura, fabServicio, artServicio,tarifa,tipoLinea)
