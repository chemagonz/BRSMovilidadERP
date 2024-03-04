package com.advantys.brsmovilidaderp.UI.Views.Centros

import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.advantys.brsmovilidaderp.Data.DataBase.Daos.Centros_Dao
import com.advantys.brsmovilidaderp.R

class DetallesCentro_Activity : AppCompatActivity() {

    //Instancia de Centros_Dao
    private val centros = Centros_Dao(this)

    //Region variables
    val centrosList= centros.getAllDetalles()

    // Region Componentes
    private lateinit var edNIF: EditText
    private lateinit var edNombre: EditText
    private lateinit var edDireccion: EditText
    private lateinit var edCodPostal: EditText
    private lateinit var edPoblacion: EditText
    private lateinit var edProvincia: EditText
    private lateinit var edTelefono: EditText
    private lateinit var edSerie: EditText
    private lateinit var chVentaMenora: CheckBox
    private lateinit var chAplCargo: CheckBox
    private lateinit var edCalleCPMunicProvin: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_centro)


        iniciarComponentes()
        verDetallesCentro()
    }

    //Falta variables de los componentes y sus id
    private fun verDetallesCentro(){

        if(!centrosList.isEmpty()){
            val centro= centrosList.first()
                centro?.let {
                    edNIF.setText(it.NIF)
                    edNombre.setText(it.nombre)
                    edDireccion.setText(it.direccion)
                    edCodPostal.setText(it.codigoPostal?.toString())
                    edPoblacion.setText(it.poblacion)
                    edProvincia.setText(it.provincia)
                    edTelefono.setText(it.telefono?.toString())
                    edSerie.setText(it.serie?.toString())
                    it.ventaMenorA?.let { ventaMenorA ->
                        chVentaMenora.isChecked = ventaMenorA
                    }
                    it.aplicaCargo?.let { aplicaCargo ->
                        chAplCargo.isChecked = aplicaCargo
                    }
            }
        }

    }
    private fun iniciarComponentes(){
        edNIF = findViewById(R.id.edNIF)
        edNombre = findViewById(R.id.edNombre)
        edCalleCPMunicProvin= findViewById(R.id.edCalleCPMunicProvin)
        edDireccion = edCalleCPMunicProvin
        edCodPostal = edCalleCPMunicProvin
        edPoblacion = edCalleCPMunicProvin
        edProvincia = edCalleCPMunicProvin
        edTelefono = findViewById(R.id.edTelefono)
        edSerie = findViewById(R.id.edSerie)
        chVentaMenora = findViewById(R.id.checkVenMenorA)
        chAplCargo = findViewById(R.id.checkAplCargo)
    }

}