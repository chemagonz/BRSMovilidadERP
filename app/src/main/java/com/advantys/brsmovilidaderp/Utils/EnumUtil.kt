package com.advantys.brsmovilidaderp.Utils

class EnumUtil {
    enum class Dias{
        lunes,
        martes,
        miercoles,
        jueves,
        viernes,
        sabado,
        domingo,
        todos
    }
    enum class OrdenarPor{
        ruta,
        cliente,
        nombre,
        secuencia,
        ordenpersonalizado
    }
    enum class MostrarPor{
        marcado,
        desmarcado,
        todos;
    }
    enum class BuscarArticulosPor{
        descripcion,
        codigo
    }
}