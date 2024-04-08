package com.advantys.brsmovilidaderp.Domain.Models

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Formato_Entity

class Formato (var formato: Int?= null, var nombre: String?=null){
    override fun toString(): String {
        return "$formato - $nombre"
    }
}

fun Formato_Entity.toDomain()= Formato(formato, nombre)