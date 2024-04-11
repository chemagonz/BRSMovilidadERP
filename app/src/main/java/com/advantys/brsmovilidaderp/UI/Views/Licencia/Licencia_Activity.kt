package com.advantys.brsmovilidaderp.UI.Views.Licencia

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Licencia_Entity
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.Licencia_ViewModel
import com.advantys.brsmovilidaderp.databinding.ActivityLicenciaBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Licencia_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityLicenciaBinding
    val licenciaViewmodel: Licencia_ViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityLicenciaBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            title= "LICENCIA"
        }
        licenciaViewmodel.getLicencia()

        configurarEditTextConPaddingMaximo(binding.identificadorClienteLicencia, 6)
        configurarEditTextConPaddingMaximo(binding.numLicencia, 4 )
        obtenerCodigo()
        configurarEditTextConPaddingMaximo(binding.licenciaLicencia1, 8, binding.licenciaLicencia2)
        configurarEditTextConPaddingMaximo(binding.licenciaLicencia2, 4, null)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.aceptar_licencia, menu)
        val aceptarItem= menu.findItem(R.id.aceptar_licencia)
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
    private fun configurarEditTextConPaddingMaximo(editText: EditText, maximo: Int) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                editable?.let {
                    val texto = it.toString()
                    if (texto.length < maximo) {
                        val padding = maximo - texto.length
                        val paddingText = "0".repeat(padding)
                        editText.setText(paddingText + texto)
                        editText.setSelection(maximo)
                    }
                }
            }
        })
    }
    @SuppressLint("HardwareIds")
    fun obtenerCodigo() {
        val androidId: String? = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        binding.identificadorIDENDISP.setText(androidId)
    }
    @SuppressLint("HardwareIds")
    private  fun aceptarLicencia(){

        val licencia= Licencia_Entity()
        val androidId: String? = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        licencia.idenDisp= binding.identificadorIDENDISP.setText(androidId).toString()
        licencia.cliente= binding.identificadorClienteLicencia.text.toString()
        licencia.numLicencia= binding.numLicencia.text.toString()
        licencia.idenProg= licencia.cliente + licencia.numLicencia + binding.identificadorIDENDISP.text.toString() + "ILC9TJWQBO9C54WCKAZV0H1P5OWAFR0QXPMDUOAH"
        val licenciaConcatenada= binding.licenciaLicencia1.text.toString().uppercase() + "-" + binding.licenciaLicencia2.text.toString().uppercase()
        licencia.licencia= licenciaConcatenada

        if(binding.identificadorClienteLicencia.text.toString().isEmpty() || binding.numLicencia.text.toString().isEmpty() || binding.equipoLicencia.text.toString().isEmpty() || (binding.licenciaLicencia1.text.toString().isEmpty() && binding.licenciaLicencia2.text.toString().isEmpty()))
            mostrarDialogoDatosIncorrectos()

        if(licenciaViewmodel.aceptar(licencia)) {
            setResult(RESULT_OK)
            finish();
        }
    }

    private fun mostrarDialogoDatosIncorrectos() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("No se pudo insertar licencia")
            .setMessage("Para continuar, debe de rellenar todos los campos.")
            .setPositiveButton("Aceptar") { dialog, which ->

            }
            .setCancelable(false)
            .show()
    }

    //Este dialogo se llamaria en la clase licencia viewmodel
//    private fun mostrarDialogoLicenciaNoValida() {
//        val builder = AlertDialog.Builder(this)
//        builder.setTitle("Licencia no válida")
//            .setMessage("Para continuar, inserte una licencia correcta")
//            .setPositiveButton("Aceptar") { dialog, which ->
//            }
//            .setCancelable(false)
//            .show()
//    }

    private fun configurarEditTextConPaddingMaximo(editText: EditText, maximo: Int, siguienteEditText: EditText?) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                editable?.let {
                    val texto = it.toString()
                    if (texto.length > maximo) {
                        // Si el texto supera el límite, mueve hasta 4 caracteres al siguiente EditText si está disponible
                        siguienteEditText?.let { siguiente ->
                            val textoRestante = texto.substring(maximo)
                            editText.setText(texto.substring(0, maximo))
                            if (textoRestante.length > 4) {
                                siguiente.setText(textoRestante.substring(4))
                                editText.setSelection(maximo)
                            } else {
                                siguiente.setText(textoRestante)
                                siguiente.setSelection(textoRestante.length)
                            }
                        }
                    }
                }
            }
        })
    }
}


