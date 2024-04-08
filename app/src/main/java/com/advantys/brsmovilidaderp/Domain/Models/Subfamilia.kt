package com.advantys.brsmovilidaderp.Domain.Models

import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Subfamilias_Entity

class Subfamilia (var familia: Short?= null, var subfamilia: Short?=null, var nombre: String?= null){
    override fun toString(): String {
        return "$subfamilia - $nombre"
    }
}

fun Subfamilias_Entity.toDomain()= Subfamilia(familia, subfamilia, nombre)
