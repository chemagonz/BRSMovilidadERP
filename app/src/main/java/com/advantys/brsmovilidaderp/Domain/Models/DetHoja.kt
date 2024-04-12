package com.advantys.brsmovilidaderp.Domain.Models

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.DetHojas_Entity

class DetHoja (
    var fabricante: Short? = null,
    var articulo: String? = null,
    var unidadcaja: Float? = null,
    var carga1: Float? = null,
    var carga2: Float? = null,
    var carga3: Float? = null,
    var ventas: Float? = null,
    var abonos: Float? = null,
    var cambios: Float? = null,
    var roturas: Float? = null,
    var caducadas: Float? = null,
    var caducadasdisp: Float? = null
)
fun DetHojas_Entity.toDomain()= DetHoja(fabricante, articulo, unidadcaja, carga1, carga2, carga3, ventas, abonos, cambios, roturas, caducadas, caducadasdisp)