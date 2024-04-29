package com.advantys.brsmovilidaderp.UI.ViewModels

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.brsmovilidaderp.Domain.UseCases.BorrarDatos_UseCase
import com.advantys.brsmovilidaderp.Utils.BORRADO
import com.advantys.brsmovilidaderp.Utils.Respuesta
import com.advantys.brsmovilidaderp.Utils.Ruta
import com.advantys.brsmovilidaderp.Utils.borrarBackups
import com.advantys.brsmovilidaderp.Utils.crearBackup
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class BorrarDatos_ViewModel @Inject constructor(private var borrarDatosUsecase: BorrarDatos_UseCase): ViewModel() {
    private var _respuesta = MutableLiveData<Respuesta>()
    private var _respuestaDialogo = MutableLiveData<Respuesta>()
    val respuesta : LiveData<Respuesta> get() = _respuesta
    val respuestaDialogo : LiveData<Respuesta> get() = _respuestaDialogo

    fun comprobarDatosCorrutina(fecha: String,  borrarPedidosChecked: Boolean, borrarCobrosChecked:Boolean, borrarIncidenciasChecked:Boolean, borrarHojaDeCargaChecked:Boolean){
        viewModelScope.launch(Dispatchers.Default) {
            comprobarDatos(fecha, borrarPedidosChecked,borrarCobrosChecked,borrarIncidenciasChecked,borrarHojaDeCargaChecked)
        }
    }
    fun compactarBaseDeDatos(contexto: Context){
        viewModelScope.launch(Dispatchers.Default) {
            compactarBDcorrutina(contexto)
        }
    }

    private suspend fun comprobarDatos(fecha: String, borrarPedidosChecked: Boolean, borrarCobrosChecked:Boolean, borrarIncidenciasChecked:Boolean, borrarHojaDeCargaChecked:Boolean) = withContext(Dispatchers.Main) {
        _respuestaDialogo.postValue(Respuesta.cargando("Comprobando datos sin exportar"))
        delay(500)
        val pendientes = arrayListOf(borrarDatosUsecase.comprobarDatosPendientesCabPedidos(fecha), borrarDatosUsecase.comprobarDatosPendientesCobros(fecha), borrarDatosUsecase.comprobarDatosPendientesVisitas(fecha))
        var mensaje = ""

        if (pendientes[0] != 0) mensaje += " Ventas: ${pendientes[0]}\n"
        if (pendientes[1] != 0) mensaje += " Visitas: ${pendientes[1]}\n"
        if (pendientes[2] != 0) mensaje += " Cobros: ${pendientes[2]}\n"
        if (mensaje.isNotEmpty()) {
            withContext(Dispatchers.Main) {

                _respuestaDialogo.postValue(Respuesta.ok("Realice una exportación de: \n$mensaje"))
            }
        } else {
            borrarDatos(fecha, borrarPedidosChecked, borrarCobrosChecked, borrarHojaDeCargaChecked, borrarIncidenciasChecked)
        }

    }
    @SuppressLint("SuspiciousIndentation")
    private suspend fun borrarDatos(fecha:String, borrarPedidosChecked: Boolean, borrarCobrosChecked:Boolean, borrarIncidenciasChecked:Boolean, borrarHojaDeCargaChecked:Boolean): Boolean = coroutineScope {

        var flag = true

            withContext(Dispatchers.IO) {
                _respuestaDialogo.postValue(Respuesta.cargando("Realizando copia de seguridad"))
                crearBackup(BORRADO)
            }

            withContext(Dispatchers.IO) {
                delay(1000)
                _respuestaDialogo.postValue(Respuesta.cargando("Compactando base de datos"))
                borrarDatosUsecase.compactarBD()
            }

            if (borrarPedidosChecked && flag) {
                withContext(Dispatchers.IO) {
                    delay(1000)
                    _respuestaDialogo.postValue(Respuesta.cargando("Eliminando ventas"))
                    flag = borrarDatosUsecase.borrarVentasCabPedidos(fecha) && borrarDatosUsecase.borrarVentasDetPedidos(fecha)
                }
            }

            if (borrarCobrosChecked && flag) {
                withContext(Dispatchers.IO) {
                    delay(1000)
                    _respuestaDialogo.postValue(Respuesta.cargando("Eliminando cobros"))
                    flag = borrarDatosUsecase.borrarCobros(fecha)
                }
            }

            if (borrarHojaDeCargaChecked && flag) {
                withContext(Dispatchers.IO) {
                    delay(1000)
                    _respuestaDialogo.postValue(Respuesta.cargando("Eliminando hoja de carga"))
                    flag = borrarDatosUsecase.borrarHojaCarga()
                }


                }
                if (borrarIncidenciasChecked && flag) {
                    withContext(Dispatchers.IO) {
                        delay(1000)
                        _respuestaDialogo.postValue(Respuesta.cargando("Eliminando visitas"))
                        flag = borrarDatosUsecase.borrarVisitas()
                    }

                }
            withContext(Dispatchers.IO) {
                delay(1000)
                _respuestaDialogo.postValue(Respuesta.cargando("Eliminando otros datos"))
                if (flag) {
                    flag = borrarDatosUsecase.borrarCargaCero()
                    borrarDatosUsecase.borrarRegistrosSueltos()
                }
            }

            withContext(Dispatchers.IO) {
                delay(1000)
                _respuestaDialogo.postValue(Respuesta.cargando("Compactando base de datos"))
                borrarDatosUsecase.compactarBD()
            }

            withContext(Dispatchers.IO) {
                delay(1000)
                _respuestaDialogo.postValue(Respuesta.cargando("Eliminando ficheros"))
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
        withContext(Dispatchers.IO) {
            if (flag) {
                delay(1000)
                _respuesta.postValue(Respuesta.ok("Datos borrados correctamente"))
            } else {
                delay(1000)
                _respuesta.postValue(Respuesta.error("Error al borrar datos"))
            }
        }
        flag
    }
    private suspend fun compactarBDcorrutina(contexto: Context) = coroutineScope {

        _respuestaDialogo.postValue(Respuesta.cargando("Compactando base de datos"))
        try {
            delay(1000)
            borrarDatosUsecase.compactarBD()
            _respuesta.postValue(Respuesta.ok("Proceso realizado con éxito."))
        } catch (e: Exception) {
            _respuesta.postValue(Respuesta.error("Error al compactar la base de datos"))
        }

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

}