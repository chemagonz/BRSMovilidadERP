package com.advantys.brsmovilidaderp.Domain.Models

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Cobros_Entity
import java.util.Date

class Cobro (
    var cobro: Int? = null,
    var cliente: Int? = null,
    var delegacion: Short? = null,
    var fecha: Date? = null,
    var seriealbaran: String? = null,
    var albaran: Int? = null,
    var seriefactura: String? = null,
    var factura: Int? = null,
    var gasto: Int? = null,
    var cobrado: Float? = null,
    var tipopago: String? = null,
    var tipoefecto: Short? = null,
    var efecto: String? = null,
    var libramiento: Date? = null,
    var vencimiento: Date? = null,
    var asiento: Int? = null,
    var acuenta: Int? = null,
    var enviado: Boolean? = false,
    var ejeralbaran: Int? = null,
    var ejerfactura: Int? = null,
    var recibo: Int? = null,
    var datafono: String? = null
)

fun Cobros_Entity.toDomain()= Cobro(cobro, cliente, delegacion, fecha, seriealbaran, albaran, seriefactura, factura, gasto, cobrado, tipopago, tipoefecto,efecto, libramiento, vencimiento, asiento, acuenta, enviado, ejeralbaran, ejerfactura,recibo, datafono)