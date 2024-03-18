package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.TipoOperacion_Schema

data class TipoOperacion_Entity(
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
    var actStkInc: String? = null) {
    companion object{
        fun fromCursorA(cursor: Cursor):TipoOperacion_Entity{
            var modelo= TipoOperacion_Entity()
            modelo.tipoOperacion= cursor.getInt(cursor.getColumnIndexOrThrow(TipoOperacion_Schema.TIPOOPERACION_FIELD))
            modelo.nombre= cursor.getString(cursor.getColumnIndexOrThrow(TipoOperacion_Schema.NOMBRE_FIELD))
            return modelo
        }
    }
}