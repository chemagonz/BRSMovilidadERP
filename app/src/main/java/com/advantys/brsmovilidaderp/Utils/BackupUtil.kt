package com.advantys.brsmovilidaderp.Utils

import android.os.Build
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.nio.file.Files
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


class BackupUtil {

    companion object{

        const val INICIO = 0
        const val IMPORTACION_INI = 1
        const val IMPORTACION_FIN = 2
        const val EXPORTACION_INI = 3
        const val EXPORTACION_FIN = 4
        const val BORRADO = 5
        const val JORNADA_INI = 6
        const val JORNADA_FIN = 7
        const val INVENTARIO = 8
        const val MOVIMIENTO = 9
        const val CARGA_INI = 10
        const val CARGA_FIN = 11
        const val IMP_CARGA_INI = 12
        const val IMP_CARGA_FIN = 13
        const val BORRADO_EXISTENCIAS = 14
        const val BORRADO_AUT_INI = 15
        const val BORRADO_AUT_FIN = 16
        const val VERIFICAR_LOTES = 17
        const val IMP_INVENTARIO_INI = 18
        const val IMP_INVENTARIO_FIN = 19
        const val EXP_INVENTARIO_INI = 20
        const val EXP_INVENTARIO_FIN = 21
        const val IMP_EXISTENCIAS_INI = 22
        const val IMP_EXISTENCIAS_FIN = 23
        const val BORRADO_INVENTARIO = 24

        fun crearBackup(tipoCopia: Int) {
            try {
                val formatoDia = SimpleDateFormat("dd-MM-yyyy")
                val formatoHora = SimpleDateFormat("HH:mm:ss")
                val fecha: String = formatoDia.format(Calendar.getInstance().getTime())
                val hora: String = formatoHora.format(Calendar.getInstance().getTime())
                val directorioBackups: File = File(Ruta + "/Backups")
                val directorioDia = File("$directorioBackups/$fecha")
                var existeBackups = true
                var existeDia = true
                if (!directorioBackups.exists()) existeBackups = directorioBackups.mkdir()
                if (!directorioDia.exists()) existeDia = directorioDia.mkdir()
                if (existeBackups && existeDia) {
                    val nombreBackup =
                        "/" + "BRSAndroid_" + tipoCopia(tipoCopia) + "_" + hora.replace(
                            ":",
                            "."
                        ) + ".db"
                    val bdoriginal: File = File(Ruta + "/BRSAndroid.db")
                    val backup = File(directorioDia.toString() + nombreBackup)
                    try {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) Files.copy(bdoriginal.toPath(), backup.toPath()) else {
                            if (backup.createNewFile()) {
                                val entrada: InputStream = FileInputStream(bdoriginal)
                                val salida: OutputStream = FileOutputStream(backup)
                                val buf = ByteArray(1024)
                                var longitud: Int
                                while (entrada.read(buf).also { longitud = it } > 0) {
                                    salida.write(buf, 0, longitud)
                                }
                                entrada.close()
                                salida.close()
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun borrarBackups() {
            try {
                val formatoDia = SimpleDateFormat("dd-MM-yyyy")
                val c: Calendar = Calendar.getInstance()
                c.add(Calendar.DATE, -10)
                val fechaAnterior: Date = c.getTime()
                var fechaFile: Date? = null
                val directorioBackups: File = File(Ruta + "/Backups")
                val files = directorioBackups.listFiles()
                for (f in files) {
                    try {
                        if (fechaValida(f.getName(), "dd-MM-yyyy")) {
                            fechaFile = formatoDia.parse(f.getName())
                            if (fechaAnterior.after(fechaFile)) {
                                eliminarFicheros(File(directorioBackups.toString() + "/" + f.getName()))
                            }
                        } else eliminarFicheros(File(directorioBackups.toString() + "/" + f.getName()))
                    } catch (e: ParseException) {
                        e.printStackTrace()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        private fun tipoCopia(tipoCopia: Int): String {
            return when (tipoCopia) {
                INICIO -> "Inicio"
                IMPORTACION_INI -> "ImportacionIni"
                IMPORTACION_FIN -> "ImportacionFin"
                EXPORTACION_INI -> "ExportacionIni"
                EXPORTACION_FIN -> "ExportacionFin"
                BORRADO -> "Borrado"
                JORNADA_INI -> "JornadaIni"
                JORNADA_FIN -> "JornadaFin"
                INVENTARIO -> "Inventario"
                MOVIMIENTO -> "Movimiento"
                CARGA_INI -> "CargaIni"
                CARGA_FIN -> "CargaFin"
                IMP_CARGA_INI -> "ImpCargaIni"
                IMP_CARGA_FIN -> "ImpCargaFin"
                BORRADO_EXISTENCIAS -> "BorradoExistencias"
                BORRADO_AUT_INI -> "BorradoAutIni"
                BORRADO_AUT_FIN -> "BorradoAutFin"
                VERIFICAR_LOTES -> "VerificarLotes"
                IMP_INVENTARIO_INI -> "ImpInventarioIni"
                IMP_INVENTARIO_FIN -> "ImpInventarioFin"
                EXP_INVENTARIO_INI -> "ExpInventarioIni"
                EXP_INVENTARIO_FIN -> "ExpInventarioFin"
                IMP_EXISTENCIAS_INI -> "ImpExistenciasIni"
                IMP_EXISTENCIAS_FIN -> "ImpExistenciasFin"
                BORRADO_INVENTARIO -> "BorradoInventario"
                else -> ""
            }
        }
    }
}