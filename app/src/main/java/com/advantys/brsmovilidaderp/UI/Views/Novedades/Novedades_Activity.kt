package com.advantys.brsmovilidaderp.UI.Views.Novedades

import android.R.id
import android.content.res.Resources
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.databinding.ActivityNovedadesBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


@AndroidEntryPoint
class Novedades_Activity : AppCompatActivity() {

    private lateinit var binding : ActivityNovedadesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNovedadesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            title= "NOVEDADES"
        }
        mostrarNovedades()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            id.home -> {
                setResult(RESULT_OK)
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

//    private fun verificarSihayNovedades():Boolean{
//        var flag = false
//      val versionApp: String = obtenerVersionApp().replace(".", "")
//
//        if(LISTA_VERSIONES != 1){
//            mostrarNovedades()
//            flag = true
//        }
//        else{
//            flag = false
//        }
//        return flag
//    }

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
            binding.novedadesWebView.loadDataWithBaseURL(null, stringBuilder.toString(), "text/html", "utf-8", null)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}