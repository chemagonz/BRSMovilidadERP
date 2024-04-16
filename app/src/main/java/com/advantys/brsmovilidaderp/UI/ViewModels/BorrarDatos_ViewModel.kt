package com.advantys.brsmovilidaderp.UI.ViewModels

import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Domain.UseCases.BorrarDatos_UseCase
import com.advantys.brsmovilidaderp.Utils.BORRADO
import com.advantys.brsmovilidaderp.Utils.Ruta
import com.advantys.brsmovilidaderp.Utils.borrarBackups
import com.advantys.brsmovilidaderp.Utils.crearBackup
import com.advantys.brsmovilidaderp.Utils.showOkDialog
import com.advantys.brsmovilidaderp.Utils.showProgressDialog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class BorrarDatos_ViewModel @Inject constructor(private var borrarDatosUsecase: BorrarDatos_UseCase): ViewModel() {

    fun borrarCobros(fecha:String):Boolean{
        viewModelScope.launch(Dispatchers.Default) {
            borrarDatosUsecase.borrarCobros(fecha)
        }
        return true
    }
    fun borrarCargaCero():Boolean{
        viewModelScope.launch(Dispatchers.Default) {
            borrarDatosUsecase.borrarCargaCero()
        }
        return true
    }
    fun borrarVisitas():Boolean{
        viewModelScope.launch(Dispatchers.Default) {
            borrarDatosUsecase.borrarVisitas()
        }
        return true
    }
    fun borrarHojaCarga():Boolean{
        viewModelScope.launch(Dispatchers.Default) {
            borrarDatosUsecase.borrarHojaCarga()
        }
        return true
    }
    fun borrarVentasDetPedidos(fecha:String):Boolean{
        viewModelScope.launch(Dispatchers.Default) {
            borrarDatosUsecase.borrarVentasDetPedidos(fecha)
        }
        return true
    }
    fun borrarVentasCabPedidos(fecha:String):Boolean{
        viewModelScope.launch(Dispatchers.Default) {
            borrarDatosUsecase.borrarVentasCabPedidos(fecha)
        }
        return true
    }
   fun borrarRegistrosSueltos():Boolean{
       viewModelScope.launch(Dispatchers.Default) {
           borrarDatosUsecase.borrarRegistrosSueltos()
       }
       return true
    }
    suspend fun comprobarDatosPendientesCabPedidos(fecha: String): Int {
        return withContext(Dispatchers.Default) {
            borrarDatosUsecase.comprobarDatosPendientesCabPedidos(fecha) ?: 0
        }
    }
    suspend fun comprobarDatosPendientesVisitas(fecha: String): Int {
        return withContext(Dispatchers.Default) {
            borrarDatosUsecase.comprobarDatosPendientesVisitas(fecha) ?: 0
        }
    }
    suspend fun comprobarDatosPendientesCobros(fecha: String): Int {
        return withContext(Dispatchers.Default) {
            borrarDatosUsecase.comprobarDatosPendientesCobros(fecha) ?: 0
        }
    }
   suspend fun compactarBD(){
       return withContext(Dispatchers.Default) {
           borrarDatosUsecase.compactarBD()
       }
    }
    fun comprobarDatosCorrutina(contexto: Context, fecha: String,  borrarPedidosChecked: Boolean, borrarCobrosChecked:Boolean, borrarIncidenciasChecked:Boolean, borrarHojaDeCargaChecked:Boolean){
         viewModelScope.launch(Dispatchers.Default) {
          comprobarDatos(contexto,fecha, borrarPedidosChecked,borrarCobrosChecked,borrarIncidenciasChecked,borrarHojaDeCargaChecked)
      }
    }
    fun compactarBaseDeDatos(contexto: Context){
        viewModelScope.launch(Dispatchers.Default) {
            compactarBDcorrutina(contexto)
        }
    }
    private suspend fun comprobarDatos(contexto: Context, fecha: String, borrarPedidosChecked: Boolean, borrarCobrosChecked:Boolean, borrarIncidenciasChecked:Boolean, borrarHojaDeCargaChecked:Boolean) = withContext(Dispatchers.Main) {
        val dialogo= showProgressDialog(contexto, "Comprobando datos sin exportar")
        val pendientes = arrayListOf<Int>(comprobarDatosPendientesCabPedidos(fecha), comprobarDatosPendientesCobros(fecha), comprobarDatosPendientesVisitas(fecha))
        var mensaje = ""

        if (pendientes[0] != 0) mensaje += " Ventas: ${pendientes[0]}\n"
        if (pendientes[1] != 0) mensaje += " Visitas: ${pendientes[1]}\n"
        if (pendientes[2] != 0) mensaje += " Cobros: ${pendientes[2]}\n"
        if (mensaje.isNotEmpty()) {
            withContext(Dispatchers.Main) {
                showOkDialog(contexto,"Realice una exportación de: \n$mensaje")
            }
        } else {
            borrarDatos(contexto,fecha, borrarPedidosChecked, borrarCobrosChecked, borrarHojaDeCargaChecked, borrarIncidenciasChecked)
        }
        if (dialogo.isShowing) dialogo.dismiss()
    }
    private suspend fun borrarDatos(contexto: Context, fecha:String, borrarPedidosChecked: Boolean, borrarCobrosChecked:Boolean, borrarIncidenciasChecked:Boolean, borrarHojaDeCargaChecked:Boolean): Boolean = coroutineScope {

        val dialogo = showProgressDialog(contexto, "Procesando datos")
        var flag = true
        try {
            withContext(Dispatchers.Main) {
                publishProgress(dialogo, "Realizando copia de seguridad")
                crearBackup(BORRADO)
                publishProgress(dialogo, "Compactando base de datos")
                compactarBD()
                if (borrarPedidosChecked && flag) {
                    publishProgress(dialogo, "Eliminando ventas")
                    flag = borrarVentasCabPedidos(fecha)
                    flag= borrarVentasDetPedidos(fecha)
                }
                if (borrarCobrosChecked && flag) {
                    publishProgress(dialogo, "Eliminando cobros")
                    flag = borrarCobros(fecha)
                }
                if (borrarHojaDeCargaChecked && flag) {
                    publishProgress(dialogo, "Eliminando hoja de carga")
                    flag =borrarHojaCarga()
                }
                if (borrarIncidenciasChecked && flag) {
                    publishProgress(dialogo, "Eliminando visitas")
                    flag =borrarVisitas()
                }
                publishProgress(dialogo, "Eliminando otros datos")
                if (flag) {
                    flag = borrarCargaCero()
                    borrarRegistrosSueltos()
                }
                publishProgress(dialogo, "Compactando base de datos")
                compactarBD()
                publishProgress(dialogo, "Eliminando ficheros")
                val filesToDelete = listOf(
                    "versionmovil.txt",
                    "existe.txt",
                    "LOGMOVIMIENTOS.TXT",
                    "LOGINVENTARIO.TXT",
                    "LOGEXPORTACION.TXT",
                    "FINMOVIMIENTO.TXT",
                    "FININVENTARIO.TXT",
                    "FIN.TXT"
                )
                filesToDelete.forEach { fileName ->
                    val file = File(Ruta + fileName)
                    file.delete()
                }
                eliminarCarpeta(File(Ruta + "informes"))
                eliminarCarpetaFecha10(File(Ruta +"/Logs/"))
                borrarBackups()
                val firmaFolder = File(Ruta + "/Firma")
                firmaFolder.listFiles()?.forEach { file ->
                    if (!file.isDirectory) {
                        val sdf = SimpleDateFormat("yyyy-MM-dd")
                        val fechaAnterior = sdf.parse(fecha)
                        if (fechaAnterior.after(sdf.parse(sdf.format(file.lastModified())))) {
                            file.delete()
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            flag = false
        } finally {
            dialogo.dismiss()
        }

        flag
    }
    private suspend fun compactarBDcorrutina(contexto: Context) = withContext(Dispatchers.Main) {
        val dialogo = showProgressDialog(contexto, "Compactando base de datos")
        var flag = false

        try {
            compactarBD()
            flag = true
        } catch (e: Exception) {
            e.printStackTrace()

        }

        if (flag) {
            showOkDialog(contexto, "Proceso realizado con éxito.")
        } else {
            showOkDialog(contexto, "Proceso no completado. Vuelve a intentarlo.")
        }

        if (dialogo.isShowing) dialogo.dismiss()
    }

    private fun eliminarCarpetaFecha10(carpeta: File) {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, -10)
        val fechaAnterior = calendar.time

        try {
            carpeta.listFiles()?.forEach { archivo ->
                val nombreArchivo = archivo.name
                val archivoFecha = try {
                    dateFormat.parse(nombreArchivo)
                } catch (e: Exception) {
                    null
                }
                if (archivoFecha != null && fechaAnterior.after(archivoFecha)) {
                    eliminarCarpeta(archivo)
                } else if (archivo.isDirectory) {
                    eliminarCarpetaFecha10(archivo)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun eliminarCarpeta(carpeta: File) {
        try {
            carpeta.walkTopDown().forEach {
                if (!it.deleteRecursively()) {
                    throw RuntimeException("Error al eliminar: $it")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    private suspend fun publishProgress(dialogo: AlertDialog, message: String) {
        withContext(Dispatchers.Main) {
            dialogo.setMessage(message)
        }
    }
}