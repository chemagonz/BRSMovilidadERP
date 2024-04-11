package com.advantys.brsmovilidaderp.UI.Views.BorrarVentas

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.advantys.brsmovilidaderp.databinding.ActivityBorrarDatosBinding

class BorrarDatos_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityBorrarDatosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityBorrarDatosBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            title= "BORRAR DATOS"
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}