package com.advantys.brsmovilidaderp.UI.Views.AcercaDe

import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.Configuracion_ViewModel
import com.advantys.brsmovilidaderp.Utils.actionBar
import com.advantys.brsmovilidaderp.Utils.obtenerVersionApp
import com.advantys.brsmovilidaderp.databinding.ActivityAcercaDeBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


@AndroidEntryPoint
class AcercaDe_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityAcercaDeBinding
    val configViewmodel : Configuracion_ViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAcercaDeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        actionBar("ACERCA DE")

        mostrarTerminal()
        obtenerVersion()
        obtenerModelo()
        mostrarNovedades()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun mostrarTerminal() {
        configViewmodel.getTerminal()
        configViewmodel.terminal.observe(this, Observer { terminal ->
            binding.acercadeTerminal.text = "Terminal nº: " +  terminal.toString()
        })
    }

    private fun obtenerModelo() {
        val modelo = mostrarNombreDispositivo()
        binding.acercadeInfoterminal.setText(modelo)
    }

    private fun obtenerVersion() {
        binding.acercadeNombreVersion.text = "AdvantysERP v." + obtenerVersionApp()
    }

    //Se obtiene el contenido de novedades
    fun mostrarNovedades(){
        try {
            val res: Resources = getResources()
            val inputStream: InputStream = res.openRawResource(R.raw.leeme)
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            val stringBuilder = StringBuilder()
            var line: String?

            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuilder.append(line).append("\n")
            }

            inputStream.close()
            binding.acercadeWebView.loadDataWithBaseURL(null, stringBuilder.toString(), "text/html", "utf-8", null)

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    //Devuelve el nombre del dispositivo que se está usando. Se inserta la función capitalize
    fun mostrarNombreDispositivo(): String {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        return if (model.startsWith(manufacturer)) capitalize(model) else capitalize(manufacturer) + " " + model
    }

    //Función para poner la primera letra en mayúsculas
    private fun capitalize(s: String?): String {
        if (s == null || s.length == 0) return ""

        val first = s[0]
        return if (Character.isUpperCase(first)) s else first.uppercaseChar()
            .toString() + s.substring(1)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}