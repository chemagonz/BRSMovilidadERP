package com.advantys.brsmovilidaderp.Utils

class Utils {
    //Clase dedicada para metodos estaticos, funciones generales, variables generables...
        companion object {
        var diasSeleccionados: MutableMap<dias, Boolean> = mutableMapOf(
            dias.lunes to false,
            dias.martes to false,
            dias.miercoles to false,
            dias.jueves to false,
            dias.viernes to false,
            dias.sabado to false,
            dias.domingo to false,
            dias.todos to false
        )
            var orderPor: ordenarPor= ordenarPor.ruta
            var mostrar: mostrarPor= mostrarPor.todos
            var buscarArticulo: buscarArticulosPor= buscarArticulosPor.descripcion
         }
}

enum class dias{
    lunes,
    martes,
    miercoles,
    jueves,
    viernes,
    sabado,
    domingo,
    todos
}
enum class ordenarPor{
        ruta,
        cliente,
        nombre,
        secuencia,
        ordenpersonalizado
}
enum class mostrarPor{
        marcado,
        desmarcado,
        todos;
}
enum class buscarArticulosPor{
    descripcion,
    codigo
}