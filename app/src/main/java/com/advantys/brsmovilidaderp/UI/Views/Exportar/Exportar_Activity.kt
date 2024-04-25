package com.advantys.brsmovilidaderp.UI.Views.Exportar

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.advantys.brsmovilidaderp.databinding.ActivityExportarBinding

class Exportar_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityExportarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityExportarBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            title= "EXPORTAR"
        }
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