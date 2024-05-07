package com.advantys.brsmovilidaderp.Domain.Models

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Licencia_Entity

class Licencia(

    var idenDisp: String? = null,
    var idenProg: String? = null,
    var licencia: String? = null,
    var cliente: String? = null,
    var numLicencia: String? = null,

)
fun Licencia_Entity.toDomain() = Licencia(idenDisp, idenProg, licencia, cliente, numLicencia)
