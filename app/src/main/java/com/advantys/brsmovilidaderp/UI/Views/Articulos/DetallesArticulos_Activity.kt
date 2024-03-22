package com.advantys.brsmovilidaderp.UI.Views.Articulos

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.advantys.brsmovilidaderp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetallesArticulos_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalles_articulos)

    }
}