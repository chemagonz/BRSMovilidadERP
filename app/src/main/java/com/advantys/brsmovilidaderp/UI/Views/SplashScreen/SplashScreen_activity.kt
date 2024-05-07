package com.advantys.brsmovilidaderp.UI.Views.SplashScreen

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Environment.getExternalStorageDirectory
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.advantys.brsmovilidaderp.BRSMovilidadERPApp
import com.advantys.brsmovilidaderp.UI.ViewModels.SplashScreen_ViewModel
import com.advantys.brsmovilidaderp.UI.Views.Clientes.Clientes_Activity
import com.advantys.brsmovilidaderp.UI.Views.Licencia.Licencia_Activity
import com.advantys.brsmovilidaderp.Utils.BDUtil
import com.advantys.brsmovilidaderp.Utils.FechaHoy
import com.advantys.brsmovilidaderp.Utils.INICIO
import com.advantys.brsmovilidaderp.Utils.MostrarPor
import com.advantys.brsmovilidaderp.Utils.OrdenarPor
import com.advantys.brsmovilidaderp.Utils.PermisosUtils
import com.advantys.brsmovilidaderp.Utils.Ruta
import com.advantys.brsmovilidaderp.Utils.YaCargado
import com.advantys.brsmovilidaderp.Utils.crearBackup
import com.advantys.brsmovilidaderp.Utils.obtenerVersionApp
import com.advantys.brsmovilidaderp.databinding.ActivitySplashScreenBinding
import com.google.firebase.BuildConfig
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.util.Date
import javax.inject.Inject


@AndroidEntryPoint
class SplashScreen_activity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    @Inject lateinit var bdUtil: BDUtil



    val splashScreenviewmodel: SplashScreen_ViewModel by viewModels()

    private val responseLauncher= registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ activityResult->

        if(activityResult.resultCode== RESULT_OK){
           EntrarAlPrograma()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Verificar permisos
        Handler(Looper.getMainLooper()).postDelayed({
            // Verificar permisos
            if (!PermisosUtils.verificarPermisosAlmacenamiento(this)) {
                PermisosUtils.solicitarPermisosAlmacenamiento(this)
            } else {
                continuarDespuesPermisos()
            }
        }, 500)

    }

    //Si no tiene los permisos, salta aviso para permitir o denegar.
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PermisosUtils.REQUEST_STORAGE_PERMISSION) {
            if(grantResults.isNotEmpty()){
                var flag = true

                for (resultado in grantResults) {
                    if (resultado == PackageManager.PERMISSION_DENIED) flag = false
                }

                if(flag) continuarDespuesPermisos()
                else mostrarDialogoPermisosDenegados()
            }
            else mostrarDialogoPermisosDenegados()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun continuarDespuesPermisos() {
        val file = File(Ruta, "BRSAndroid.db")
        if (!file.exists()) {
            GenerarBBDD()
        } else {
            if (bdUtil.basedeDatosCorrupta().isNotEmpty())
                ProcesoInicio()
        }
    }

    fun ProcesoInicio() {
        //Si la ruta y base de datos son correctas
        if (ObtenerRuta()) {
            FechaHoy = Date()
            CrearCarpetasExportacion()
            //Si la ruta y base de datos son correctas
            if (ObtenerRuta()) {
                CrearCarpetasExportacion()
                bdUtil.actualizarBD()
                if (ComprobarLicencia()) {
                    GuardarLicenciaEnXML()
                    EntrarAlPrograma()
                } else {
                    entrarLicenciaActivity()
                }
            }
        }
    }

    //Función para generar la base de datos
    private fun GenerarBBDD() {
        File(Ruta + "BRSAndroid.db").delete()
    }

    private fun CrearCarpetasExportacion() {
        if (!File(Ruta + "Exportar").exists()) File(Ruta + "Exportar").mkdir()
        if (!File(Ruta + "Update").exists()) File(Ruta + "Update").mkdir()
    }

    private fun ComprobarLicencia(): Boolean {
        var ok = true
//        var licencia = Licencia_Entity()
//        licencia = splashViewmodel.ObtenerLicencia()
//        if (ValidarLicencia(licencia))
//            ok = true
//
        return ok
    }

//    private fun ValidarLicencia(licencia: Licencia_Entity): Boolean {
//        var ok = false
//        if (licencia != null) {
//            splashViewmodel.verificarLicencia(licencia)
//            ok = true
//        }
//        return ok
//    }

    private fun GuardarLicenciaEnXML() {
    }
    private fun cargarClientesenLista(){
        // Modificar versión de la aplicación
        binding.txtVersion.text = " v. " + obtenerVersionApp()

    }

     fun EntrarAlPrograma() {
        //idUtil.escribirIDPreferencias
         YaCargado = true
        //ComprobarConfiguracion
         crearBackup(INICIO)
         cargarClientesenLista()
         val intent = Intent(this, Clientes_Activity::class.java)
         startActivity(intent)
         finish()
    }

    //Comprobar si existe carpetas de la aplicación y su base de datos
    @SuppressLint("SuspiciousIndentation")
    private fun ObtenerRuta(): Boolean {
        var ok: Boolean = true
        //Se comprueba la ruta de la aplicación
        val file = File(getExternalStorageDirectory().path + "/BRSAndroid")
        if (!file.exists())
            File(getExternalStorageDirectory().path + "/BRSAndroid/").mkdir()

        val filea = File(Ruta, "BRSAndroid.db")
        if (!filea.exists()) {
            mostrarDialogoBDNoDisponible()
            ok = false
        }

        return ok
    }

    private fun entrarLicenciaActivity() {
        val intent = Intent(this, Licencia_Activity::class.java)
        responseLauncher.launch(intent)
        finish()
    }

    private fun mostrarDialogoBDNoDisponible() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Base de datos no encontrada")
            .setPositiveButton("Aceptar") { dialog, which ->
                GenerarBBDD()
            }
            .setNegativeButton("Cancelar") { dialog, which ->
                finish()
            }
            .setCancelable(false)
            .show()
    }

    private fun mostrarDialogoPermisosDenegados() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Permisos denegados")
            .setMessage("Para continuar, por favor, concede los permisos de almacenamiento en la configuración de la aplicación.")
            .setPositiveButton("Configuración") { dialog, which ->
                PermisosUtils.irConfiguracionPermisos(this)
            }
            .setNegativeButton("Cancelar") { dialog, which ->
                finish()
            }
            .setCancelable(false)
            .show()
    }


}