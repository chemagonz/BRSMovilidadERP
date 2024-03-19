package com.advantys.brsmovilidaderp.UI.Views.AjustesAvanzados

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.advantys.brsmovilidaderp.UI.Views.Clientes.Clientes_Activity
import com.advantys.brsmovilidaderp.databinding.ActivityAjustesAvanzadosBinding

class AjustesAvanzados_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityAjustesAvanzadosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAjustesAvanzadosBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        //ACTION BAR
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "AJUSTES AVANZADOS"
        }
    }
    //Manejo de botones del action bar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                //Boton para atras
                val intent = Intent(this, Clientes_Activity::class.java)
                startActivity(intent)
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

