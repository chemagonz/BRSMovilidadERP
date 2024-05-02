package com.advantys.brsmovilidaderp.Utils

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Environment
import com.advantys.brsmovilidaderp.BRSMovilidadERPApp
import java.io.File
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


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
    var hemosEntradoEnMulti = false
    var hemosCanceladoMulti = false


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

//    fun formatofecha(date: Date?): String {
//        var fecha = ""
//        if (date != null) {
//            if (date.date.toString().length == 2) fecha =
//                date.date.toString() + "/" else fecha += "0" + date.date.toString() + "/"
//            fecha += if ((date.month + 1).toString().length == 2) (date.month + 1).toString() + "/" else "0" + (date.month + 1).toString() + "/"
//            fecha += (date.year + 1900).toString()
//        }
//        return fecha
//    }

    //Obtener versión de la aplicación instalada

    fun obtenerVersionApp(): String {
        return try {
            val context = BRSMovilidadERPApp.getContext()
            val pinfo: PackageInfo = context.packageManager
                .getPackageInfo(context.packageName, 0)
            pinfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            ""
        }
    }
    //Función para validar contraseña introducida
    fun validarPassword(password: String): Boolean {
        val sdf = SimpleDateFormat("ddMMyy", Locale.getDefault())
        val currentDate = sdf.format(Date())
        val expectedPassword = "$currentDate${10}"
        return password == expectedPassword
    }








