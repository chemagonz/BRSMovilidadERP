package com.advantys.brsmovilidaderp

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment.getExternalStorageDirectory
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.advantys.brsmovilidaderp.UI.Views.Clientes.Clientes_Activity
import com.advantys.brsmovilidaderp.Utils.BDUtil
import com.advantys.brsmovilidaderp.Utils.BackupUtil
import com.advantys.brsmovilidaderp.Utils.PermisosUtils
import com.advantys.brsmovilidaderp.Utils.Utils
import com.advantys.brsmovilidaderp.databinding.ActivitySplashScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.util.Date
import javax.inject.Inject


@AndroidEntryPoint
class SplashScreen_activity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    @Inject lateinit var bdUtil: BDUtil
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Modificar versión de la aplicación
        binding.txtVersion.text = "Version 1.0"
        //Verificar permisos
        Handler(Looper.getMainLooper()).postDelayed({
            // Verificar permisos
            if (!PermisosUtils.verificarPermisosAlmacenamiento(this)) {
                PermisosUtils.solicitarPermisosAlmacenamiento(this)
            } else {
                continuarDespuesPermisos()
            }
        }, 50)
    }
    //Si no tiene los permisos, salta aviso para permitir o denegar.
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PermisosUtils.REQUEST_STORAGE_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
               continuarDespuesPermisos()
            } else {
                mostrarDialogoPermisosDenegados()
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun continuarDespuesPermisos(){
        val file= File(Utils.Ruta, "BRSAndroid.db")
        if(!file.exists()) {
            GenerarBBDD()
        } else {
            if(bdUtil.basedeDatosCorrupta().isNotEmpty())
                ProcesoInicio()
        }
    }

    private fun ProcesoInicio(){
        //Si la ruta y base de datos son correctas
        if(ObtenerRuta()){
            Utils.FechaHoy= Date()
            CrearCarpetasExportacion()
            //Si la ruta y base de datos son correctas
            if(ObtenerRuta()){
                CrearCarpetasExportacion()
                bdUtil.actualizarBD()
                if(ComprobarLicencia()){
                    GuardarLicenciaEnXML()
                    EntrarAlPrograma()
                }
            }
        }
    }
    //Función para generar la base de datos
    private fun GenerarBBDD() {
        File(Utils.Ruta + "BRSAndroid.db").delete()
    }
    private fun CrearCarpetasExportacion() {
        if (!File(Utils.Ruta + "Exportar").exists()) File(Utils.Ruta + "Exportar").mkdir()
        if (!File(Utils.Ruta + "Update").exists()) File(Utils.Ruta + "Update").mkdir()
    }
    private fun ComprobarLicencia():Boolean{
        var ok= false

        return ok
    }
    private fun GuardarLicenciaEnXML(){
    }

    private fun EntrarAlPrograma(){
        //idUtil.escribirIDPreferencias
        Utils.YaCargado= true
        //ComprobarConfiguracion
        BackupUtil.crearBackup(BackupUtil.INICIO)
        val intent = Intent(this, Clientes_Activity::class.java)
        startActivity(intent)
        finish()
    }
    //Comprobar si existe carpetas de la aplicación y su base de datos
    @SuppressLint("SuspiciousIndentation")
    private fun ObtenerRuta():Boolean{
        var ok: Boolean= true
        //Se comprueba la ruta de la aplicación
        val file = File(getExternalStorageDirectory().path + "/BRSAndroid")
        if(!file.exists())
           File(getExternalStorageDirectory().path + "/BRSAndroid/").mkdir()

        val filea = File(Utils.Ruta, "BRSAndroid.db")
        if(!filea.exists())
            mostrarDialogoBDNoDisponible()
            ok= false
        return ok
    }
    private fun mostrarDialogoBDNoDisponible(){
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