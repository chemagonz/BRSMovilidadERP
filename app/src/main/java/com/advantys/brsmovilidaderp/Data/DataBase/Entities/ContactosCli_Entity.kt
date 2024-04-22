package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.ContactosCli_Schema
import com.advantys.brsmovilidaderp.Utils.getBoolean
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString

class ContactosCli_Entity (
    var codigo: Short? = null,
    var cliente: Int? = null,
    var delegacion: Short? = null,
    var nombre: String? = null,
    var cargo: String? = null,
    var telefono: String? = null,
    var telefono2: String? = null,
    var fax: String? = null,
    var email: String? = null,
    var extension: String? = null,
    var password: String? = null,
    var primerPsw: Boolean? = null,
    var accion: String? = null,
    var enviado: Boolean? = null
) {
    companion object {
        fun fromCursor(cursor: Cursor): ContactosCli_Entity {
            val modelo = ContactosCli_Entity()
            modelo.codigo = cursor.getShort(ContactosCli_Schema.CODIGO_FIELD,null)
            modelo.cliente = cursor.getInt(ContactosCli_Schema.CLIENTE_FIELD,null)
            modelo.delegacion = cursor.getShort(ContactosCli_Schema.DELEGACION_FIELD,null)
            modelo.nombre = cursor.getString(ContactosCli_Schema.NOMBRE_FIELD,null)
            modelo.cargo = cursor.getString(ContactosCli_Schema.CARGO_FIELD,null)
            modelo.telefono = cursor.getString(ContactosCli_Schema.TELEFONO_FIELD,null)
            modelo.telefono2 = cursor.getString(ContactosCli_Schema.TELEFONO2_FIELD,null)
            modelo.fax = cursor.getString(ContactosCli_Schema.FAX_FIELD,null)
            modelo.email = cursor.getString(ContactosCli_Schema.EMAIL_FIELD,null)
            modelo.extension = cursor.getString(ContactosCli_Schema.EXTENSION_FIELD,null)
            modelo.password = cursor.getString(ContactosCli_Schema.PASSWORD_FIELD,null)
            modelo.primerPsw = cursor.getBoolean(ContactosCli_Schema.PRIMERPSW_FIELD,null)
            modelo.accion = cursor.getString(ContactosCli_Schema.ACCION_FIELD,null)
            modelo.enviado = cursor.getBoolean(ContactosCli_Schema.ENVIADO_FIELD,null)
            return modelo
        }
    }
}