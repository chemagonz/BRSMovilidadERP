package com.advantys.brsmovilidaderp.Domain.Models

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Visitas_Entity
import java.util.Date

class Visita (
    var preventa: Date? = null,
    var cliente: Int? = null,
    var delegacion: Short? = null,
    var rutaventa: String? = null,
    var secuventa: String? = null,
    var motivo: Short? = null,
    var enviado: Boolean? = null
)
fun Visitas_Entity.toDomain()=Visita(preventa, cliente, delegacion, rutaventa, secuventa, motivo,enviado)