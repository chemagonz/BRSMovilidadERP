package com.advantys.brsmovilidaderp.UI.Views.Series

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.advantys.brsmovilidaderp.databinding.ActivityDetallesCentroBinding

class DetallesSerie_Activity : AppCompatActivity() {

    lateinit var binding: ActivityDetallesCentroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetallesCentroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //ACTION BAR
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "SERIES"
            setSubtitle("DETALLES")
        }

    }
}