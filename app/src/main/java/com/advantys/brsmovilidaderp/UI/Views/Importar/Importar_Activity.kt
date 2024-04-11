package com.advantys.brsmovilidaderp.UI.Views.Importar

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.advantys.brsmovilidaderp.databinding.ActivityImportarBinding

class Importar_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityImportarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityImportarBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            title= "IMPORTAR"
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