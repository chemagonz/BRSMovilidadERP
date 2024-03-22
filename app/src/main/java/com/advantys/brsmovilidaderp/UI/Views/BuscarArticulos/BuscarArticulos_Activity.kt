package com.advantys.brsmovilidaderp.UI.Views.BuscarArticulos

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.advantys.brsmovilidaderp.databinding.ActivityBuscarArticulosBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BuscarArticulos_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityBuscarArticulosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityBuscarArticulosBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        supportActionBar?.apply {
            title="BUSCAR ARTÍCULOS"
        }
    }
}