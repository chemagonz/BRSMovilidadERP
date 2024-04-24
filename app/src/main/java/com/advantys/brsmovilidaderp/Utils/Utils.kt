package com.advantys.brsmovilidaderp.Utils

import android.os.Environment
import java.io.File
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

var diasSeleccionados: MutableMap<Dias, Boolean> = mutableMapOf(
    Dias.lunes to false,
    Dias.martes to false,
    Dias.miercoles to false,
    Dias.jueves to false,
    Dias.viernes to false,
    Dias.sabado to false,
    Dias.domingo to false,
    Dias.todos to false
)

var orderPor: OrdenarPor = OrdenarPor.ruta
var mostrar: MostrarPor = MostrarPor.todos
var buscarArticulo: BuscarArticulosPor = BuscarArticulosPor.descripcion
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

fun formatofecha(date: Date?): String {
    var fecha = ""
    if (date != null) {
        if (date.date.toString().length == 2) fecha =
            date.date.toString() + "/" else fecha += "0" + date.date.toString() + "/"
        fecha += if ((date.month + 1).toString().length == 2) (date.month + 1).toString() + "/" else "0" + (date.month + 1).toString() + "/"
        fecha += (date.year + 1900).toString()
    }
    return fecha
}








