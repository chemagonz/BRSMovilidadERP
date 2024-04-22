package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.InfoCli_Schema
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getString

class InfoCli_Entity (
    var cliente: Int?=null,
    var nombre: String?=null,
    var terminal: Int?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): InfoCli_Entity {
            val modelo= InfoCli_Entity()
            modelo.cliente = cursor.getInt(InfoCli_Schema.CLIENTE_FIELD,null)
            modelo.nombre = cursor.getString(InfoCli_Schema.NOMBRE_FIELD,null)
            modelo.terminal = cursor.getInt(InfoCli_Schema.TERMINAL_FIELD,null)
            return modelo
        }
    }
}