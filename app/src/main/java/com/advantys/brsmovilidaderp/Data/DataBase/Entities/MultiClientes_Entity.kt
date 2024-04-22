package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.MultiClientes_Schema
import com.advantys.brsmovilidaderp.Utils.getBoolean
import com.advantys.brsmovilidaderp.Utils.getInt
import com.advantys.brsmovilidaderp.Utils.getShort
import com.advantys.brsmovilidaderp.Utils.getString

class MultiClientes_Entity (
    var multiCliente: Int?=null,
    var multiDelegacion: Short?=null,
    var fabricante: Short?=null,
    var clieFabri: Int?=null,
    var tarifa: Short?=null,
    var serieAlbaran: String?=null,
    var facturable: String?=null,
    var tipoOperacion: Short?=null,
    var aPlexCart: Boolean?=null,
    var identicket: String?=null,
    var aplicaMulti: String?=null,
    var blqCaraBo: String?=null
) {
    companion object {
        fun fromCursor(cursor: Cursor): MultiClientes_Entity {
            val modelo=  MultiClientes_Entity()
            modelo.multiCliente = cursor.getInt(MultiClientes_Schema.MULTICLIENTE_FIELD,null)
            modelo.multiDelegacion = cursor.getShort(MultiClientes_Schema.MULTIDELEGACION_FIELD,null)
            modelo.fabricante = cursor.getShort(MultiClientes_Schema.FABRICANTE_FIELD,null)
            modelo.clieFabri = cursor.getInt(MultiClientes_Schema.CLIEFABRI_FIELD,null)
            modelo.tarifa = cursor.getShort(MultiClientes_Schema.TARIFA_FIELD,null)
            modelo.serieAlbaran = cursor.getString(MultiClientes_Schema.SERIEALBARAN_FIELD,null)
            modelo.facturable = cursor.getString(MultiClientes_Schema.FACTURABLE_FIELD,null)
            modelo.tipoOperacion = cursor.getShort(MultiClientes_Schema.TIPOOPERACION_FIELD,null)
            modelo.aPlexCart = cursor.getBoolean(MultiClientes_Schema.APLEXCART_FIELD,null)
            modelo.identicket = cursor.getString(MultiClientes_Schema.IDENTICKET_FIELD,null)
            modelo.aplicaMulti = cursor.getString(MultiClientes_Schema.APLICAMULTI_FIELD,null)
            modelo.blqCaraBo = cursor.getString(MultiClientes_Schema.BLQCARABO_FIELD,null)
            return modelo
        }
    }
}