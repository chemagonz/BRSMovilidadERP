package com.advantys.brsmovilidaderp.Data.DataBase.Entities

import android.database.Cursor
import com.advantys.brsmovilidaderp.Data.DataBase.Schemas.Series_Schema

data class Series_Entity(
    var cSeries: String?= null,
    var nombre: String?= null,
    var ultPedido: Int?= null,
    var ultAlbaran: Int?= null,
    var ultFactura: Int?= null,
    var centro: Int?= null,
    var aplicaIva: String?= null,
    var pedido: Boolean?= null,
    var albaran: Boolean?= null,
    var factura: Boolean?= null,
    var fabServicio: Int?= null,
    var artServicio: Int? =null,
    var tarifa: Int?= null,
    var tipoLinea: String?= null
) {

    //Cursor que recorrer todos los campos.

    companion object{
        fun fromCursor(cursor: Cursor):Series_Entity{
            var modelo= Series_Entity()
            modelo.cSeries= cursor.getString(cursor.getColumnIndexOrThrow(Series_Schema.SERIE_FIELD))
            modelo.nombre= cursor.getString(cursor.getColumnIndexOrThrow(Series_Schema.NOMBRE_FIELD))
            modelo.ultPedido= cursor.getInt(cursor.getColumnIndexOrThrow(Series_Schema.ULTPEDIDO_FIELD))
            modelo.ultAlbaran= cursor.getInt(cursor.getColumnIndexOrThrow(Series_Schema.ULTALBARAN_FIELD))
            modelo.ultFactura= cursor.getInt(cursor.getColumnIndexOrThrow(Series_Schema.ULTFACTURA_FIELD))
            modelo.centro= cursor.getInt(cursor.getColumnIndexOrThrow(Series_Schema.CENTRO_FIELD))
            modelo.aplicaIva= cursor.getString(cursor.getColumnIndexOrThrow(Series_Schema.APLICAIVA_FIELD))
            modelo.pedido= cursor.getInt(cursor.getColumnIndexOrThrow(Series_Schema.PEDIDO_FIELD))!=0
            modelo.albaran= cursor.getInt(cursor.getColumnIndexOrThrow(Series_Schema.ALBARAN_FIELD))!=0
            modelo.factura= cursor.getInt(cursor.getColumnIndexOrThrow(Series_Schema.FACTURA_FIELD))!=0
            modelo.fabServicio= cursor.getInt(cursor.getColumnIndexOrThrow(Series_Schema.FABSERVICIO_FIELD))
            modelo.artServicio= cursor.getInt(cursor.getColumnIndexOrThrow(Series_Schema.ARTSERVICIO_FIELD))
            modelo.tarifa= cursor.getInt(cursor.getColumnIndexOrThrow(Series_Schema.TARIFA_FIELD))
            modelo.tipoLinea= cursor.getString(cursor.getColumnIndexOrThrow(Series_Schema.TIPOLINEA_FIELD))
            return modelo
        }
    }

}