package com.advantys.brsmovilidaderp.Utils

import android.os.Environment
import java.io.File
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date


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
            var Ruta= Environment.getExternalStorageDirectory().path+ "/BRSAndroid/"
            var FechaHoy: Date? = null
            var YaCargado = false

        fun fechaValida(date: String?, dateFormat: String?): Boolean {
            val sdf: DateFormat = SimpleDateFormat(dateFormat)
            sdf.setLenient(false)
            try {
                sdf.parse(date)
            } catch (e: ParseException) {
                return false
            }
            return true
        }
        fun eliminarFicheros(carpeta: File) {
            try {
                val listaFiles = carpeta.listFiles()
                if (listaFiles != null) {
                    for (f in listaFiles) {
                        if (f.isDirectory()) eliminarFicheros(f) else f.delete()
                    }
                }
                carpeta.delete()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
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