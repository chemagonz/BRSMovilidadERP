package com.advantys.brsmovilidaderp.Utils

class Utils {
    //Clase dedicada para metodos estaticos, funciones generales, variables generables...
        companion object {
        var lunes: Boolean= false
        var martes: Boolean= false
        var miercoles: Boolean= false
        var jueves:Boolean= false
        var viernes: Boolean= false
        var sabado: Boolean= false
        var domingo: Boolean= false
        var todos: Boolean= false
            var orderPor: ordenarPor= ordenarPor.ruta
            var mostrar: mostrarPor= mostrarPor.todos
         }
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