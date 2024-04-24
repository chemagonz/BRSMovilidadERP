package com.advantys.brsmovilidaderp.UI.Views.Licencia

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Licencia_Entity
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.Licencia_ViewModel
import com.advantys.brsmovilidaderp.Utils.Respuesta
import com.advantys.brsmovilidaderp.Utils.TipoAlerta
import com.advantys.brsmovilidaderp.Utils.mostrarSnackbar
import com.advantys.brsmovilidaderp.Utils.showProgressDialog
import com.advantys.brsmovilidaderp.databinding.ActivityLicenciaBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Licencia_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityLicenciaBinding
    val licenciaViewmodel: Licencia_ViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLicenciaBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            title = "LICENCIA"
        }

        obtenerCodigo()
        inicializarHandlerLicencia()
        licenciaViewmodel.respuestaDialogo.observe(this,  Observer { respuesta ->
                showProgressDialog(respuesta.mensaje,1000)

        })

        licenciaViewmodel.respuesta.observe(this,  Observer { respuesta ->
            when (respuesta.tipo) {
                Respuesta.Tipo.OK -> mostrarSnackbar(respuesta.mensaje, TipoAlerta.ok)
                Respuesta.Tipo.ERROR -> mostrarSnackbar(respuesta.mensaje, TipoAlerta.error)
                else -> Unit
            }
        })

        binding.identificadorClienteLicencia.onFocusChangeListener = createOnFocusChangeListener(6)
        binding.numLicencia.onFocusChangeListener = createOnFocusChangeListener(4)
        binding.equipoLicencia.onFocusChangeListener = createOnFocusChangeListener(2)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.aceptar_licencia, menu)
        val aceptarItem = menu.findItem(R.id.aceptar_licencia)
        aceptarItem?.setOnMenuItemClickListener {
            aceptarLicencia()
            true
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    fun obtenerCodigo() {
        val androidId: String? =
            Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        binding.identificadorIDENDISP.setText(androidId)
    }

    @SuppressLint("HardwareIds")
    private fun aceptarLicencia() {

        val licencia = Licencia_Entity()
        licencia.cliente = binding.identificadorClienteLicencia.text.toString()
        licencia.numLicencia = binding.numLicencia.text.toString()
        licencia.idenProg = licencia.cliente + licencia.numLicencia + binding.identificadorIDENDISP.text.toString() + "ILC9TJWQBO9C54WCKAZV0H1P5OWAFR0QXPMDUOAH"
        val licenciaConcatenada = binding.licenciaLicencia1.text.toString()
            .uppercase() + "-" + binding.licenciaLicencia2.text.toString().uppercase()
        licencia.licencia = licenciaConcatenada

        if (binding.identificadorClienteLicencia.text.toString()
                .isEmpty() || binding.numLicencia.text.toString()
                .isEmpty() || binding.equipoLicencia.text.toString()
                .isEmpty() || (binding.licenciaLicencia1.text.toString()
                .isEmpty() && binding.licenciaLicencia2.text.toString().isEmpty())
        ){
          mostrarSnackbar("Los campos no deben estar vacíos", TipoAlerta.advertencia)
        }
        else{
            licenciaViewmodel.aceptar(licencia)
            setResult(RESULT_OK)
        }
    }

    //Funcion para rellenar ceros
    private fun createOnFocusChangeListener(maxLength: Int): View.OnFocusChangeListener {
        return View.OnFocusChangeListener { v, hasFocus ->
            val txt = (v as? EditText)?.text.toString()
            if (!hasFocus && txt.length < maxLength) {
                val stringBuilder = StringBuilder()
                for (i in 0 until maxLength - txt.length) {
                    stringBuilder.append("0")
                }
                stringBuilder.append(txt)
                (v as? EditText)?.setText(stringBuilder.toString())
            }
        }
    }
    //Funcion para cambiar foco al siguiente campo licencia2, una vez que licencia 1 tenga 8 caracteres
    private fun inicializarHandlerLicencia() {
        binding.licenciaLicencia1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    // Si la longitud actual es igual a 8, cambia el foco al siguiente EditText
                    if (it.length == 8) {
                        binding.licenciaLicencia1.clearFocus()
                        binding.licenciaLicencia2.requestFocus()
                    }
                }
            }
        })
    }
}

//Comentado por ahora hasta proxima actualizacion de funciones Moises

//    private fun mostrarDialogoDatosIncorrectos() {
//        val builder = AlertDialog.Builder(this)
//        builder.setTitle("No se pudo insertar licencia")
//            .setMessage("Para continuar, debe de rellenar todos los campos.")
//            .setPositiveButton("Aceptar") { dialog, which ->
//
//            }
//            .setCancelable(false)
//            .show()
//    }



