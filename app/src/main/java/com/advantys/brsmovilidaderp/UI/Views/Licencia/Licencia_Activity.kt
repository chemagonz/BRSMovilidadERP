package com.advantys.brsmovilidaderp.UI.Views.Licencia

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Licencia_Entity
import com.advantys.brsmovilidaderp.Domain.Models.Licencia
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
        licenciaViewmodel.licenciaModel.observe(this, Observer { licencia ->
            mostrarCamposLicencia(licencia)
        })

        configurarEditTextConPaddingMaximo(binding.identificadorClienteLicencia, 6)
        configurarEditTextConPaddingMaximo(binding.identificadorLicencia, 4 )
    }

    private fun mostrarCamposLicencia(licencia: Licencia?){
        binding.identificadorIDENDISP.setText(licencia?.idenDisp)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.aceptar_licencia, menu)
        val aceptarItem= menu.findItem(R.id.aceptar_licencia)
        aceptarItem?.setOnMenuItemClickListener {
            //aceptarLicencia()
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
    private  fun aceptar(): Boolean {


            val clienteLicencia = binding.identificadorClienteLicencia.text
            val identificadorLicencia = binding.identificadorLicencia.text
            val identificadorIDENDISP = binding.identificadorIDENDISP.text
            val equipoLicencia = binding.equipoLicencia.text
            val licencia1 = binding.licenciaLicencia1.text
            val licencia2 = binding.licenciaLicencia2.text
            val licenciaConcatenada = "${licencia1} - ${licencia2}"
            val idenProg =
                "${clienteLicencia} + ${identificadorLicencia} + ${identificadorIDENDISP} + 'ILC9TJWQBO9C54WCKAZV0H1P5OWAFR0QXPMDUOAH'"

            val licencia = Licencia_Entity()

            if (clienteLicencia.isEmpty() || identificadorLicencia.isEmpty() || identificadorIDENDISP.isEmpty() || equipoLicencia.isEmpty() || licencia1.isEmpty() || licencia2.isEmpty() || idenProg.isEmpty()) {

                Toast.makeText(this, "Todos los campos deben estar rellenos", Toast.LENGTH_SHORT)
                    .show()
                return false
            }
            return true
        }
}


