package com.advantys.brsmovilidaderp.Domain.Models

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.TipoOperacion_Entity

class TipoOperacion(
    var tipoOperacion: Int? = null,
    var nombre: String? = null,
    var genEnvVen: String? = null,
    var genEnvReg: String? = null,
    var genEnvDep: String? = null,
    var genEnvPro: String? = null,
    var genEnvInc: String? = null,
    var ptoVerVen: String? = null,
    var ptoVerReg: String? = null,
    var ptoVerDep: String? = null,
    var ptoVerPro: String? = null,
    var ptoVerInc: String? = null,
    var manipuVen: String? = null,
    var manipuReg: String? = null,
    var manipuDep: String? = null,
    var manipuPro: String? = null,
    var manipuInc: String? = null,
    var actStkVen: String? = null,
    var actStkReg: String? = null,
    var actStkDep: String? = null,
    var actStkPro: String? = null,
    var actStkInc: String? = null
)
fun TipoOperacion_Entity.toDomain() = TipoOperacion(tipoOperacion, nombre, genEnvVen, genEnvReg, genEnvDep, genEnvPro, genEnvInc, ptoVerVen, ptoVerReg, ptoVerDep,ptoVerPro,ptoVerInc,
    manipuVen,manipuReg,manipuDep,manipuPro,manipuInc,actStkVen,actStkReg,actStkDep,actStkPro,actStkInc )