package com.advantys.brsmovilidaderp.UI.Views.Fechas

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.databinding.ActivityFechasBinding

class Fechas_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityFechasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityFechasBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            title = "FECHAS"
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.aceptar_fechas, menu)
        val aceptarFechas = menu.findItem(R.id.aceptar_fechas)
        aceptarFechas?.setOnMenuItemClickListener {

            true
        }
        return true
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