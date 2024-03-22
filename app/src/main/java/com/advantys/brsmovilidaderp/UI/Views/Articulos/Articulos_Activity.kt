package com.advantys.brsmovilidaderp.UI.Views.Articulos

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.advantys.brsmovilidaderp.databinding.ActivityArticulosBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Articulos_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityArticulosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityArticulosBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        supportActionBar.apply {
            supportActionBar?.title="ARTÍCULOS"
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeButtonEnabled(true)
        }
    }
}