package com.advantys.brsmovilidaderp.UI.Views.Rutas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.advantys.brsmovilidaderp.databinding.ActivityRutasBinding

class Rutas_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityRutasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRutasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //ACTION BAR
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "RUTAS"
        }
    }
}