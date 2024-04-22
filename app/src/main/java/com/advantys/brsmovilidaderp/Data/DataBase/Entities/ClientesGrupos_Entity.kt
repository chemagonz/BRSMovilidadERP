package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.ClientesGrupos_Schema
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString

class ClientesGrupos_Entity (
    var grupo: String? = null,
    var cliente: Int? = null,
    var delegacion: Short? = null
) {
    companion object {
        fun fromCursor(cursor: Cursor): ClientesGrupos_Entity {
            val modelo = ClientesGrupos_Entity()
            modelo.grupo = cursor.getString(ClientesGrupos_Schema.GRUPO_FIELD,null)
            modelo.cliente = cursor.getInt(ClientesGrupos_Schema.CLIENTE_FIELD,null)
            modelo.delegacion = cursor.getShort(ClientesGrupos_Schema.DELEGACION_FIELD,null)
            return modelo
        }
    }
}